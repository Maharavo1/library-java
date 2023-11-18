import Test.AuthorCrudTest;
import Test.BookCrudTest;
import Test.SubscribersCrudTest;

public class Main {
    public static void main(String[] args) {
        AuthorCrudTest.AuthorTest();
        BookCrudTest.BookTest();
        SubscribersCrudTest.SubscribersTest();
        }
    }
