package Test;

import Models.Author;
import Repository.AuthorCrudOperations;

import java.util.ArrayList;
import java.util.List;

public class AuthorCrudTest {
    public static void AuthorTest() {
        AuthorCrudOperations authorCrudOperations = new AuthorCrudOperations();
        AuthorCrudOperations.getConnection();
        List<Author> allAuthors = authorCrudOperations.findAll();
        System.out.println("Tous les auteurs :");
        for (Author a : allAuthors) {
            System.out.println(a);
        }
        Author author = new Author(13, "Maharavo", "M");
        authorCrudOperations.save(author);

        List<Author> authorsToSave = new ArrayList<>();
        authorsToSave.add(new Author(14, "Fitahiana", "M"));
        authorsToSave.add(new Author(15, "Harena", "F"));

        List<Author> savedAuthors = authorCrudOperations.saveAll(authorsToSave);
        System.out.println("Auteurs enregistrés :");
        for (Author a : savedAuthors) {
            System.out.println(a);
        }
        Author authorToDelete = author;
        Author deletedAuthor = authorCrudOperations.delete(authorToDelete);
        System.out.println("Auteur supprimé : " + deletedAuthor);
    }
}
