package ra.entity;

import ra.inteFace.IBook;
import ra.run.BookManagement;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Book implements IBook , Serializable{
    private String bookId;
    private String bookName;
    private float importPrice;
    private float exportPrice;
    private float profit;
    private int quantity;
    private ArrayList<Author> listAuthor;
    private String title;
    private String content;
    private String publishing;
    private boolean bookStatus;

    public Book() {
    }

    public Book(String bookId, String bookName, float importPrice, float exportPrice, float profit, int quantity, ArrayList<Author> listAuthor, String title, String content, String publishing, boolean bookStatus) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.importPrice = importPrice;
        this.exportPrice = exportPrice;
        this.profit = profit;
        this.quantity = quantity;
        this.listAuthor = listAuthor;
        this.title = title;
        this.content = content;
        this.publishing = publishing;
        this.bookStatus = bookStatus;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public float getImportPrice() {
        return importPrice;
    }

    public void setImportPrice(float importPrice) {
        this.importPrice = importPrice;
    }

    public float getExportPrice() {
        return exportPrice;
    }

    public void setExportPrice(float exportPrice) {
        this.exportPrice = exportPrice;
    }

    public float getProfit() {
        return profit;
    }

    public void setProfit(float profit) {
        this.profit = profit;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public ArrayList<Author> getListAuthor() {
        return listAuthor;
    }

    public void setListAuthor(ArrayList<Author> listAuthor) {
        this.listAuthor = listAuthor;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPublishing() {
        return publishing;
    }

    public void setPublishing(String publishing) {
        this.publishing = publishing;
    }

    public boolean isBookStatus() {
        return bookStatus;
    }

    public void setBookStatus(boolean bookStatus) {
        this.bookStatus = bookStatus;
    }

    @Override
    public void inputData(Scanner sc) {
        System.out.println("Hãy nhập mã id sách");
        do {
            this.bookId = sc.nextLine();
            if (this.bookId.trim().length() == 5) {
                if (this.bookId.trim().charAt(0) == 'B') {
                    boolean check = true;
                    for (Book book : BookManagement.booksList) {
                        if (book.getBookId().equals(this.bookId.trim())) {
                            check = false;
                            break;
                        }
                    }
                    if (!check) {
                        System.out.println("trùng con mẹ nó rồi");
                    } else {
                        break;
                    }
                } else {
                    System.out.println("Hãy nhâp mã id bắt đàu bằng kí tự B");
                }

            } else {
                System.out.println("Hãy nập lại mã id gồm 5 kí tự");
            }
        } while (true);
        System.out.println("Hãy nhập tên sách");
        do {
            this.bookName = sc.nextLine();
            if (this.bookName.trim().length() >= 10 &&
                    this.bookName.trim().length() <= 100) {
                break;
            } else {
                System.out.println("Hãy nhập tên sách từ 10 đến 100 kí tự");
            }
        } while (true);
        System.out.println("Hãy nhâp giá tiền sách ");
        do {
            this.importPrice = Float.parseFloat(sc.nextLine());
            if (this.importPrice > 0) {
                break;
            } else {
                System.out.println("Hãy nhập lại giá tiền trên 0");
            }
        } while (true);
        System.out.println("Hãy nhập giá bán sách ");
        do {
            this.exportPrice = Float.parseFloat(sc.nextLine());
            if (
                    this.exportPrice > ((this.importPrice * 20 / 100) + this.importPrice)
            ) {
                break;
            } else {
                System.out.println("hãy nhập giá bán sách lớn hơn it nhất 20% sách nhap");
            }
        } while (true);
        System.out.println("Hãy nhấp số lượng sách ");
        this.quantity = Integer.parseInt(sc.nextLine());
        System.out.println("Hãy nhập tiêu đề cho sách");
        do {
            this.title = sc.nextLine();
            if (this.title.trim().length() >= 30 &&
                    this.title.trim().length() <= 100) {
                break;
            } else {
                System.out.println("hãy nhập tiêu đề cho sách từ 30 tới 100 ");
            }
        } while (true);
        System.out.println("Hãy nhập nôi dung tóm tắt sách");
        this.content = sc.nextLine();
        System.out.println("hãy nhập nhà sản xuất");
        this.publishing = sc.nextLine();
        System.out.println("Hãy nhập trạng thái sách");
        this.bookStatus = Boolean.getBoolean(sc.nextLine());
    }

    @Override
    public void displayData() {
        System.out.printf("Mã sách : %s--Tên sách: %s--Giá nhập sách: %f--Giá bán sách: %f--Lợi nhuận:%f--Số lượng sách còn lại: %d--Tiêu đề :%s--Nhà xuất bản:%s--Trạng thái sách: %b \n",
                this.bookId, this.bookName, this.importPrice, this.exportPrice, this.profit, this.quantity, this.title, this.publishing, this.bookStatus);


    }

    public static List<Book> getData() {
        List<Book> books = new ArrayList<>();
        File file = null;
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try {
            file = new File(PATH_BOOK);
            fis = new FileInputStream(file);
            ois = new ObjectInputStream(fis);
            books = (List<Book>) ois.readObject();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return books;
    }


    public static List<Book> insertData(Scanner sc , List<Book> books){
        File file = null;
        FileOutputStream fos= null;
        ObjectOutputStream oos=null;
        try{
            file = new File(PATH_BOOK);
            fos = new FileOutputStream(file);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(books);
            oos.close();
        }catch (FileNotFoundException e){
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return books;
    }
    public  void calProfit(){
        this.profit=this.exportPrice-this.importPrice;
    }
    public void buyBook(Scanner sc){
        System.out.println("hãy nhâp số sách muốn bán");
        int number=Integer.parseInt(sc.nextLine());
        if (quantity<number){
            System.out.println("Số lượng sách không đủ");
        }else {
            this.setQuantity(this.getQuantity()-number);
        }


    }


}
