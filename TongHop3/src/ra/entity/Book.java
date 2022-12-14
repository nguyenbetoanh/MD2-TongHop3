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
        System.out.println("H??y nh???p m?? id s??ch");
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
                        System.out.println("tr??ng con m??? n?? r???i");
                    } else {
                        break;
                    }
                } else {
                    System.out.println("H??y nh??p m?? id b???t ????u b???ng k?? t??? B");
                }

            } else {
                System.out.println("H??y n???p l???i m?? id g???m 5 k?? t???");
            }
        } while (true);
        System.out.println("H??y nh???p t??n s??ch");
        do {
            this.bookName = sc.nextLine();
            if (this.bookName.trim().length() >= 10 &&
                    this.bookName.trim().length() <= 100) {
                break;
            } else {
                System.out.println("H??y nh???p t??n s??ch t??? 10 ?????n 100 k?? t???");
            }
        } while (true);
        System.out.println("H??y nh??p gi?? ti???n s??ch ");
        do {
            this.importPrice = Float.parseFloat(sc.nextLine());
            if (this.importPrice > 0) {
                break;
            } else {
                System.out.println("H??y nh???p l???i gi?? ti???n tr??n 0");
            }
        } while (true);
        System.out.println("H??y nh???p gi?? b??n s??ch ");
        do {
            this.exportPrice = Float.parseFloat(sc.nextLine());
            if (
                    this.exportPrice > ((this.importPrice * 20 / 100) + this.importPrice)
            ) {
                break;
            } else {
                System.out.println("h??y nh???p gi?? b??n s??ch l???n h??n it nh???t 20% s??ch nhap");
            }
        } while (true);
        System.out.println("H??y nh???p s??? l?????ng s??ch ");
        this.quantity = Integer.parseInt(sc.nextLine());
        System.out.println("H??y nh???p ti??u ????? cho s??ch");
        do {
            this.title = sc.nextLine();
            if (this.title.trim().length() >= 30 &&
                    this.title.trim().length() <= 100) {
                break;
            } else {
                System.out.println("h??y nh???p ti??u ????? cho s??ch t??? 30 t???i 100 ");
            }
        } while (true);
        System.out.println("H??y nh???p n??i dung t??m t???t s??ch");
        this.content = sc.nextLine();
        System.out.println("h??y nh???p nh?? s???n xu???t");
        this.publishing = sc.nextLine();
        System.out.println("H??y nh???p tr???ng th??i s??ch");
        this.bookStatus = Boolean.getBoolean(sc.nextLine());
    }

    @Override
    public void displayData() {
        System.out.printf("M?? s??ch : %s--T??n s??ch: %s--Gi?? nh???p s??ch: %f--Gi?? b??n s??ch: %f--L???i nhu???n:%f--S??? l?????ng s??ch c??n l???i: %d--Ti??u ????? :%s--Nh?? xu???t b???n:%s--Tr???ng th??i s??ch: %b \n",
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
        System.out.println("h??y nh??p s??? s??ch mu???n b??n");
        int number=Integer.parseInt(sc.nextLine());
        if (quantity<number){
            System.out.println("S??? l?????ng s??ch kh??ng ?????");
        }else {
            this.setQuantity(this.getQuantity()-number);
        }


    }


}
