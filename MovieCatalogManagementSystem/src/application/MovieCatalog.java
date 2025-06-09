package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


public class MovieCatalog {
	private AVLTree<Movie>[] hashTable;
	private int tableSize;
	private int movieCount;

	public MovieCatalog() {
		allocate(5);

	}

	private void allocate(int size) {
		tableSize = size;
		hashTable = new AVLTree[tableSize];
		for (int i = 0; i < tableSize; i++) {
			hashTable[i] = new AVLTree<>();
		}
		movieCount = 0;
	}

	private boolean isPrime(int num) {
		if (num < 2)
			return false;
		if (num == 2)
			return true;
		if (num % 2 == 0)
			return false;

		for (int i = 3; i * i <= num; i += 2) {
			if (num % i == 0) {
				return false;
			}
		}
		return true;
	}

	private int getNextPrime(int num) {
		while (!isPrime(num)) {
			num++;
		}
		return num;
	}

	private boolean needsRehashing() {
		return movieCount >= tableSize / 2;
	}

	private int hash(String title) {
		if (title == null) {
			return 0;
		}
		int hash = title.hashCode() % tableSize;
		if (hash < 0) {
			hash += tableSize;
		}
		return hash;
	}

	private void rehash() {
		AVLTree<Movie>[] oldTable = hashTable;
		int oldSize = tableSize;

		allocate(getNextPrime(oldSize * 2));

		for (int i = 0; i < oldSize; i++) {
			if (!oldTable[i].isEmpty()) {
				Movie[] movies = oldTable[i].getAllMoviesSorted(true);
				for (int j = 0; j < movies.length; j++) {
					if (movies[j] != null) {
						int newIndex = hash(movies[j].getTitle());
						hashTable[newIndex].insert(movies[j]);
					}
				}
			}
		}
	}

	public void add(Movie movie) {
		if (movie == null || movie.getTitle() == null) {
			return;
		}
		if (needsRehashing()) {
			rehash();
		}

		int index = hash(movie.getTitle());
		Movie exists = hashTable[index].search(movie.getTitle());

		if (exists == null) {
			hashTable[index].insert(movie);
			movieCount++;
		} else {
			hashTable[index].insert(movie);
		}

	}

	public Movie get(String title) {
		if (title == null) {
			return null;
		}

		int index = hash(title);
		return hashTable[index].search(title);
	}

	public boolean erase(String title) {
		if (title == null) {
			return false;
		}
		int index = hash(title);
		Movie deleted = hashTable[index].delete(title);

		if (deleted != null) {
			movieCount--;
			return true;
		}
		return false;

	}

