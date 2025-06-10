-- Project Management Database Schema
-- MySQL Implementation

CREATE DATABASE IF NOT EXISTS project_management;
USE project_management;


CREATE TABLE Branch (
    branch_id INT PRIMARY KEY AUTO_INCREMENT,
    location VARCHAR(255) NOT NULL
);

-- Create Department table
CREATE TABLE Department (
    dept_id INT PRIMARY KEY AUTO_INCREMENT,
    dept_name VARCHAR(100) NOT NULL
);

-- Create Role table
CREATE TABLE Role (
    role_id INT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(100) NOT NULL
);

-- Create Supplier table
CREATE TABLE Supplier (
    supp_id INT PRIMARY KEY AUTO_INCREMENT,
    supp_name VARCHAR(255) NOT NULL,
    location VARCHAR(255)
);

-- Create Material table
CREATE TABLE Material (
    material_id INT PRIMARY KEY AUTO_INCREMENT,
    mname VARCHAR(255) NOT NULL,
    price DECIMAL(10,2) NOT NULL
);

-- Create Client table
CREATE TABLE Client (
    client_id INT PRIMARY KEY AUTO_INCREMENT,
    client_name VARCHAR(255) NOT NULL,
    location VARCHAR(255),
    requested_by VARCHAR(255),
    based_in VARCHAR(255)
);

-- Create Employee table
CREATE TABLE Employee (
    emp_id INT PRIMARY KEY AUTO_INCREMENT,
    emp_name VARCHAR(255) NOT NULL,
    position_id INT,
    salary DECIMAL(10,2),
    branch_id INT,
    manager_id INT,
    department_id INT,
    is_manager BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (position_id) REFERENCES Role(role_id),
    FOREIGN KEY (branch_id) REFERENCES Branch(branch_id),
    FOREIGN KEY (manager_id) REFERENCES Employee(emp_id),
    FOREIGN KEY (department_id) REFERENCES Department(dept_id)
);

-- Create Projects table
CREATE TABLE Projects (
    project_id INT PRIMARY KEY AUTO_INCREMENT,
    pname VARCHAR(255) NOT NULL,
    location VARCHAR(255),
    cost DECIMAL(12,2),
    revenue DECIMAL(12,2),
    branch_id INT,
    client_id INT,
    assigned_to INT,
    FOREIGN KEY (branch_id) REFERENCES Branch(branch_id),
    FOREIGN KEY (client_id) REFERENCES Client(client_id),
    FOREIGN KEY (assigned_to) REFERENCES Employee(emp_id)
);

-- Create Works_On junction table (Employee works on Projects)
CREATE TABLE Works_On (
    emp_id INT,
    project_id INT,
    role VARCHAR(100),
    PRIMARY KEY (emp_id, project_id),
    FOREIGN KEY (emp_id) REFERENCES Employee(emp_id) ON DELETE CASCADE,
    FOREIGN KEY (project_id) REFERENCES Projects(project_id) ON DELETE CASCADE
);

-- Create Project_Materials junction table
CREATE TABLE Project_Materials (
    project_id INT,
    material_id INT,
    quantity INT NOT NULL,
    PRIMARY KEY (project_id, material_id),
    FOREIGN KEY (project_id) REFERENCES Projects(project_id) ON DELETE CASCADE,
    FOREIGN KEY (material_id) REFERENCES Material(material_id) ON DELETE CASCADE
);

-- Create Project_Suppliers junction table
CREATE TABLE Project_Suppliers (
    project_id INT,
    supplier_id INT,
    PRIMARY KEY (project_id, supplier_id),
    FOREIGN KEY (project_id) REFERENCES Projects(project_id) ON DELETE CASCADE,
    FOREIGN KEY (supplier_id) REFERENCES Supplier(supp_id) ON DELETE CASCADE
);

-- Create Phase table
CREATE TABLE Phase (
    phase_id INT PRIMARY KEY AUTO_INCREMENT,
    project_id INT NOT NULL,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    start_date DATE,
    end_date DATE,
    status VARCHAR(50) DEFAULT 'Planning',
    FOREIGN KEY (project_id) REFERENCES Projects(project_id) ON DELETE CASCADE
);

-- Create Payment table
CREATE TABLE Payment (
    payment_id INT PRIMARY KEY AUTO_INCREMENT,
    from_client INT,
    to_supplier INT,
    amount DECIMAL(10,2) NOT NULL,
    pdate DATE NOT NULL,
    pmethod VARCHAR(50),
    FOREIGN KEY (from_client) REFERENCES Client(client_id),
    FOREIGN KEY (to_supplier) REFERENCES Supplier(supp_id)
);

-- Create Purchase table
CREATE TABLE Purchase (
    purchase_id INT PRIMARY KEY AUTO_INCREMENT,
    supplier_id INT NOT NULL,
    material_id INT NOT NULL,
    quantity INT NOT NULL,
    purchase_date DATE NOT NULL,
    total_cost DECIMAL(10,2) NOT NULL,
    FOREIGN KEY (supplier_id) REFERENCES Supplier(supp_id),
    FOREIGN KEY (material_id) REFERENCES Material(material_id)
);

-- Create Contract table
CREATE TABLE Contract (
    contract_id INT PRIMARY KEY AUTO_INCREMENT,
    project_id INT NOT NULL,
    client_id INT NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE,
    total_value DECIMAL(12,2) NOT NULL,
    status VARCHAR(50) DEFAULT 'Active',
    FOREIGN KEY (project_id) REFERENCES Projects(project_id),
    FOREIGN KEY (client_id) REFERENCES Client(client_id)
);

-- Create Schedule table
CREATE TABLE Schedule (
    schedule_id INT PRIMARY KEY AUTO_INCREMENT,
    project_id INT NOT NULL,
    phase_id INT,
    start_date DATE NOT NULL,
    end_date DATE,
    task_details TEXT,
    FOREIGN KEY (project_id) REFERENCES Projects(project_id) ON DELETE CASCADE,
    FOREIGN KEY (phase_id) REFERENCES Phase(phase_id) ON DELETE SET NULL
);

-- Create Sales table
CREATE TABLE Sales (
    sale_id INT PRIMARY KEY AUTO_INCREMENT,
    project_id INT NOT NULL,
    client_id INT NOT NULL,
    amount DECIMAL(10,2) NOT NULL,
    issue_date DATE NOT NULL,
    due_date DATE,
    FOREIGN KEY (project_id) REFERENCES Projects(project_id),
    FOREIGN KEY (client_id) REFERENCES Client(client_id)
);

