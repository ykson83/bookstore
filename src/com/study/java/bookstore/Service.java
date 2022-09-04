package com.study.java.bookstore;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class Service {

    private List<Book> bookData = new ArrayList<>(
            Arrays.asList(
                    new Book("해리포터", "판타지", "조앤K롤링", "영국", "1990-01-01", 10000, "1열 1", 1),
                    new Book("해리포터2", "판타지", "조앤K롤링", "영국", "1990-01-02", 12000, "1열 2", 2),
                    new Book("이순신전", "위인전", "홍길동", "순신사", "1990-01-03", 10000, "1열 3", 3),
                    new Book("토비의 스프링", "개발", "토비", "토비사", "1990-01-04", 35000, "1열 4", 4),
                    new Book("자바의 정석", "개발", "남궁성", "궁성사", "1990-01-05", 30000, "1열 5", 5)
            )
    );

    private List<Purchase> purchaseList = new ArrayList<>();

    static String HEAD = String.format("| %-5s | %-5s | %-5s | %-5s | %-5s |", "TITLE", "WRITER", "PRICE", "LOCATION", "STOCK");
    static String LINE = "-----------------------------------------------------------";

    static String instruction
            = "======================================================================\n" +
            "=                   Welcome to Bookstore                             =\n" +
            "=                                                                    =\n" +
            "=            ------------------------------------                    =\n" +
            "=            |     1. Print book list           |                    =\n" +
            "=            |     2. Book Search               |                    =\n" +
            "=            |     3. Add new book              |                    =\n" +
            "=            |     4. Delete a book             |                    =\n" +
            "=            |     5. buy a book                |                    =\n" +
            "=            |     6. Print purchase list       |                    =\n" +
            "=            |     q. Quit                      |                    =\n" +
            "=            ------------------------------------                    =\n" +
            "======================================================================\n";

    public void instruction() {
        Scanner in = new Scanner(System.in);
        System.out.println(instruction);

        while (true) {
            System.out.print("press number: ");
            String input = in.nextLine();

            switch (input) {
                case "q":
                    System.out.println("ok bye :(");
                    in.close();
                    System.exit(0);
                    break;
                case "1":
                    printBookList();
                    break;
                case "2":
                    System.out.print("title : ");
                    String title_word = in.nextLine();
                    List<Book> searchList = getBooksByTitle(title_word);

                    if (searchList.size() > 0) {
                        searchList.forEach((book) -> {
                            System.out.printf("| %-5s | %-5s | %-5s | %-5s | %-5s |\n", book.getTitle(), book.getAuthor(), book.getPrice(), book.getLocation(), book.getStock());
                        });
                    }
                    break;
                case "3":
                    System.out.print("Type title : ");
                    String title = in.nextLine();
                    System.out.print("Type genre : ");
                    String genre = in.nextLine();
                    System.out.print("Type author : ");
                    String author = in.nextLine();
                    System.out.print("Type publisher : ");
                    String publisher = in.nextLine();
                    System.out.print("Type price : ");
                    int price = in.nextInt();
                    System.out.print("Type pubDate : ");
                    String pubDate = in.nextLine();
                    System.out.print("Type location : ");
                    String location = in.nextLine();
                    System.out.print("Type stock : ");
                    int stock = in.nextInt();

                    Book new_book = new Book(title, genre, author, publisher, pubDate, price, location, stock);
                    addBook(new_book);
                    break;
                case "4":
                    System.out.print("Type title : ");
                    String delete_title = in.nextLine();

                    List<Book> deleteList = getBooksByTitle(delete_title);
                    deleteBook(deleteList);
                    break;
                case "5":
                    System.out.print("Type title : ");
                    String title_buy = in.nextLine();
                    System.out.print("Type quantity: ");
                    int quantity = in.nextInt();
                    // nextInt()는 개행 전까지만 입력 받음 -> 다음 입력에 개행(엔터)가 들어가버려서 switch문이 한번 통과해서 오류
                    in.nextLine();
                    System.out.print("Type customer: ");
                    String customer_name = in.nextLine();
                    buyBook(title_buy, quantity, customer_name);
                    break;
                case "6":
                    printPurchaseList();
                    break;
                default:
                    System.out.println("error..unknown number..");
            }
        }
    }

    // # Book -----------------------------------------
    private List<Book> getBookList() {
        /*System.out.println(HEAD);
        System.out.println(LINE);

        bookList.forEach((book) -> {
            System.out.printf("| %-5s | %-5s | %-5s | %-5s |\n", book.getTitle(), book.getAuthor(), book.getPrice(), book.getLocation());
            System.out.println(LINE);
        });*/
        return bookData;
    }

    public void printBookList() {
        System.out.println(HEAD);
        System.out.println(LINE);

        List<Book> bkList = getBookList();
        bkList.forEach((book) -> {
            System.out.printf("| %-5s | %-5s | %-5s | %-5s | %-5s |\n", book.getTitle(), book.getAuthor(), book.getPrice(), book.getLocation(), book.getStock());
            System.out.println(LINE);
        });
    }

    private List<Book> getBooksByTitle(String title) {
        List<Book> resultList = bookData.stream()
                .filter((book) -> book.getTitle().contains(title))
                .collect(Collectors.toList());

        return resultList;
    }

    private void addBook(Book book) {
        bookData.add(book);
    }

    private void deleteBook(List<Book> deleteList) {
        deleteList.forEach((delete) -> bookData.remove(delete));
    }

    public void buyBook(String title, int quantity, String customer) {
        List<Book> searchList = getBooksByTitle(title);

        if(searchList.size() == 0) {
            System.out.println("찾으시는 책이 없습니다");
            return;
        }

        bookData.stream()
            .filter(book -> book.getTitle().equals(title))
            .filter(book -> book.getStock() > quantity)
            .forEach(book -> {
                book.setStock(book.getStock() - quantity);

                Purchase purchase = new Purchase(title, customer, quantity, getToday());
                getPurchaseList().add(purchase);
            });
    }

    // #Purchase --------------------------------
    public List<Purchase> getPurchaseList() {
        return purchaseList;
    }

    public void printPurchaseList() {
        List<Purchase> purlist = getPurchaseList();
        if(purlist.size() == 0) System.out.println("구매 내역이 없습니다");
        purlist.forEach((purchase) -> System.out.println(purchase.toString()));
    }

    // util -------------------------------------
    public String getToday() {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String today = format.format(date);

        return today;
    }
}
