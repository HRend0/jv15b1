import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        dsMovie<Movie> manager = new dsMovie<>();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        while (true) {
            System.out.println("\nChọn chức năng:");
            System.out.println("1. Thêm phim");
            System.out.println("2. Xóa phim");
            System.out.println("3. Sửa phim");
            System.out.println("4. Hiển thị phim");
            System.out.println("5. Tìm kiếm phim theo tên");
            System.out.println("6. Lọc phim theo rating");
            System.out.println("7. Thoát");

            int choice = 0;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Lỗi: Vui lòng chọn số chức năng từ 1 đến 7!");
                continue;
            }

            switch (choice) {
                case 1:
                    try {
                        System.out.print("Nhập ID phim: ");
                        int id = Integer.parseInt(scanner.nextLine());

                        System.out.print("Nhập tiêu đề phim: ");
                        String title = scanner.nextLine();

                        System.out.print("Nhập đạo diễn: ");
                        String director = scanner.nextLine();

                        System.out.print("Nhập ngày phát hành (dd-MM-yyyy): ");
                        LocalDate releaseDate = LocalDate.parse(scanner.nextLine(), dateFormatter);

                        System.out.print("Nhập rating: ");
                        double rating = Double.parseDouble(scanner.nextLine());

                        Movie newMovie = new Movie(id, title, director, releaseDate, rating);
                        manager.addMovie(newMovie);
                    } catch (NumberFormatException e) {
                        System.out.println("Lỗi: ID phải là số nguyên và Rating phải là số thực hợp lệ!");
                    } catch (DateTimeParseException e) {
                        System.out.println("Lỗi: Định dạng ngày không đúng (yêu cầu dạng dd-MM-yyyy)!");
                    }
                    break;

                case 2:
                    try {
                        System.out.print("Nhập ID phim cần xóa: ");
                        int idDelete = Integer.parseInt(scanner.nextLine());
                        if (manager.deleteMovie(idDelete)) {
                            System.out.println("Phim đã được xóa thành công.");
                        } else {
                            System.out.println("Không tìm thấy phim muốn xóa !");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Lỗi: ID phim phải là số!");
                    }
                    break;

                case 3:
                    try {
                        System.out.print("Mời nhập id phim muốn sửa : ");
                        int idEdit = Integer.parseInt(scanner.nextLine());
                        Movie editMovie = manager.findById(idEdit);

                        if (editMovie == null) {
                            System.out.println("Không tìm thấy phim với id = " + idEdit);
                        } else {
                            System.out.print("Nhập tiêu đề phim:\n");
                            editMovie.setTitle(scanner.nextLine());

                            System.out.print("Nhập đạo diễn:\n");
                            editMovie.setDirector(scanner.nextLine());

                            System.out.print("Nhập ngày phát hành (dd-MM-yyyy):\n");
                            editMovie.setReleaseDate(LocalDate.parse(scanner.nextLine(), dateFormatter));

                            System.out.print("Nhập rating:\n");
                            editMovie.setRating(Double.parseDouble(scanner.nextLine()));

                            System.out.println("Cập nhật phim thành công !");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Lỗi: Dữ liệu số nhập vào không hợp lệ!");
                    } catch (DateTimeParseException e) {
                        System.out.println("Lỗi: Định dạng ngày không hợp lệ!");
                    }
                    break;

                case 4:
                    manager.displayAllMovies();
                    break;

                case 5:
                    System.out.print("Nhập tiêu đề phim để tìm kiếm: ");
                    String searchTitle = scanner.nextLine();
                    manager.searchByName(searchTitle);
                    break;

                case 6:
                    try {
                        System.out.print("Nhập rating tối thiểu để lọc: ");
                        double minRating = Double.parseDouble(scanner.nextLine());
                        manager.filterByRating(minRating);
                    } catch (NumberFormatException e) {
                        System.out.println("Lỗi: Mức đánh giá rating nhập vào phải là một số!");
                    }
                    break;

                case 7:
                    System.out.println("Đã dừng chương trình.");
                    scanner.close();
                    return;

                default:
                    System.out.println("Chức năng không hợp lệ, vui lòng chọn từ 1-7.");
            }
        }
    }
}