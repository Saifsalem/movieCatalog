import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class MainApplication extends Application {

	@Override
	public void start(Stage primaryStage) {
		try {
			// Test database connection first
			testDatabaseConnection();

			// Try to find the FXML file
			URL fxmlLocation = null;

			// Option 1: Try loading from resources/fxml directory
			fxmlLocation = getClass().getResource("/fxml/ComprehensiveProjectManagement.fxml");
			if (fxmlLocation == null) {
				// Option 2: Try loading from resources root
				fxmlLocation = getClass().getResource("/ComprehensiveProjectManagement.fxml");
				if (fxmlLocation == null) {
					// Option 3: Try loading from same package
					fxmlLocation = getClass().getResource("ComprehensiveProjectManagement.fxml");
				}
			}

			if (fxmlLocation == null) {
				System.err.println("ERROR: Could not find ComprehensiveProjectManagement.fxml!");
				System.err.println("Please ensure the FXML file exists in one of these locations:");
				System.err.println("1. src/main/resources/fxml/ComprehensiveProjectManagement.fxml");
				System.err.println("2. src/main/resources/ComprehensiveProjectManagement.fxml");
				System.err.println("3. Same directory as FinalMainApplication.java");

				// Create a simple scene without FXML for testing
				createFallbackScene(primaryStage);
				return;
			}

			// Create FXMLLoader with the found location
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(fxmlLocation);

			System.out.println("Loading FXML from: " + fxmlLocation);

			// Load the FXML
			Scene scene = new Scene(loader.load(), 1400, 900);

			primaryStage.setTitle("Comprehensive Project Management System");
			primaryStage.setScene(scene);
			primaryStage.setResizable(true);
			primaryStage.setMaximized(true);
			primaryStage.show();

			System.out.println("Application started successfully!");

		} catch (IOException e) {
			System.err.println("Error loading FXML: " + e.getMessage());
			e.printStackTrace();

			// Fallback to simple scene
			createFallbackScene(primaryStage);

		} catch (Exception e) {
			System.err.println("Unexpected error: " + e.getMessage());
			e.printStackTrace();

			// Fallback to simple scene
			createFallbackScene(primaryStage);
		}
	}

	private void createFallbackScene(Stage primaryStage) {
		try {
			// Create a simple scene without FXML as fallback
			VBox root = new VBox(20);
			root.setStyle("-fx-padding: 50; -fx-alignment: center; -fx-background-color: #ecf0f1;");

			Label titleLabel = new Label("Comprehensive Project Management System");
			titleLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: #2c3e50;");

			Label statusLabel = new Label("Running in fallback mode without FXML");
			statusLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: #7f8c8d;");

			Label instructionLabel = new Label("Please check FXML file location and restart the application.");
			instructionLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #e74c3c;");

			Label featuresLabel = new Label("System Features:");
			featuresLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: #2c3e50;");

			Label feature1 = new Label("✓ Complete CRUD operations for all 10+ entities");
			Label feature2 = new Label("✓ Advanced sorting (ASC/DESC) and search functionality");
			Label feature3 = new Label("✓ Statistical operations (COUNT, MAX, MIN)");
			Label feature4 = new Label("✓ Comprehensive business reports and analytics");
			Label feature5 = new Label("✓ Real-time dashboard with KPIs");
			Label feature6 = new Label("✓ MySQL database integration with proper constraints");
			Label feature7 = new Label("✓ JavaFX GUI with modern Material Design interface");

			String featureStyle = "-fx-font-size: 14px; -fx-text-fill: #27ae60; -fx-padding: 5 0 0 20;";
			feature1.setStyle(featureStyle);
			feature2.setStyle(featureStyle);
			feature3.setStyle(featureStyle);
			feature4.setStyle(featureStyle);
			feature5.setStyle(featureStyle);
			feature6.setStyle(featureStyle);
			feature7.setStyle(featureStyle);

			root.getChildren().addAll(titleLabel, statusLabel, instructionLabel, featuresLabel, feature1, feature2,
					feature3, feature4, feature5, feature6, feature7);

			Scene scene = new Scene(root, 800, 600);
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

			// Show success alert
			showAlert("Database Connection", "Successfully connected to MySQL database!", Alert.AlertType.INFORMATION);

		} catch (Exception e) {
			System.err.println("✗ Database connection failed: " + e.getMessage());
			System.err.println("Please check:");
			System.err.println("1. MySQL server is running");
			System.err.println("2. Database 'project_management_system' exists");
			System.err.println("3. Username and password in DatabaseConnection.java are correct");
			System.err.println("4. MySQL JDBC driver is in the classpath");

			// Show error alert
			showAlert("Database Connection Error",
					"Failed to connect to database: " + e.getMessage() + "\n\nPlease ensure:\n"
							+ "1. MySQL server is running\n" + "2. Database 'project_management_system' exists\n"
							+ "3. Credentials in DatabaseConnection.java are correct\n"
							+ "4. MySQL JDBC driver is available",
					Alert.AlertType.ERROR);
		}
	}

	private void showAlert(String title, String message, Alert.AlertType type) {
		try {
			Alert alert = new Alert(type);
			alert.setTitle(title);
			alert.setHeaderText(null);
			alert.setContentText(message);
			alert.showAndWait();
		} catch (Exception e) {
			// If JavaFX isn't fully initialized, just print to console
			System.out.println(title + ": " + message);
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
		System.out.println("=========================================");
		System.out.println("Comprehensive Project Management System");
		System.out.println("=========================================");
		System.out.println("Java Version: " + System.getProperty("java.version"));
		System.out.println("JavaFX Version: " + System.getProperty("javafx.version"));
		System.out.println("Starting application...");
		System.out.println();

		// Print system features
		System.out.println("System Features:");
		System.out.println("• Complete CRUD operations for all entities");
		System.out.println("• Advanced sorting (ASC/DESC) and search functionality");
		System.out.println("• Statistical operations (COUNT, MAX, MIN)");
		System.out.println("• Comprehensive business reports and analytics");
		System.out.println("• Real-time dashboard with key performance indicators");
		System.out.println("• MySQL database with proper constraints and relationships");
		System.out.println("• Modern JavaFX GUI with Material Design principles");
		System.out.println();

		launch(args);
	}
}