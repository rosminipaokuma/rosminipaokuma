import java.util.Scanner;

class User {
    protected String name;
    protected String nim;
    protected String faculty;
    protected String programStudi;

    public User(String name, String nim, String faculty, String programStudi) {
        this.name = name;
        this.nim = nim;
        this.faculty = faculty;
        this.programStudi = programStudi;
    }

    public void displayInfo() {
        System.out.println("Nama: " + name);
        System.out.println("NIM: " + nim);
        System.out.println("Fakultas: " + faculty);
        System.out.println("Program Studi: " + programStudi);
    }

    public void showBorrowedBooks() {
    }

    public void logout() {
    }

    public void displayBooks(Book[] books) {
        System.out.println("Daftar Buku:");
        for (Book book : books) {
            System.out.println("ID: " + book.bookId + " | Judul: " + book.title + " | Penulis: " + book.author);
        }
    }
}

class Book {
    protected String bookId;
    protected String title;
    protected String author;
    protected String category;
    protected int stock;
    protected int duration;

    public Book(String bookId, String title, String author, String category, int stock, int duration) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.category = category;
        this.stock = stock;
        this.duration = duration;
    }
}

class HistoryBook extends Book {
    public HistoryBook(String bookId, String title, String author, String category, int stock, int duration) {
        super(bookId, title, author, category, stock, duration);
    }
}

class StoryBook extends Book {
    public StoryBook(String bookId, String title, String author, String category, int stock, int duration) {
        super(bookId, title, author, category, stock, duration);
    }
}

class TextBook extends Book {
    public TextBook(String bookId, String title, String author, String category, int stock, int duration) {
        super(bookId, title, author, category, stock, duration);
    }
}

class Student extends User {
    private Book[] borrowedBooks;
    private int borrowedCount;

    public Student(String name, String nim, String faculty, String programStudi) {
        super(name, nim, faculty, programStudi);
        borrowedBooks = new Book[10];
        borrowedCount = 0;
    }

    @Override
    public void showBorrowedBooks() {
        System.out.println("Buku yang dipinjam:");
        for (Book book : borrowedBooks) {
            if (book != null) {
                System.out.println("ID: " + book.bookId + " | Judul: " + book.title + " | Penulis: " + book.author);
            }
        }
    }

    public void borrowBook(Book book) {
        if (borrowedCount < borrowedBooks.length && book.stock > 0) {
            borrowedBooks[borrowedCount++] = book;
            book.stock--;
            System.out.println("Buku dengan ID " + book.bookId + " berhasil dipinjam.");
        } else {
            System.out.println("Maaf, tidak dapat meminjam buku.");
        }
    }

    public void returnBook(Book book) {
        if (borrowedCount > 0) {
            for (int i = 0; i < borrowedBooks.length; i++) {
                if (borrowedBooks[i] != null && borrowedBooks[i].bookId.equals(book.bookId)) {
                    borrowedBooks[i] = null;
                    borrowedCount--;
                    book.stock++;
                    System.out.println("Buku dengan ID " + book.bookId + " berhasil dikembalikan.");
                    return;
                }
            }
            System.out.println("Buku tidak ditemukan dalam daftar peminjaman.");
        } else {
            System.out.println("Tidak ada buku yang dipinjam.");
        }
    }

    @Override
    public void displayBooks(Book[] books) {
        super.displayBooks(books);
    }
}

class Admin extends User {
    private Book[] bookList;
    private int bookCount;

    public Admin(String name, String nim, String faculty, String programStudi) {
        super(name, nim, faculty, programStudi);
        bookList = new Book[100];
        bookCount = 0;
    }

    public void addBook(String title, String author, String category, int stock, int duration) {
        String bookId = generateId();
        Book book = null;
        if (category.equalsIgnoreCase("history")) {
            book = new HistoryBook(bookId, title, author, category, stock, duration);
        } else if (category.equalsIgnoreCase("story")) {
            book = new StoryBook(bookId, title, author, category, stock,
                    duration);
        } else if (category.equalsIgnoreCase("text")) {
            book = new TextBook(bookId, title, author, category, stock, duration);
        }
        if (book != null && bookCount < bookList.length) {
            bookList[bookCount++] = book;
            System.out.println("Buku dengan ID " + bookId + " berhasil ditambahkan.");
        } else {
            System.out.println("Gagal menambahkan buku.");
        }
    }

    public void modifyBook(String bookId, int stock) {
        for (int i = 0; i < bookCount; i++) {
            if (bookList[i].bookId.equals(bookId)) {
                bookList[i].stock = stock;
                System.out.println("Stok buku dengan ID " + bookId + " berhasil diubah.");
                return;
            }
        }
        System.out.println("Buku dengan ID " + bookId + " tidak ditemukan.");
    }

    @Override
    public void displayBooks(Book[] books) {
        super.displayBooks(books);
    }

    private String generateId() {
        // Implementasi untuk generate ID unik
        return "B" + (bookCount + 1);
    }
}

