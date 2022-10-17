package ra.entity;

import ra.inteFace.IBook;
import ra.run.BookManagement;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Author implements IBook, Serializable {
    private int authorId;
    private String authorName;
    private boolean authorStatus;

    public Author() {
    }

    public Author(int authorId, String authorName, boolean authorStatus) {
        this.authorId = authorId;
        this.authorName = authorName;
        this.authorStatus = authorStatus;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public boolean isAuthorStatus() {
        return authorStatus;
    }

    public void setAuthorStatus(boolean authorStatus) {
        this.authorStatus = authorStatus;
    }

    @Override
    public void inputData(Scanner sc) {
        this.authorId = BookManagement.authorsList.size() + 1;
        System.out.println("Hãy nhập tên  tác giả");
        do {
            this.authorName = sc.nextLine();
            if (this.authorName.trim().length() >= 6 &&
                    this.authorName.trim().length() <= 50) {
                break;
            } else {
                System.out.println("Hãy nhập tên tác giả từ 6 đến 50 kí tự");
            }
        } while (true);
        System.out.println("Hãy nhập trạng thái tác giả");
        this.authorStatus = Boolean.parseBoolean(sc.nextLine());
    }

    @Override
    public void displayData() {
        System.out.printf("Mã tác giả: %d--Tên tác giả: %s--Trạng thái tác giả : %b\n",
                this.authorId, this.authorName, this.authorStatus);

    }

    public static List<Author> getData() {
        ArrayList<Author> authors = new ArrayList<>();
        File file = null;
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try {
            file = new File(PATH_AUTHOR);
            fis=new FileInputStream(file);
            ois=new ObjectInputStream(fis);
            authors= (ArrayList<Author>) ois.readObject();
            ois.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return authors;
    }

    public static void insertData(Scanner sc, List<Author> author) {
        File file = null;
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            file = new File(PATH_AUTHOR);
            fos = new FileOutputStream(file);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(author);
            oos.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
