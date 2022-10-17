package ra.run;

import ra.entity.Author;
import ra.entity.Book;

import java.util.*;

import static ra.inteFace.IBook.insertData;
import static ra.entity.Author.getData;
import static ra.entity.Author.insertData;

public class BookManagement {
    static int countAuthor = 1;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("********************QUẢN LÝ CỬA HÀNG SÁCH***************");
            System.out.println(" 1. Quản lí tác giả");
            System.out.println(" 2. Quản li sách");
            System.out.println(" 3. Thoát");
            System.out.println("Sự lựa chọn của bạn ");
            int chose = Integer.parseInt(sc.nextLine());
            switch (chose) {
                case 1:
                    BookManagement.getAuthorManagement(sc);
                    break;
                case 2:
                    BookManagement.getBookManagement(sc);
                    break;
                case 3:
                    System.exit(0);
                default:
                    System.out.println("Hãy nhập lại từ 1 đến 3");
            }

        } while (true);
    }

    public static ArrayList<Author> authorsList = new ArrayList<>();
    public static ArrayList<Book> booksList = new ArrayList<>();

    public static void getAuthorManagement(Scanner sc) {
        boolean check = false;

        do {
            System.out.println("********************QUẢN LÝ TÁC GIẢ***********************");
            System.out.println(" 1.Hiển thị danh sách tác giả");
            System.out.println(" 2. Thêm tác giả");
            System.out.println(" 3. Cập nhật thông tin tác giả");
            System.out.println(" 4. Cập nhật trạng thái tác giả");
            System.out.println(" 5. Thoát");
            System.out.println("Sự lựa chọn của bạn");
            int chose = Integer.parseInt(sc.nextLine());
            switch (chose) {
                case 1:
                    BookManagement.displayAuthorList();
                    break;
                case 2:
                    BookManagement.inputAuthorList(sc);
                    break;
                case 3:
                    BookManagement.updateAuthorList(sc);
                    break;
                case 4:
                    BookManagement.getStatus(sc);
                    break;
                case 5:
                    check = true;
                    break;
                default:
                    System.out.print("Hãy chọn lại từ 1 đến 5");
            }

        } while (!check);
    }

    public static void inputAuthorList(Scanner sc) {
        System.out.println("Hãy nhập số lượng tác giả muốn thêm");
        int tacGia = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < tacGia; i++) {
            Author listAuthor = new Author();
            listAuthor.inputData(sc);
            authorsList.add(listAuthor);
        }
        Author.insertData(sc, authorsList);
    }

    public static void displayAuthorList() {
        authorsList = (ArrayList<Author>) getData();
        System.out.println("Danh sách tác giả ");
        for (Author au : authorsList) {
            au.displayData();
        }
        System.out.print("\n");
    }

    public static void updateAuthorList(Scanner sc) {
        System.out.println("Nhập mã tác giả can cap nhật");
        int authorIdCheck = Integer.parseInt(sc.nextLine());
        for (Author st : authorsList) {
            if (st.getAuthorId() == (authorIdCheck)) {
                System.out.println("Hãy nhập tên tác giả để cập nhật");
                do {
                    String authorNameNew = sc.nextLine();
                    if (authorNameNew != "" && authorNameNew.trim().length() != 0) {
                        if (authorNameNew.trim().length() >= 6 &&
                                authorNameNew.trim().length() <= 50) {
                            st.setAuthorName(authorNameNew);
                            break;
                        } else {
                            System.err.println("Hãy nhập lại tên tác giả tù 6 đến 50");
                        }
                    }
                } while (true);
            }
        }
        Author.insertData(sc,authorsList);
    }

    public static void getStatus(Scanner sc) {
        System.out.println("Nhập mã tác giả can cap nhật");
        int authorIdCheck = Integer.parseInt(sc.nextLine());
        for (Author st : authorsList) {
            if (st.getAuthorId() == (authorIdCheck)) {
                System.out.println("Nhập trạng thái mới cho tác giả cần cập nhật");
                String authorStatusNew = sc.nextLine();
                if (authorStatusNew != "" && authorStatusNew.trim().length() != 0) {
                    st.setAuthorStatus(Boolean.parseBoolean(authorStatusNew));
                }
            }
        }
        Author.insertData(sc,authorsList);
    }

    public static void getBookManagement(Scanner sc) {
        boolean check = false;
        do {
            System.out.println("********************QUẢN LÝ SÁCH*************************");
            System.out.println(" 1. Hiện thị danh sách");
            System.out.println(" 2. Thêm các sách");
            System.out.println(" 3. Cập nhật thông tin sách");
            System.out.println(" 4. Cập nhật trạng thái sách");
            System.out.println(" 5. Tính lợi nhuận sách");
            System.out.println(" 6. Sắp xếp sách theo giá bán tắng dần");
            System.out.println(" 7. tìm kiếm sách theo tên sách, tên tác giả");
            System.out.println(" 8. Bán sách");
            System.out.println(" 9. Thoát");
            System.out.println("Sự lựa chọn của bạn");
            int chose = Integer.parseInt(sc.nextLine());
            switch (chose) {
                case 1:
                    BookManagement.displayDataBook();
                    break;
                case 2:
                    BookManagement.inputDataBook(sc);
                    break;
                case 3:
                    BookManagement.upDateBookNew(sc);
                    break;
                case 4:
                    BookManagement.updateStatusBook(sc);
                    break;
                case 5:
                    BookManagement.laiXuatBook(sc);
                    break;
                case 6:
                    BookManagement.sapXepTangDan(sc);
                    break;
                case 7:
                    BookManagement.searchBook(sc);
                    break;
                case 8:
                    BookManagement.sellBook(sc);
                    break;
                case 9:
                    check = true;
                default:
                    System.out.println("hãy chọn lai từ 1 đến 9");
            }
        } while (!check);
    }

    public static void inputDataBook(Scanner sc) {
        System.out.println("Thêm số sách muốn thêm");
        int numberBook = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < numberBook; i++) {
            Book bookNew = new Book();
            bookNew.inputData(sc);
            booksList.add(bookNew);
        }
        Book.insertData(sc, booksList);
    }

    public static void displayDataBook() {
        booksList = (ArrayList<Book>) Book.getData();
        for (Book book : booksList) {
            book.displayData();
        }
        System.out.print("\n");
    }

    public static void upDateBookNew(Scanner sc) {
        System.out.println("Nhập id để cập nhật cho sách");
        String bookIdCheck = sc.nextLine();
        for (Book book : booksList) {
            if (book.getBookId().equals(bookIdCheck.trim())) {
                System.out.println("Nhập tên sách cần cập nhật ");
                do {
                    String bookNameNew = sc.nextLine();
                    if (bookNameNew != "" && bookNameNew.trim().length() != 0) {
                        if (bookNameNew.trim().length() >= 10 &&
                                bookNameNew.trim().length() <= 100) {
                            book.setBookName(bookNameNew);
                            break;
                        } else {
                            System.err.println("Hãy nhập tên sách từ 10 đến 100 kí tự");
                        }
                    }
                } while (true);
                System.out.println("Hãy nhập giá sách cần cập nhật");
                do {
                    Float importPriceNew = Float.parseFloat(sc.nextLine());
                    if (importPriceNew > 0) {
                        book.setImportPrice(importPriceNew);
                        break;
                    } else {
                        System.err.println("hãy nhập giá trên 0 ");
                    }

                } while (true);
                System.out.println("Hãy nhập giá tiền sách cần cập nhật");
                do {
                    Float exportPriceNew = Float.parseFloat(sc.nextLine());
                    if (exportPriceNew >
                            ((book.getImportPrice() * 20 / 100) + book.getImportPrice())) {
                        book.setExportPrice(exportPriceNew);
                        break;
                    } else {
                        System.err.println("Hãy nhập giá sách có giá trị lớn hơn 20% sách nhập vào");
                    }
                } while (true);
                System.out.println("Hãy nhập số lượng sách cần cập nhật");
                String quantityNew = sc.nextLine();
                if (quantityNew != "" && quantityNew.trim().length() != 0) {
                    book.setQuantity(Integer.parseInt(quantityNew));
                }
                System.out.println("hãy nhập tiêu đề mới cần cập nhật");
                do {
                    String titleNew = sc.nextLine();
                    if (titleNew != "" && titleNew.trim().length() != 0) {
                        if (titleNew.trim().length() >= 30 &&
                                titleNew.trim().length() <= 100) {
                            book.setTitle(titleNew);
                            break;
                        } else {
                            System.err.println("Hãy nhập tiêu đề mới từ 30 đến 100 kí tự");
                        }
                    }
                } while (true);

                System.out.println("Hãy nhập nội dung mới cho sách ");
                String contentNew = sc.nextLine();
                if (contentNew != "" && contentNew.trim().length() != 0) {
                    book.setContent(contentNew);
                }
                System.out.println("Hãy nhập nhà xuất bản mới cho sách ");
                String publishingNew = sc.nextLine();
                if (publishingNew != "" && publishingNew.trim().length() != 0) {
                    book.setPublishing(publishingNew);
                }
            }

        }
        Book.insertData(sc, booksList);
    }

    public static void updateStatusBook(Scanner sc) {
        System.out.println("Nhập id để cập nhật cho sách");
        String bookIdCheck = sc.nextLine();
        for (Book book : booksList) {
            if (book.getBookId().equals(bookIdCheck.trim())) {
                System.out.println("Hãy nhập trạng thái mới cho sách");
                String bookStatusNew = sc.nextLine();
                if (bookStatusNew != "" && bookStatusNew.trim().length() != 0) {
                    book.setBookStatus(Boolean.parseBoolean(bookStatusNew));
                }
            }
        }
        Book.insertData(sc,booksList);
    }

    public static void laiXuatBook(Scanner sc) {
        for (Book book : booksList) {
            book.calProfit();
            book.getProfit();
        }
Book.insertData(sc,booksList);
    }


    public static void sapXepTangDan(Scanner sc) {

        Collections.sort(booksList, new Comparator<Book>() {
            @Override
            public int compare(Book o1, Book o2) {
                if (o1.getExportPrice()> o2.getExportPrice()){
                    return 1;
                } else if (o1.getExportPrice()== o2.getExportPrice()) {
                    return 0;
                }else {
                    return -1;
                }
            }
        });
      Book.insertData(sc, booksList);
    }

    public static void searchBook(Scanner sc) {
        System.out.println("Hãy nhập tên cần tim kiếm ");
        String searchName = sc.nextLine();
        for (Book book : booksList) {
            if (book.getBookName().equals(searchName.trim())) {
                book.displayData();
            }
        }
        for (Author at : authorsList) {
            if (at.getAuthorName().equals(searchName.trim())) {
                at.displayData();
            }
        }
Book.insertData(sc,booksList);
    }

    public static void sellBook(Scanner sc) {
        boolean check = false;
        System.out.println("Nhập vào id sách muốn bán");
        String checkId = sc.nextLine();
        for (Book book : booksList) {
            if (book.getBookId().equals(checkId.trim())) {
                book.buyBook(sc);
                break;
            } else {
                check = true;
            }
        }
        if (check) {
            System.out.println("Ko tồn tại id");
        }

        Book.insertData(sc,booksList);

    }
}
