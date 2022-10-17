package ra.inteFace;

import ra.entity.Book;

import java.util.ArrayList;
import java.util.Scanner;

public interface IBook {
    void inputData(Scanner sc);
    void displayData();
final String PATH_AUTHOR="E:\\MD2\\baiTongHop3\\TongHop3\\src\\ra\\entity\\Author.txt";
    final String PATH_BOOK="E:\\MD2\\baiTongHop3\\TongHop3\\src\\ra\\entity\\Book.txt";
    static void getData() {

    }

    static void insertData(Scanner sc, ArrayList<Book> booksList) {

    }
}
