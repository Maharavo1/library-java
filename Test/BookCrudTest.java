package Test;

import Models.Book;
import Repository.BookCrudOperations;

import java.util.ArrayList;
import java.util.List;

public class BookCrudTest {
    public static void BookTest() {
        BookCrudOperations bookCrudOperations = new BookCrudOperations();

        BookCrudOperations.getConnection();

        List<Book> allBooks = bookCrudOperations.findAll();
        System.out.println("Tous les livres :");
        for (Book b : allBooks) {
            System.out.println(b);
        }

        Book bookToSave = new Book(22, "Fantasy", "Book4", 350, Models.Topic.valueOf("OTHER"), java.sql.Date.valueOf("2023-04-01"), 1, "AVAILABLE"
        );
        Book savedBook = bookCrudOperations.save(bookToSave);
        System.out.println("Livre enregistré : " + savedBook);

        List<Book> booksToSave = new ArrayList<>();
        booksToSave.add(new Book(23, "Mystery", "Book5", 200, Models.Topic.valueOf("OTHER"), java.sql.Date.valueOf("2023-05-01"), 2, "AVAILABLE"));
        booksToSave.add(new Book(24, "Thriller", "Book6", 280, Models.Topic.valueOf("COMEDY"), java.sql.Date.valueOf("2023-06-01"), 3, "BORROWED"));

        List<Book> savedBooks = bookCrudOperations.saveAll(booksToSave);
        System.out.println("Livres enregistrés :");
        for (Book b : savedBooks) {
            System.out.println(b);
        }
        if (!savedBooks.isEmpty()) {
            Book bookToDelete = savedBooks.get(0);
            Book deletedBook = bookCrudOperations.delete(bookToDelete);
            System.out.println("Livre supprimé : " + deletedBook);
        }
    }
}
