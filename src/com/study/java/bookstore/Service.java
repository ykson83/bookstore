package com.study.java.bookstore;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Service {

    private List<Book> bookList = new ArrayList<>(
            Arrays.asList(
                    new Book("해리포터", "판타지", "조앤K롤링", "영국", "1990-01-01", 10000, "1열 1"),
                    new Book("해리포터2", "판타지2", "조앤K롤링2", "영국2", "1990-01-02", 10002, "1열 2"),
                    new Book("해리포터3", "판타지3", "조앤K롤링3", "영국3", "1990-01-03", 10003, "1열 3"),
                    new Book("해리포터4", "판타지4", "조앤K롤링4", "영국4", "1990-01-04", 10004, "1열 4"),
                    new Book("해리포터5", "판타지5", "조앤K롤링5", "영국5", "1990-01-05", 10005, "1열 5")
            )
    );

    static String HEAD = String.format("| %-5s | %-5s | %-5s | %-5s |", "TITLE", "WRITER", "PRICE", "LOCATION");
    static String LINE = "-----------------------------------------------------------";

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
                System.out.print("title : ");
                String title = in.next();
                List<Book> resultList = getBookByTitle(title);

                if (resultList.size() > 0) {
                    // System.out.println(resultList.forEach((result) -> System.out.println()));
                    resultList.forEach((result) -> System.out.println(result));
                }
            } else if(input.equals("3")) {
                System.out.print("Type title : ");
                String title = in.next();
                System.out.print("Type genre : ");
                String genre = in.next();
                System.out.print("Type author : ");
                String author = in.next();
                System.out.print("Type publisher : ");
                String publisher = in.next();
                System.out.print("Type price : ");
                int price = in.nextInt();
                System.out.print("Type pubDate : ");
                String pubDate = in.next();
                System.out.print("Type location : ");
                String location = in.next();

                Book new_book = new Book(title, genre, author, publisher, pubDate, price, location);
                addBook(new_book);
                getBookList();
            } else if(input.equals("4")) {
                System.out.print("Type title : ");
                String title = in.next();

                List<Book> deleteList = getBookByTitle(title);
                deleteBook(deleteList);
                getBookList();
            } else {
                System.out.println("error..unknown number..");
            }
        }
        in.close();
    }

    private void getBookList() {
        System.out.println(HEAD);
        System.out.println(LINE);

        bookList.forEach((book) -> {
            System.out.printf("| %-5s | %-5s | %-5s | %-5s |\n", book.getTitle(), book.getAuthor(), book.getPrice(), book.getLocation());
            System.out.println(LINE);
        });
    }

    private List<Book> getBookByTitle(String title) {
        List<Book> resultList = bookList.stream()
                .filter((book) -> book.getTitle().contains(title))
                .collect(Collectors.toList());

        return resultList;
    }

    private void addBook(Book book) {
        bookList.add(book);
    }

    private void deleteBook(List<Book> deleteList) {
        deleteList.forEach((delete) -> bookList.remove(delete));
    }
}