	public void saveMoviesToFile(String filename) {
		try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
			boolean first = true;
			for (int i = 0; i < tableSize; i++) {
				if (!hashTable[i].isEmpty()) {
					Movie[] movies = hashTable[i].getAllMoviesSorted(true);
					for (int j = 0; j < movies.length; j++) {
						if (movies[j] != null) {
							if (!first) {
								writer.println(); // Blank line between movies
							}
							writer.println("Title: " + movies[j].getTitle());
							writer.println("Description: " + movies[j].getDescription());
							writer.println("Release Year: " + movies[j].getReleaseYear());
							writer.println("Rating: " + movies[j].getRating());
							first = false;
						}
					}
				}
			}
		} catch (IOException e) {
			System.err.println("Error saving movies to file: " + e.getMessage());
		}
	}

	public void loadMoviesFromFile(String filename) {
		try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
			String line;
			String title = null, description = null;
			int releaseYear = 0;
			double rating = 0.0;

			while ((line = reader.readLine()) != null) {
				line = line.trim();

				if (line.isEmpty()) {
					continue;
				}

				if (line.startsWith("Title: ")) {
					title = line.substring(7);
				} else if (line.startsWith("Description: ")) {
					description = line.substring(13);
				} else if (line.startsWith("Release Year: ")) {
					try {
						releaseYear = Integer.parseInt(line.substring(14));
					} catch (NumberFormatException e) {
						releaseYear = 0;
					}
				} else if (line.startsWith("Rating: ")) {
					try {
						rating = Double.parseDouble(line.substring(8));

						// Create movie when we have all data
						if (title != null && description != null) {
							add(new Movie(title, description, releaseYear, rating));
							title = null;
							description = null;
							releaseYear = 0;
							rating = 0.0;
						}
					} catch (NumberFormatException e) {
						rating = 0.0;
					}
				}
			}
		} catch (IOException e) {
			System.err.println("Error loading movies from file: " + e.getMessage());
		}
	}

	public Movie[] getAllMovies() {
		// First count total movies
		int totalCount = 0;
		for (int i = 0; i < tableSize; i++) {
			if (!hashTable[i].isEmpty()) {
				totalCount += hashTable[i].getSize();
			}
		}

		// Create array and fill it
		Movie[] allMovies = new Movie[totalCount];
		int index = 0;
		for (int i = 0; i < tableSize; i++) {
			if (!hashTable[i].isEmpty()) {
				Movie[] treeMovies = hashTable[i].getAllMoviesSorted(true);
				for (int j = 0; j < treeMovies.length; j++) {
					if (treeMovies[j] != null) {
						allMovies[index++] = treeMovies[j];
					}
				}
			}
		}
		return allMovies;
	}

	public Movie[] getMoviesAtIndex(int index) {
		if (index < 0 || index >= tableSize) {
			return new Movie[0];
		}
		return hashTable[index].getAllMoviesSorted(true);
	}

	public int getHeightAtIndex(int index) {
		if (index < 0 || index >= tableSize) {
			return 0;
		}
		return hashTable[index].getTreeHeight();
	}

	public Movie getTopRatedAtIndex(int index) {
		if (index < 0 || index >= tableSize || hashTable[index].isEmpty()) {
			return null;
		}
		return hashTable[index].getHighestRatedMovie();
	}

	public Movie getLeastRatedAtIndex(int index) {
		if (index < 0 || index >= tableSize || hashTable[index].isEmpty()) {
			return null;
		}
		return hashTable[index].getLowestRatedMovie();
	}

	public Movie[] searchByTitle(String title) {
		if (title == null || title.trim().isEmpty()) {
			return new Movie[0];
		}

		String searchTerm = title.toLowerCase();
		Movie[] tempResults = new Movie[movieCount]; // Maximum possible matches
		int resultCount = 0;

		for (int i = 0; i < tableSize; i++) {
			if (!hashTable[i].isEmpty()) {
				Movie[] movies = hashTable[i].getAllMoviesSorted(true);
				for (int j = 0; j < movies.length; j++) {
					if (movies[j] != null && movies[j].getTitle().toLowerCase().contains(searchTerm)) {
						tempResults[resultCount++] = movies[j];
					}
				}
			}
		}

		// Create final array with exact size
		Movie[] results = new Movie[resultCount];
		for (int i = 0; i < resultCount; i++) {
			results[i] = tempResults[i];
		}
		return results;
	}

	public Movie[] searchByYear(int year) {
		Movie[] tempResults = new Movie[movieCount]; // Maximum possible matches
		int resultCount = 0;

		for (int i = 0; i < tableSize; i++) {
			if (!hashTable[i].isEmpty()) {
				Movie[] movies = hashTable[i].getAllMoviesSorted(true);
				for (int j = 0; j < movies.length; j++) {
					if (movies[j] != null && movies[j].getReleaseYear() == year) {
						tempResults[resultCount++] = movies[j];
					}
				}
			}
		}

		// Create final array with exact size
		Movie[] results = new Movie[resultCount];
		for (int i = 0; i < resultCount; i++) {
			results[i] = tempResults[i];
		}
		return results;
	}

	public Movie[] getAllMoviesSorted(boolean ascending) {
		Movie[] allMovies = new Movie[movieCount];
		int index = 0;

		for (int i = 0; i < tableSize; i++) {
			if (!hashTable[i].isEmpty()) {
				Movie[] treeMovies = hashTable[i].getAllMoviesSorted(ascending);
				for (int j = 0; j < treeMovies.length; j++) {
					if (treeMovies[j] != null) {
						allMovies[index++] = treeMovies[j];
					}
				}
			}
		}

		// Sort the combined array by title
		sortMoviesByTitle(allMovies, ascending);
		return allMovies;
	}

	private void sortMoviesByTitle(Movie[] movies, boolean ascending) {
		// Simple bubble sort by title
		for (int i = 0; i < movies.length - 1; i++) {
			for (int j = 0; j < movies.length - i - 1; j++) {
				if (movies[j] != null && movies[j + 1] != null) {
					int comparison = movies[j].getTitle().compareToIgnoreCase(movies[j + 1].getTitle());
					if ((ascending && comparison > 0) || (!ascending && comparison < 0)) {
						Movie temp = movies[j];
						movies[j] = movies[j + 1];
						movies[j + 1] = temp;
					}
				}
			}
		}
	}

	public double getAverageTreeHeight() {
		if (tableSize == 0)
			return 0;

		int totalHeight = 0;
		int nonEmptyTrees = 0;

		for (int i = 0; i < tableSize; i++) {
			if (!hashTable[i].isEmpty()) {
				totalHeight += hashTable[i].getTreeHeight();
				nonEmptyTrees++;
			}
		}

		return nonEmptyTrees > 0 ? (double) totalHeight / nonEmptyTrees : 0;
	}

	public void deallocate() {
		if (hashTable != null) {
			for (int i = 0; i < tableSize; i++) {
				if (hashTable[i] != null) {
					hashTable[i].clear();
				}
			}
		}
		hashTable = null;
		tableSize = 0;
		movieCount = 0;
	}

	public int getTableSize() {
		return tableSize;
	}

	public int getMovieCount() {
		return movieCount;
	}

}