public class LibraryManagementSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;

        Book[] books = {
                new HistoryBook("H1", "Sejarah Dunia", "John Doe", "History", 5, 7),
                new HistoryBook("H2", "Sejarah Indonesia", "Jane Smith", "History", 3, 5),
                new StoryBook("S1", "Harry Potter", "J.K. Rowling", "Story", 10, 14),
                new StoryBook("S2", "Lord of the Rings", "J.R.R. Tolkien", "Story", 8, 10),
                new TextBook("T1", "Mathematics", "Albert Einstein", "Text", 6, 10),
                new TextBook("T2", "Physics", "Isaac Newton", "Text", 4, 8),
                new TextBook("T3", "Chemistry", "Marie Curie", "Text", 7, 9)
        };

        while (isRunning) {
            System.out.println("\nSelamat datang di Sistem Manajemen Perpustakaan\nSilakan pilih menu:");
            System.out.println("1. Login sebagai Mahasiswa");
            System.out.println("2. Login sebagai Admin");
            System.out.println("3. Keluar");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Membuang karakter newline

            switch (choice) {
                case 1:
                    System.out.print("Masukkan nama: ");
                    String studentName = scanner.nextLine();
                    System.out.print("Masukkan NIM: ");
                    String studentNim = scanner.nextLine();
                    System.out.print("Masukkan Fakultas: ");
                    String studentFaculty = scanner.nextLine();
                    System.out.print("Masukkan Program Studi: ");
                    String studentProgramStudi = scanner.nextLine();

                    Student student = new Student(studentName, studentNim, studentFaculty, studentProgramStudi);
                    student.displayInfo();

                    boolean studentLoggedIn = true;
                    while (studentLoggedIn) {
                        System.out.println("\nMenu Mahasiswa:");
                        System.out.println("1. Lihat daftar buku");
                        System.out.println("2. Pinjam buku");
                        System.out.println("3. Lihat buku yang dipinjam");
                        System.out.println("4. Kembalikan buku");
                        System.out.println("5. Logout");

                        int studentChoice = scanner.nextInt();
                        scanner.nextLine(); // Membuang karakter newline

                        switch (studentChoice) {
                            case 1:
                                student.displayBooks(books);
                                break;
                            case 2:
                                System.out.print("Masukkan ID buku yang ingin dipinjam: ");
                                String bookToBorrow = scanner.nextLine();
                                for (Book book : books) {
                                    if (book.bookId.equals(bookToBorrow)) {
                                        student.borrowBook(book);
                                        break;
                                    }
                                }
                                break;
                            case 3:
                                student.showBorrowedBooks();
                                break;
                            case 4:
                                System.out.print("Masukkan ID buku yang ingin dikembalikan: ");
                                String bookToReturn = scanner.nextLine();
                                for (Book book : books) {
                                    if (book.bookId.equals(bookToReturn)) {
                                        student.returnBook(book);
                                        break;
                                    }
                                }
                                break;
                            case 5:
                                student.logout();
                                studentLoggedIn = false;
                                break;
                            default:
                                System.out.println("Pilihan tidak valid.");
                        }
                    }
                    break;
                case 2:
                    System.out.print("Masukkan nama: ");
                    String adminName = scanner.nextLine();
                    System.out.print("Masukkan ID: ");
                    String adminNim = scanner.nextLine();
                    System.out.print("Masukkan Fakultas: ");
                    String adminFaculty = scanner.nextLine();
                    System.out.print("Masukkan Program Studi: ");
                    String adminProgramStudi = scanner.nextLine();

                    Admin admin = new Admin(adminName, adminNim, adminFaculty, adminProgramStudi);
                    admin.displayInfo();

                    boolean adminLoggedIn = true;
                    while (adminLoggedIn) {
                        System.out.println("\nMenu Admin:");
                        System.out.println("1. Lihat daftar buku");
                        System.out.println("2. Tambah buku");
                        System.out.println("3. Modifikasi stok buku");
                        System.out.println("4. Logout");

                        int adminChoice = scanner.nextInt();
                        scanner.nextLine(); // Membuang karakter newline

                        switch (adminChoice) {
                            case 1:
                                admin.displayBooks(books);
                                break;
                            case 2:
                                System.out.print("Masukkan judul buku: ");
                                String title = scanner.nextLine();
                                System.out.print("Masukkan penulis buku: ");
                                String author = scanner.nextLine();
                                System.out.print("Masukkan kategori buku (history/story/text): ");
                                String category = scanner.nextLine();
                                System.out.print("Masukkan stok buku: ");
                                int stock = scanner.nextInt();
                                System.out.print("Masukkan durasi peminjaman buku (dalam hari): ");
                                int duration = scanner.nextInt();
                                admin.addBook(title, author, category, stock, duration);
                                break;
                            case 3:
                                System.out.print("Masukkan ID buku yang ingin dimodifikasi: ");
                                String bookId = scanner.nextLine();
                                System.out.print("Masukkan stok baru: ");
                                int newStock = scanner.nextInt();
                                admin.modifyBook(bookId, newStock);
                                break;
                            case 4:
                                admin.logout();
                                adminLoggedIn = false;
                                break;
                            default:
                                System.out.println("Pilihan tidak valid.");
                        }
                    }
                    break;
                case 3:
                    isRunning = false;
                    break;
                default:
                    System.out.println("Pilihan tidak valid.");
            }
        }

        scanner.close();
    }
}
