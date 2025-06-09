package application;

public class Movie implements Comparable<Movie>{
	private String title;
	private String description;
	private int releaseYear;
	private double rating;
	
	public Movie(String title, String description , int releaseYear , double rating) {
		this.title = title;
		this.description = description;
		this.releaseYear = releaseYear;
		this.rating = rating;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getReleaseYear() {
		return releaseYear;
	}

	public void setReleaseYear(int releaseYear) {
		this.releaseYear = releaseYear;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		if(rating >=0.0 && rating <=10.0)
			this.rating = rating;
		
	}

	@Override
	public String toString() {
		return "Movie [title=" + title + ", description=" + description + ", releaseYear=" + releaseYear + ", rating="
				+ rating + "]";
	}
	
	public boolean equals(Object o) {
		if(this == o)
			return true;
		if(o == null || this.getClass()!=o.getClass())
			return false;
		Movie movie = (Movie)o;
		if (title != null) {
		    return title.equals(movie.title);
		} else {
		    return movie.title == null;
		}
		
	}

	@Override
	 public int compareTo(Movie o) {
        if (o == null) {
            return 1;
        }
        if (this.title == null && o.title == null) {
            return 0;
        }
        if (this.title == null) {
            return -1;
        }
        if (o.title == null) {
            return 1;
        }
        return this.title.compareToIgnoreCase(o.title);
    }
	
	  public int hashCode() {
	        if (title != null) {
	            return title.hashCode();
	        } else {
	            return 0;
	        }
	    }

}
