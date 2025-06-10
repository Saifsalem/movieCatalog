package application;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import java.time.LocalDate;
import java.time.Year;

public class MovieCatalogGUI extends Application {
    
    private MovieCatalog catalog;
    private TableView<Movie> movieTable;
    private ObservableList<Movie> movieData;
    private int currentIndex = 0;
    private Label statusLabel;
    private Label hashIndexLabel;
    private Label treeHeightLabel;
    
    public static void main(String[] args) {
        launch(args);
    }
    
    public void start(Stage primaryStage) {
        catalog = new MovieCatalog();
        movieData = FXCollections.observableArrayList();
        
        primaryStage.setTitle("Movie Catalog Management System");
        
        // Create main layout
        BorderPane mainLayout = new BorderPane();
        mainLayout.setStyle("-fx-background-color: #f8f4ff;");
        
        // Create menu bar
        MenuBar menuBar = createMenuBar(primaryStage);
        mainLayout.setTop(menuBar);
        
        // Create center content
        VBox centerContent = createCenterContent();
        mainLayout.setCenter(centerContent);
        
        // Create bottom status bar
        HBox statusBar = createStatusBar();
        mainLayout.setBottom(statusBar);
        
        Scene scene = new Scene(mainLayout, 1200, 800);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    private MenuBar createMenuBar(Stage primaryStage) {
        MenuBar menuBar = new MenuBar();
        menuBar.setStyle("-fx-background-color: #6a4c93; -fx-text-fill: white;");
        
        // File Menu
        Menu fileMenu = new Menu("File");
        fileMenu.setStyle("-fx-text-fill: white;");
        
        MenuItem openItem = new MenuItem("Open");
        openItem.setOnAction(e -> openFile(primaryStage));
        
        MenuItem saveItem = new MenuItem("Save");
        saveItem.setOnAction(e -> saveFile(primaryStage));
        
        MenuItem exitItem = new MenuItem("Exit");
        exitItem.setOnAction(e -> primaryStage.close());
        
        fileMenu.getItems().addAll(openItem, saveItem, new SeparatorMenuItem(), exitItem);
        
        // Movie Menu
        Menu movieMenu = new Menu("Movie");
        movieMenu.setStyle("-fx-text-fill: white;");
        
        MenuItem addMovieItem = new MenuItem("Add Movie");
        addMovieItem.setOnAction(e -> showAddMovieDialog());
        
        MenuItem updateMovieItem = new MenuItem("Update Movie");
        updateMovieItem.setOnAction(e -> showUpdateMovieDialog());
        
        MenuItem deleteMovieItem = new MenuItem("Delete Movie");
        deleteMovieItem.setOnAction(e -> showDeleteMovieDialog());
        
        MenuItem searchMovieItem = new MenuItem("Search Movie");
        searchMovieItem.setOnAction(e -> showSearchMovieDialog());
        
        MenuItem printSortedItem = new MenuItem("Print Sorted");
        printSortedItem.setOnAction(e -> showPrintSortedDialog());
        
        MenuItem printRankedItem = new MenuItem("Print Top and Least Ranked Movies");
        printRankedItem.setOnAction(e -> showRankedMoviesDialog());
        
        MenuItem averageHeightItem = new MenuItem("Show Average Tree Height");
        averageHeightItem.setOnAction(e -> showAverageHeightDialog());
        
        movieMenu.getItems().addAll(addMovieItem, updateMovieItem, deleteMovieItem, 
                                   new SeparatorMenuItem(), searchMovieItem, 
                                   new SeparatorMenuItem(), printSortedItem, printRankedItem,
                                   new SeparatorMenuItem(), averageHeightItem);
        
        menuBar.getMenus().addAll(fileMenu, movieMenu);
        return menuBar;
    }
    
    private VBox createCenterContent() {
        VBox centerContent = new VBox(20);
        centerContent.setPadding(new Insets(20));
        centerContent.setAlignment(Pos.CENTER);
        
        // Title
        Label titleLabel = new Label("Movie Catalog Management System");
        titleLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: #6a4c93;");
        
        // Create table
        movieTable = createMovieTable();
        
        // Create button panel
        HBox buttonPanel = createButtonPanel();
        
        centerContent.getChildren().addAll(titleLabel, movieTable, buttonPanel);
        return centerContent;
    }
    
    private TableView<Movie> createMovieTable() {
        TableView<Movie> table = new TableView<>();
        table.setStyle("-fx-border-color: #6a4c93; -fx-border-width: 2px;");
        
        // Title column
        TableColumn<Movie, String> titleCol = new TableColumn<>("Title");
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        titleCol.setPrefWidth(200);
        
        // Description column
        TableColumn<Movie, String> descCol = new TableColumn<>("Description");
        descCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        descCol.setPrefWidth(300);
        
        // Release Year column
        TableColumn<Movie, Integer> yearCol = new TableColumn<>("Release Year");
        yearCol.setCellValueFactory(new PropertyValueFactory<>("releaseYear"));
        yearCol.setPrefWidth(120);
        
        // Rating column
        TableColumn<Movie, Double> ratingCol = new TableColumn<>("Rating");
        ratingCol.setCellValueFactory(new PropertyValueFactory<>("rating"));
        ratingCol.setPrefWidth(100);
        
        table.getColumns().addAll(titleCol, descCol, yearCol, ratingCol);
        table.setItems(movieData);
        table.setPrefHeight(400);
        
        return table;
    }
    
    private HBox createStatusBar() {
        HBox statusBar = new HBox(20);
        statusBar.setPadding(new Insets(10));
        statusBar.setStyle("-fx-background-color: #6a4c93;");
        statusBar.setAlignment(Pos.CENTER_LEFT);
        
        statusLabel = new Label("Ready");
        statusLabel.setStyle("-fx-text-fill: white; -fx-font-weight: bold;");
        
        hashIndexLabel = new Label("Hash Index: N/A");
        hashIndexLabel.setStyle("-fx-text-fill: white;");
        
        treeHeightLabel = new Label("Tree Height: N/A");
        treeHeightLabel.setStyle("-fx-text-fill: white;");
        
        statusBar.getChildren().addAll(statusLabel, hashIndexLabel, treeHeightLabel);
        return statusBar;
    }
    
    private void openFile(Stage primaryStage) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Movie File");
        fileChooser.getExtensionFilters().add(
            new FileChooser.ExtensionFilter("Text Files", "*.txt")
        );
        
        File file = fileChooser.showOpenDialog(primaryStage);
        if (file != null) {
            catalog.loadMoviesFromFile(file.getAbsolutePath());
            refreshTable();
            statusLabel.setText("File loaded: " + file.getName());
        }
    }
    
