package com.study.java.bookstore;

import java.util.List;
import java.util.Scanner;

public class Service {
    public void instruction() {
        Scanner in = new Scanner(System.in);
        String instruction
                = "======================================================================\n" +
                "=                                                                    =\n" +
                "=                                                                    =\n" +
                "=                   Welcome to Bookstore                             =\n" +
                "=                                                                    =\n" +
                "=            ------------------------------------                    =\n" +
                "=            |                                  |                    =\n" +
                "=            |     1. Print book list           |                    =\n" +
                "=            |                                  |                    =\n" +
                "=            |     2. Book Search               |                    =\n" +
                "=            |                                  |                    =\n" +
                "=            |     3. Add new book              |                    =\n" +
                "=            |                                  |                    =\n" +
                "=            |     4. Delete a book             |                    =\n" +
                "=            |                                  |                    =\n" +
                "=            |     q. Quit                      |                    =\n" +
                "=            |                                  |                    =\n" +
                "=            ------------------------------------                    =\n" +
                "=                                                                    =\n" +
                "======================================================================";
        System.out.println(instruction);

        while(true) {
            System.out.print("press number: ");
            String input = in.nextLine();
            if(input.equals("q")) {
                System.out.println("ok bye :(");
                break;
            } else if(input.equals("1")) {
                getBookList();
            } else if(input.equals("2")) {
                searchBook();
            } else if(input.equals("3")) {
                addBook();
            } else if(input.equals("4")) {
                deleteBook();
            } else {
                System.out.println("error..unknown number..");
            }
        }
        in.close();
    }

    private void getBookList() {
        Book bkInstance = new Book();
        List<Book> bookList = bkInstance.bookList;
        for(Book bk : bookList) {
            System.out.println(bk.toString());
        }
    }

    private void searchBook() {

    }

    private void addBook() {

    }

    private void deleteBook() {

    }
}
