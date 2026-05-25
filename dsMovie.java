import java.util.ArrayList;

public class dsMovie<T extends Movie> {
    private ArrayList<T> movieList;

    public dsMovie() {
        this.movieList = new ArrayList<>();
    }

    public void addMovie(T movie) {
        movieList.add(movie);
        System.out.println("Phim đã được thêm thành công.");
    }

    public boolean deleteMovie(int id) {
        for (T movie : movieList) {
            if (movie.getId() == id) {
                movieList.remove(movie);
                return true;
            }
        }
        return false;
    }

    public T findById(int id) {
        for (T movie : movieList) {
            if (movie.getId() == id) {
                return movie;
            }
        }
        return null;
    }

    public void displayAllMovies() {
        if (movieList.isEmpty()) {
            System.out.println("Danh sách phim trống!");
            return;
        }
        System.out.println("Danh sách phim:");
        for (T movie : movieList) {
            System.out.println(movie);
        }
    }

    public void searchByName(String title) {
        boolean found = false;
        for (T movie : movieList) {
            if (movie.getTitle().toLowerCase().contains(title.toLowerCase())) {
                System.out.println("Phim tìm thấy: " + movie);
                found = true;
            }
        }
        if (!found) {
            System.out.println("Không tìm thấy phim");
        }
    }

    public void filterByRating(double minRating) {
        System.out.println("Phim có rating lớn hơn " + minRating + ":");
        boolean found = false;
        for (T movie : movieList) {
            if (movie.getRating() > minRating) {
                System.out.println(movie);
                found = true;
            }
        }
        if (!found) {
            System.out.println("(Không có phim nào thỏa mãn)");
        }
    }
}