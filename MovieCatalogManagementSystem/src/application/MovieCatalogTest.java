package application;

public class MovieCatalogTest {
    public static void main(String[] args) {
        // Create a movie catalog
        MovieCatalog catalog = new MovieCatalog();
        
        // Test adding movies
        System.out.println("Testing Movie Catalog System");
        System.out.println("===============================");
        
        System.out.println("Initial table size: " + catalog.getTableSize()); // Should be 5
        
        // Add movies one by one to demonstrate rehashing
        System.out.println("\nAdding movies to demonstrate rehashing:");
        
        catalog.add(new Movie("The Matrix", "A computer programmer discovers reality is a simulation", 1999, 8.7));
        System.out.println("Added 1st movie - Table size: " + catalog.getTableSize() + ", Movie count: " + catalog.getMovieCount());
        
        catalog.add(new Movie("Inception", "A thief who steals through dream-sharing technology", 2010, 8.7));
        System.out.println("Added 2nd movie - Table size: " + catalog.getTableSize() + ", Movie count: " + catalog.getMovieCount());
        
        catalog.add(new Movie("The Godfather", "The aging patriarch of an organized crime dynasty", 1972, 9.2));
        System.out.println("Added 3rd movie - Table size: " + catalog.getTableSize() + ", Movie count: " + catalog.getMovieCount());
        System.out.println("Note: Table should have resized from 5 to 11 when 3rd movie was added (half of 5 is 2.5, so at 3 movies it rehashes)");
        
        catalog.add(new Movie("Pulp Fiction", "The lives of two mob hitmen intertwine", 1994, 8.9));
        System.out.println("Added 4th movie - Table size: " + catalog.getTableSize() + ", Movie count: " + catalog.getMovieCount());
        
        catalog.add(new Movie("Forrest Gump", "The story of an Alabama man through historical events", 1994, 8.8));
        System.out.println("Added 5th movie - Table size: " + catalog.getTableSize() + ", Movie count: " + catalog.getMovieCount());
        
        // Add one more to trigger another resize (11/2 = 5.5, so at 6 movies it should rehash)
        catalog.add(new Movie("The Dark Knight", "Batman faces the Joker", 2008, 9.0));
        System.out.println("Added 6th movie - Table size: " + catalog.getTableSize() + ", Movie count: " + catalog.getMovieCount());
        System.out.println("Note: Table should have resized from 11 to 23 when 6th movie was added (half of 11 is 5.5, so at 6 movies it rehashes)");
        
        System.out.println("\nRehashing demonstration:");
        System.out.println("Original size: 5 -> Rehash at 3 movies -> New size: 11 (first prime after 5*2=10)");
        System.out.println("Size 11 -> Rehash at 6 movies -> New size: 23 (first prime after 11*2=22)");
        System.out.println();
        
        // Test retrieval
        System.out.println("Testing movie retrieval:");
        Movie matrix = catalog.get("The Matrix");
        if (matrix != null) {
            System.out.println("Found: " + matrix.toString());
        }
        System.out.println();
        
        // Test search by title
        System.out.println("Testing search by title 'The':");
        Movie[] searchResults = catalog.searchByTitle("The");
        for (Movie movie : searchResults) {
            if (movie != null) {
                System.out.println("  " + movie.getTitle() + " (" + movie.getRating() + ")");
            }
        }
        System.out.println();
        
        // Test search by year
        System.out.println("Testing search by year 1994:");
        Movie[] yearResults = catalog.searchByYear(1994);
        for (Movie movie : yearResults) {
            if (movie != null) {
                System.out.println("  " + movie.getTitle() + " (" + movie.getRating() + ")");
            }
        }
        System.out.println();
        
        // Test hash table navigation
        System.out.println("Testing hash table structure:");
        for (int i = 0; i < catalog.getTableSize(); i++) {
            Movie[] moviesAtIndex = catalog.getMoviesAtIndex(i);
            if (moviesAtIndex.length > 0) {
                System.out.println("Hash index " + i + " (height: " + catalog.getHeightAtIndex(i) + "):");
                for (Movie movie : moviesAtIndex) {
                    if (movie != null) {
                        System.out.println("  " + movie.getTitle());
                    }
                }
            }
        }
        System.out.println();
        
        // Test top and least rated
        System.out.println("Testing top and least rated movies by hash cell:");
        for (int i = 0; i < catalog.getTableSize(); i++) {
            Movie[] moviesAtIndex = catalog.getMoviesAtIndex(i);
            if (moviesAtIndex.length > 0) {
                Movie topRated = catalog.getTopRatedAtIndex(i);
                Movie leastRated = catalog.getLeastRatedAtIndex(i);
                System.out.println("Hash cell " + i + ":");
                if (topRated != null) {
                    System.out.println("  Top: " + topRated.getTitle() + " (" + topRated.getRating() + ")");
                }
                if (leastRated != null) {
                    System.out.println("  Least: " + leastRated.getTitle() + " (" + leastRated.getRating() + ")");
                }
            }
        }
        System.out.println();
        
        // Test file operations
        System.out.println("Testing file save/load:");
        catalog.saveMoviesToFile("test_output.txt");
        System.out.println("Saved movies to test_output.txt");
        
        // Create new catalog and load
        MovieCatalog newCatalog = new MovieCatalog();
        newCatalog.loadMoviesFromFile("test_output.txt");
        System.out.println("Loaded movies into new catalog");
        System.out.println("New catalog movie count: " + newCatalog.getMovieCount());
        System.out.println("New catalog table size: " + newCatalog.getTableSize());
        
        // Test deletion
        System.out.println("\nTesting movie deletion:");
        boolean deleted = catalog.erase("The Matrix");
        System.out.println("Deleted 'The Matrix': " + deleted);
        System.out.println("New movie count: " + catalog.getMovieCount());
        
        // Test update
        System.out.println("\nTesting movie update:");
        catalog.add(new Movie("Inception", "Updated description: Dream within a dream", 2010, 9.0));
        Movie updated = catalog.get("Inception");
        if (updated != null) {
            System.out.println("Updated movie: " + updated.toString());
        }
        
        System.out.println("\nAll tests completed successfully!");
    }
}