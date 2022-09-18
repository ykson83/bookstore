package com.study.java.bookstore;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class Service {

    /*private List<Book> booklist = new ArrayList<>(
            Arrays.asList(
                    new Book("해리포터", "판타지", "조앤K롤링", "영국", "1990-01-01", 10000, "1열 1", 1),
                    new Book("해리포터2", "판타지", "조앤K롤링", "영국", "1990-01-02", 12000, "1열 2", 2),
                    new Book("이순신전", "위인전", "홍길동", "순신사", "1990-01-03", 10000, "1열 3", 3),
                    new Book("토비의 스프링", "개발", "토비", "토비사", "1990-01-04", 35000, "1열 4", 4),
                    new Book("자바의 정석", "개발", "남궁성", "궁성사", "1990-01-05", 30000, "1열 5", 5)
            )
    );*/
    private List<Book> booklist = new ArrayList<>();
    private List<Purchase> purchaselist = new ArrayList<>();
    private List<Member> memberlist = new ArrayList<>();

    {
        String path_b = "booklist.csv";
        String path_p = "purchaselist.csv";
        String path_m = "memberlist.csv";
        try {
            booklist = Files.lines(Path.of(path_b)).map( // line -> List<String> -> List<Book>
                    line -> {
                        List<String> bookStr = Arrays.stream(line.split(",")).map(String::trim).collect(Collectors.toList());

                        return new Book(bookStr.get(0), bookStr.get(1), bookStr.get(2), bookStr.get(3), bookStr.get(4),
                                Integer.parseInt(bookStr.get(5)), bookStr.get(6), Integer.parseInt(bookStr.get(7)));
                    }
            ).collect(Collectors.toList());

            purchaselist = Files.lines(Path.of(path_p)).map(
                    line -> {
                        List<String> purStr = Arrays.stream(line.split(",")).map(String::trim).collect(Collectors.toList());

                        return new Purchase(purStr.get(0), purStr.get(1), Integer.parseInt(purStr.get(2)), purStr.get(3));
                    }
            ).collect(Collectors.toList());

            memberlist = Files.lines(Path.of(path_m)).map(
                    line -> {
                        List<String> memberStr = Arrays.stream(line.split(",")).map(String::trim).collect(Collectors.toList());
                        return new Member(memberStr.get(0), memberStr.get(1), memberStr.get(2), memberStr.get(3));
                    }
            ).collect(Collectors.toList());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

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
            "=            |     5. Buy a book                |                    =\n" +
            "=            |     6. Print purchase list       |                    =\n" +
            "=            |     7. Add book stock            |                    =\n" +
            "=            |     8. Print Member list         |                    =\n" +
            "=            |     9. Add new member            |                    =\n" +
            "=            |    10. Withdraw a member         |                    =\n" +
            "=            |    11. Modify a member           |                    =\n" +
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
                case "7":
                    System.out.print("Type title: ");
                    String bookTitle = in.nextLine();
                    System.out.print("Type add stock: ");
                    int addStock = Integer.parseInt(in.nextLine().trim());

                    addBookStock(bookTitle, addStock);
                    break;
                case "8":
                    printMemberList();
                    break;
                case "9":
                    System.out.print("Type name: ");
                    String name = in.nextLine().trim();
                    System.out.print("Type email: ");
                    String email = in.nextLine().trim();
                    System.out.print("Type address: ");
                    String address = in.nextLine().trim();

                    addMember(name, email, address);
                    break;
                case "10":
                    System.out.print("Type name: ");
                    String name_except = in.nextLine().trim();
                    System.out.print("Type email: ");
                    String email_except = in.nextLine().trim();

                    withdrawMember(name_except, email_except);
                    break;
                case "11":
                    System.out.print("Type email: ");
                    String email_old = in.nextLine().trim();
                    Optional<Member> member_old = getMemberByEmail(email_old);
                    if(member_old.isEmpty()) {
                        System.out.println("not exist member... ");
                        break;
                    }

                    System.out.println(member_old.toString());

                    System.out.print("Type new name: ");
                    String name_new = in.nextLine().trim();
                    System.out.print("Type new address: ");
                    String address_new = in.nextLine().trim();

                    modifyMember(email_old, new Member(name_new, email_old, address_new, null));
                    break;
                default:
                    System.out.println("error..unknown number..");
            }
        }
    }

    // # Book -----------------------------------------
    private List<Book> getBookList() { return booklist; }

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
        List<Book> resultList = booklist.stream()
                .filter((book) -> book.getTitle().contains(title))
                .collect(Collectors.toList());

        return resultList;
    }

    private void addBook(Book book) { booklist.add(book); }

    private void deleteBook(List<Book> deleteList) {
        deleteList.forEach((delete) -> booklist.remove(delete));
    }

    public void buyBook(String title, int quantity, String customer) {
        List<Book> searchList = getBooksByTitle(title);

        if(searchList.size() == 0) {
            System.out.println("찾으시는 책이 없습니다");
            return;
        }

        booklist.stream()
            .filter(book -> book.getTitle().equals(title))
            .filter(book -> book.getStock() > quantity)
            .forEach(book -> {
                book.setStock(book.getStock() - quantity);

                Purchase purchase = new Purchase(title, customer, quantity, getToday());
                getPurchaseList().add(purchase);
            });
    }

    public void addBookStock(String bookTitle, int addStock) {
        getBookList().stream().filter(book -> book.getTitle().equals(bookTitle))
                .forEach(book -> book.setStock(book.getStock() + addStock));
    }

    // #Purchase --------------------------------
    public List<Purchase> getPurchaseList() { return purchaselist; }

    public void printPurchaseList() {
        List<Purchase> purlist = getPurchaseList();
        if(purlist.size() == 0) System.out.println("구매 내역이 없습니다");
        purlist.forEach((purchase) -> System.out.println(purchase.toString()));
    }

    // #Member --------------------------------
    public List<Member> getMemberlist() { return memberlist; }

    public List<Member> getActiveMemberList() {
        return getMemberlist().stream().filter(member -> member.isActive() == true).collect(Collectors.toList());
    }

    public void printMemberList() {
        getActiveMemberList().stream().forEach(member -> System.out.println(member.toString()));
    }

    public void addMember(String name, String email, String address) {
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        Date d = new Date();
        String today = f.format(d);

        getMemberlist().add(new Member(name, email, address, today));
    }

    public void withdrawMember(String name, String email) {
        getActiveMemberList().stream()
                .filter(member -> member.getEmail().equals(email))
                .forEach(member -> member.setActive(false));
    }

    public Optional<Member> getMemberByEmail(String email) {
        return getActiveMemberList().stream()
                .filter(m -> m.getEmail().equals(email)).findFirst();
    }

    public void modifyMember(String email, Member m_t) {
        // null check는 사전에 함
        getActiveMemberList().stream()
                .filter(member -> member.getEmail().equals(m_t.getEmail()))
                .forEach(member -> {
                    System.out.println(member.toString());
                    member.setName(m_t.getName());
                    member.setAddress(m_t.getAddress());
                });
    }

    // util -------------------------------------
    public String getToday() {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String today = format.format(date);

        return today;
    }
}
