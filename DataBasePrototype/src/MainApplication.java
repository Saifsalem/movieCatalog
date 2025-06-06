import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;

public class MainApplication extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        try {
            // Try to find the FXML file and create a proper FXMLLoader
            URL fxmlLocation = null;
            
            // Option 1: Try loading from resources/fxml directory
            fxmlLocation = getClass().getResource("/fxml/MainDashboard.fxml");
            if (fxmlLocation != null) {
                System.out.println("Found FXML at: " + fxmlLocation);
            } else {
                System.out.println("FXML not found at /fxml/MainDashboard.fxml");
                
                // Option 2: Try loading from resources root
                fxmlLocation = getClass().getResource("/MainDashboard.fxml");
                if (fxmlLocation != null) {
                    System.out.println("Found FXML at: " + fxmlLocation);
                } else {
                    System.out.println("FXML not found at /MainDashboard.fxml");
                    
                    // Option 3: Try loading from same package
                    fxmlLocation = getClass().getResource("MainDashboard.fxml");
                    if (fxmlLocation != null) {
                        System.out.println("Found FXML at: " + fxmlLocation);
                    }
                }
            }
            
            if (fxmlLocation == null) {
                System.err.println("ERROR: Could not find MainDashboard.fxml in any location!");
                System.err.println("Please ensure MainDashboard.fxml exists in one of these locations:");
                System.err.println("1. src/main/resources/fxml/MainDashboard.fxml");
                System.err.println("2. src/main/resources/MainDashboard.fxml");
                System.err.println("3. Same directory as MainApplication.java");
                
                // Create a simple scene without FXML for testing
                createSimpleScene(primaryStage);
                return;
            }
            
            // Create FXMLLoader with the found location
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(fxmlLocation);
            
            System.out.println("Loading FXML from: " + fxmlLocation);
            
            // Load the FXML
            Scene scene = new Scene(loader.load(), 1200, 800);
            
            primaryStage.setTitle("Project Management System");
            primaryStage.setScene(scene);
            primaryStage.setResizable(true);
            primaryStage.show();
            
            System.out.println("Application started successfully!");
            
            // Test database connection
            testDatabaseConnection();
            
        } catch (IOException e) {
            System.err.println("Error loading FXML: " + e.getMessage());
            e.printStackTrace();
            
            // Fallback to simple scene
            createSimpleScene(primaryStage);
            
        } catch (Exception e) {
            System.err.println("Unexpected error: " + e.getMessage());
            e.printStackTrace();
            
            // Fallback to simple scene
            createSimpleScene(primaryStage);
        }
    }
    
    private void createSimpleScene(Stage primaryStage) {
        try {
            // Create a simple scene without FXML as fallback
            javafx.scene.control.Label label = new javafx.scene.control.Label("Project Management System - Running without FXML");
            label.setStyle("-fx-font-size: 18px; -fx-padding: 50px;");
            
            javafx.scene.layout.VBox root = new javafx.scene.layout.VBox();
            root.getChildren().add(label);
            root.getChildren().add(new javafx.scene.control.Label("Please check FXML file location and try again."));
            
            Scene scene = new Scene(root, 600, 400);
            primaryStage.setTitle("Project Management System - Fallback Mode");
            primaryStage.setScene(scene);
            primaryStage.show();
            
            System.out.println("Running in fallback mode without FXML");
            
        } catch (Exception e) {
            System.err.println("Failed to create fallback scene: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    private void testDatabaseConnection() {
        try {
            DatabaseConnection.getConnection();
            System.out.println("✓ Database connection test successful!");
        } catch (Exception e) {
            System.err.println("✗ Database connection failed: " + e.getMessage());
            System.err.println("Please check:");
            System.err.println("1. MySQL server is running");
            System.err.println("2. Database 'project_management_system' exists");
            System.err.println("3. Username and password in DatabaseConnection.java are correct");
        }
    }
    
    @Override
    public void stop() {
        try {
            DatabaseConnection.closeConnection();
            System.out.println("Application stopped and database connection closed.");
        } catch (Exception e) {
            System.err.println("Error closing database connection: " + e.getMessage());
        }
    }
    
    public static void main(String[] args) {
        System.out.println("Starting Project Management System...");
        System.out.println("Java Version: " + System.getProperty("java.version"));
        System.out.println("JavaFX Version: " + System.getProperty("javafx.version"));
        
        launch(args);
    }
}