    private void saveFile(Stage primaryStage) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Movie File");
        fileChooser.getExtensionFilters().add(
            new FileChooser.ExtensionFilter("Text Files", "*.txt")
        );
        fileChooser.setInitialFileName("movies.txt");
        
        File file = fileChooser.showSaveDialog(primaryStage);
        if (file != null) {
            catalog.saveMoviesToFile(file.getAbsolutePath());
            statusLabel.setText("File saved: " + file.getName());
        }
    }
    
    private void showAddMovieDialog() {
        Dialog<Movie> dialog = new Dialog<>();
        dialog.setTitle("Add Movie");
        dialog.setHeaderText("Enter movie details");
        
        // Create dialog content
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20));
        grid.setStyle("-fx-background-color: #f8f4ff;");
        
        TextField titleField = new TextField();
        titleField.setPromptText("Movie Title");
        titleField.setStyle("-fx-border-color: #6a4c93;");
        
        TextArea descField = new TextArea();
        descField.setPromptText("Movie Description");
        descField.setPrefRowCount(3);
        descField.setStyle("-fx-border-color: #6a4c93;");
        
        DatePicker yearPicker = new DatePicker();
        yearPicker.setValue(LocalDate.now());
        yearPicker.setPromptText("Select Release Date");
        yearPicker.setStyle("-fx-border-color: #6a4c93;");
        
        Spinner<Double> ratingSpinner = new Spinner<>(0.0, 10.0, 5.0, 0.1);
        ratingSpinner.setStyle("-fx-border-color: #6a4c93;");
        
        grid.add(new Label("Title:"), 0, 0);
        grid.add(titleField, 1, 0);
        grid.add(new Label("Description:"), 0, 1);
        grid.add(descField, 1, 1);
        grid.add(new Label("Release Date:"), 0, 2);
        grid.add(yearPicker, 1, 2);
        grid.add(new Label("Rating:"), 0, 3);
        grid.add(ratingSpinner, 1, 3);
        
        dialog.getDialogPane().setContent(grid);
        
        ButtonType addButtonType = new ButtonType("Add", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(addButtonType, ButtonType.CANCEL);
        
        // Style buttons
        Button addButton = (Button) dialog.getDialogPane().lookupButton(addButtonType);
        addButton.setStyle("-fx-background-color: #6a4c93; -fx-text-fill: white;");
        
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == addButtonType) {
                int releaseYear = yearPicker.getValue() != null ? yearPicker.getValue().getYear() : Year.now().getValue();
                return new Movie(titleField.getText(), descField.getText(), 
                               releaseYear, ratingSpinner.getValue());
            }
            return null;
        });
        
        dialog.showAndWait().ifPresent(movie -> {
            catalog.add(movie);
            refreshTable();
            statusLabel.setText("Movie added: " + movie.getTitle());
        });
    }
    
    private void showUpdateMovieDialog() {
        Movie selectedMovie = movieTable.getSelectionModel().getSelectedItem();
        if (selectedMovie == null) {
            showAlert("No Selection", "Please select a movie to update.");
            return;
        }
        
        Dialog<Movie> dialog = new Dialog<>();
        dialog.setTitle("Update Movie");
        dialog.setHeaderText("Update movie details");
        
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20));
        grid.setStyle("-fx-background-color: #f8f4ff;");
        
        TextField titleField = new TextField(selectedMovie.getTitle());
        titleField.setStyle("-fx-border-color: #6a4c93;");
        
        TextArea descField = new TextArea(selectedMovie.getDescription());
        descField.setPrefRowCount(3);
        descField.setStyle("-fx-border-color: #6a4c93;");
        
        DatePicker yearPicker = new DatePicker();
        yearPicker.setValue(LocalDate.of(selectedMovie.getReleaseYear(), 1, 1));
        yearPicker.setStyle("-fx-border-color: #6a4c93;");
        
        Spinner<Double> ratingSpinner = new Spinner<>(0.0, 10.0, selectedMovie.getRating(), 0.1);
        ratingSpinner.setStyle("-fx-border-color: #6a4c93;");
        
        grid.add(new Label("Title:"), 0, 0);
        grid.add(titleField, 1, 0);
        grid.add(new Label("Description:"), 0, 1);
        grid.add(descField, 1, 1);
        grid.add(new Label("Release Date:"), 0, 2);
        grid.add(yearPicker, 1, 2);
        grid.add(new Label("Rating:"), 0, 3);
        grid.add(ratingSpinner, 1, 3);
        
        dialog.getDialogPane().setContent(grid);
        
        ButtonType updateButtonType = new ButtonType("Update", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(updateButtonType, ButtonType.CANCEL);
        
        Button updateButton = (Button) dialog.getDialogPane().lookupButton(updateButtonType);
        updateButton.setStyle("-fx-background-color: #6a4c93; -fx-text-fill: white;");
        
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == updateButtonType) {
                int releaseYear = yearPicker.getValue() != null ? yearPicker.getValue().getYear() : selectedMovie.getReleaseYear();
                return new Movie(titleField.getText(), descField.getText(), 
                               releaseYear, ratingSpinner.getValue());
            }
            return null;
        });
        
        dialog.showAndWait().ifPresent(movie -> {
            catalog.erase(selectedMovie.getTitle());
            catalog.add(movie);
            refreshTable();
            statusLabel.setText("Movie updated: " + movie.getTitle());
        });
    }
    
    private void showDeleteMovieDialog() {
        Movie selectedMovie = movieTable.getSelectionModel().getSelectedItem();
        if (selectedMovie == null) {
            showAlert("No Selection", "Please select a movie to delete.");
            return;
        }
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete Movie");
        alert.setHeaderText("Delete Movie Confirmation");
        alert.setContentText("Are you sure you want to delete: " + selectedMovie.getTitle() + "?");
        
        // Style the alert
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.setStyle("-fx-background-color: #f8f4ff;");
        
        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                catalog.erase(selectedMovie.getTitle());
                refreshTable();
                statusLabel.setText("Movie deleted: " + selectedMovie.getTitle());
            }
        });
    }
    
    private void showSearchMovieDialog() {
        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle("Search Movie");
        dialog.setHeaderText("Search for movies");
        
        VBox content = new VBox(10);
        content.setPadding(new Insets(20));
        content.setStyle("-fx-background-color: #f8f4ff;");
        
        RadioButton titleRadio = new RadioButton("Search by Title");
        RadioButton yearRadio = new RadioButton("Search by Year");
        ToggleGroup group = new ToggleGroup();
        titleRadio.setToggleGroup(group);
        yearRadio.setToggleGroup(group);
        titleRadio.setSelected(true);
        
        TextField searchField = new TextField();
        searchField.setPromptText("Enter search term");
        searchField.setStyle("-fx-border-color: #6a4c93;");
        
        content.getChildren().addAll(titleRadio, yearRadio, searchField);
        dialog.getDialogPane().setContent(content);
        
        ButtonType searchButtonType = new ButtonType("Search", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(searchButtonType, ButtonType.CANCEL);
        
        Button searchButton = (Button) dialog.getDialogPane().lookupButton(searchButtonType);
        searchButton.setStyle("-fx-background-color: #6a4c93; -fx-text-fill: white;");
        
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == searchButtonType) {
                return searchField.getText();
            }
            return null;
        });
        
        dialog.showAndWait().ifPresent(searchTerm -> {
            if (!searchTerm.trim().isEmpty()) {
                Movie[] results;
                if (titleRadio.isSelected()) {
                    results = catalog.searchByTitle(searchTerm);
                } else {
                    try {
                        int year = Integer.parseInt(searchTerm);
                        results = catalog.searchByYear(year);
                    } catch (NumberFormatException e) {
                        showAlert("Invalid Input", "Please enter a valid year.");
                        return;
                    }
                }
                
                movieData.clear();
                for (Movie movie : results) {
                    if (movie != null) {
                        movieData.add(movie);
                    }
                }
                statusLabel.setText("Search completed. Found " + results.length + " results.");
            }
        });
    }
    
    private void showPrintSortedDialog() {
        Dialog<Void> dialog = new Dialog<>();
        dialog.setTitle("Print Sorted Movies");
        dialog.setHeaderText("Navigate through hash table");
        
        VBox content = new VBox(10);
        content.setPadding(new Insets(20));
        content.setStyle("-fx-background-color: #f8f4ff;");
        
        ComboBox<String> sortOrder = new ComboBox<>();
        sortOrder.getItems().addAll("Ascending", "Descending");
        sortOrder.setValue("Ascending");
        sortOrder.setStyle("-fx-border-color: #6a4c93;");
        
        TextArea displayArea = new TextArea();
        displayArea.setPrefRowCount(10);
        displayArea.setEditable(false);
        displayArea.setStyle("-fx-border-color: #6a4c93;");
        
        HBox buttonBox = new HBox(10);
        Button prevButton = new Button("Previous");
        Button nextButton = new Button("Next");
        prevButton.setStyle("-fx-background-color: #6a4c93; -fx-text-fill: white;");
        nextButton.setStyle("-fx-background-color: #6a4c93; -fx-text-fill: white;");
        
        buttonBox.getChildren().addAll(prevButton, nextButton);
        buttonBox.setAlignment(Pos.CENTER);
        
        content.getChildren().addAll(new Label("Sort Order:"), sortOrder, displayArea, buttonBox);
        
        // Get non-empty hash cell indices
        int[] nonEmptyIndices = getNonEmptyHashIndices();
        int[] currentIndexWrapper = {0}; // Use array to allow modification in lambda
        
        prevButton.setOnAction(e -> {
            if (currentIndexWrapper[0] > 0) {
                currentIndexWrapper[0]--;
                updateDisplayAreaForNonEmpty(displayArea, sortOrder.getValue().equals("Ascending"), 
                                           nonEmptyIndices[currentIndexWrapper[0]], 
                                           currentIndexWrapper[0] + 1, nonEmptyIndices.length);
            }
        });
        
        nextButton.setOnAction(e -> {
            if (currentIndexWrapper[0] < nonEmptyIndices.length - 1) {
                currentIndexWrapper[0]++;
                updateDisplayAreaForNonEmpty(displayArea, sortOrder.getValue().equals("Ascending"), 
                                           nonEmptyIndices[currentIndexWrapper[0]], 
                                           currentIndexWrapper[0] + 1, nonEmptyIndices.length);
            }
        });
        
        // Initialize display with first non-empty cell
        if (nonEmptyIndices.length > 0) {
            updateDisplayAreaForNonEmpty(displayArea, true, nonEmptyIndices[0], 1, nonEmptyIndices.length);
        } else {
            displayArea.setText("No movies found in any hash cell.");
            prevButton.setDisable(true);
            nextButton.setDisable(true);
        }
        
        dialog.getDialogPane().setContent(content);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);
        
        dialog.showAndWait();
    }
    
    private HBox createButtonPanel() {
        HBox buttonPanel = new HBox(15);
        buttonPanel.setAlignment(Pos.CENTER);
        buttonPanel.setPadding(new Insets(10));
        
        Button addButton = new Button("Add Movie");
        Button updateButton = new Button("Update Movie");
        Button deleteButton = new Button("Delete Movie");
        Button searchButton = new Button("Search Movie");
        Button refreshButton = new Button("Refresh Table");
        Button averageHeightButton = new Button("Average Height");
        
        // Style buttons
        String buttonStyle = "-fx-background-color: #6a4c93; -fx-text-fill: white; -fx-padding: 10 20; -fx-font-size: 12px;";
        addButton.setStyle(buttonStyle);
        updateButton.setStyle(buttonStyle);
        deleteButton.setStyle(buttonStyle);
        searchButton.setStyle(buttonStyle);
        refreshButton.setStyle(buttonStyle);
        averageHeightButton.setStyle(buttonStyle);
        
        // Set button actions
        addButton.setOnAction(e -> showAddMovieDialog());
        updateButton.setOnAction(e -> showUpdateMovieDialog());
        deleteButton.setOnAction(e -> showDeleteMovieDialog());
        searchButton.setOnAction(e -> showSearchMovieDialog());
        refreshButton.setOnAction(e -> refreshTableToMain());
        averageHeightButton.setOnAction(e -> showAverageHeightDialog());
        
        buttonPanel.getChildren().addAll(addButton, updateButton, deleteButton, searchButton, refreshButton, averageHeightButton);
        return buttonPanel;
    }
    
    private void showAverageHeightDialog() {
        double averageHeight = catalog.getAverageTreeHeight();
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Average Tree Height");
        alert.setHeaderText("Hash Table Statistics");
        alert.setContentText("Average Tree Height: " + String.format("%.2f", averageHeight));
        
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.setStyle("-fx-background-color: #f8f4ff;");
        
        alert.showAndWait();
        statusLabel.setText("Average tree height: " + String.format("%.2f", averageHeight));
    }
    
    private void refreshTableToMain() {
        refreshTable();
        statusLabel.setText("Table refreshed - showing all movies");
    }
    
    private int[] getNonEmptyHashIndices() {
        // First count non-empty cells
        int count = 0;
        for (int i = 0; i < catalog.getTableSize(); i++) {
            if (catalog.getMoviesAtIndex(i).length > 0) {
                count++;
            }
        }
        
        // Create array of non-empty indices
        int[] nonEmptyIndices = new int[count];
        int index = 0;
        for (int i = 0; i < catalog.getTableSize(); i++) {
            if (catalog.getMoviesAtIndex(i).length > 0) {
                nonEmptyIndices[index++] = i;
            }
        }
        
        return nonEmptyIndices;
    }
    
    private void updateDisplayAreaForNonEmpty(TextArea displayArea, boolean ascending, int hashIndex, int currentPos, int totalNonEmpty) {
        Movie[] moviesAtIndex = catalog.getMoviesAtIndex(hashIndex);
        int height = catalog.getHeightAtIndex(hashIndex);
        
        String content = "Hash Index: " + hashIndex + " (" + currentPos + " of " + totalNonEmpty + " non-empty cells)\n";
        content += "Tree Height: " + height + "\n";
        content += "Movies in this cell:\n\n";
        
        for (Movie movie : moviesAtIndex) {
            if (movie != null) {
                content += "Title: " + movie.getTitle() + "\n";
                content += "Description: " + movie.getDescription() + "\n";
                content += "Year: " + movie.getReleaseYear() + "\n";
                content += "Rating: " + movie.getRating() + "\n\n";
            }
        }
        
        displayArea.setText(content);
        hashIndexLabel.setText("Hash Index: " + hashIndex);
        treeHeightLabel.setText("Tree Height: " + height);
    }
    
    private void updateDisplayArea(TextArea displayArea, boolean ascending) {
        Movie[] moviesAtIndex = catalog.getMoviesAtIndex(currentIndex);
        int height = catalog.getHeightAtIndex(currentIndex);
        
        String content = "Hash Index: " + currentIndex + "\n";
        content += "Tree Height: " + height + "\n";
        content += "Movies in this cell:\n\n";
        
        if (moviesAtIndex.length == 0) {
            content += "No movies in this hash cell.";
        } else {
            for (Movie movie : moviesAtIndex) {
                if (movie != null) {
                    content += "Title: " + movie.getTitle() + "\n";
                    content += "Description: " + movie.getDescription() + "\n";
                    content += "Year: " + movie.getReleaseYear() + "\n";
                    content += "Rating: " + movie.getRating() + "\n\n";
                }
            }
        }
        
        displayArea.setText(content);
        hashIndexLabel.setText("Hash Index: " + currentIndex);
        treeHeightLabel.setText("Tree Height: " + height);
    }
    
    private void showRankedMoviesDialog() {
        Dialog<Void> dialog = new Dialog<>();
        dialog.setTitle("Top and Least Ranked Movies");
        dialog.setHeaderText("Movies ranked by rating in each hash cell");
        
        TextArea displayArea = new TextArea();
        displayArea.setPrefRowCount(15);
        displayArea.setEditable(false);
        displayArea.setStyle("-fx-border-color: #6a4c93; -fx-background-color: #f8f4ff;");
        
        String content = "";
        int cellsWithMovies = 0;
        
        for (int i = 0; i < catalog.getTableSize(); i++) {
            Movie topRated = catalog.getTopRatedAtIndex(i);
            Movie leastRated = catalog.getLeastRatedAtIndex(i);
            
            // Only show cells that have movies
            if (topRated != null || leastRated != null) {
                cellsWithMovies++;
                content += "Hash Index " + i + ":\n";
                
                if (topRated != null) {
                    content += "  Top Rated: " + topRated.getTitle() + " (" + topRated.getRating() + " stars)\n";
                } else {
                    content += "  Top Rated: No movies\n";
                }
                
                if (leastRated != null) {
                    content += "  Least Rated: " + leastRated.getTitle() + " (" + leastRated.getRating() + " stars)\n";
                } else {
                    content += "  Least Rated: No movies\n";
                }
                content += "\n";
            }
        }
        
        if (cellsWithMovies == 0) {
            content = "No movies found in any hash cell.";
        } else {
            content = "Showing " + cellsWithMovies + " hash cells with movies:\n\n" + content;
        }
        
        displayArea.setText(content);
        
        VBox contentBox = new VBox(10);
        contentBox.setPadding(new Insets(20));
        contentBox.getChildren().add(displayArea);
        
        dialog.getDialogPane().setContent(contentBox);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);
        
        dialog.showAndWait();
    }
    
    private void refreshTable() {
        movieData.clear();
        Movie[] allMovies = catalog.getAllMovies();
        for (Movie movie : allMovies) {
            if (movie != null) {
                movieData.add(movie);
            }
        }
    }
    
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.setStyle("-fx-background-color: #f8f4ff;");
        
        alert.showAndWait();
    }
}