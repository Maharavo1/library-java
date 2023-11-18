package Test;

import Models.Subscribers;
import Repository.SubscribersCrudOperations;

import java.util.ArrayList;
import java.util.List;

public class SubscribersCrudTest {
    public static void SubscribersTest() {
        SubscribersCrudOperations subscribersCrudOperations = new SubscribersCrudOperations();

        SubscribersCrudOperations.getConnection();

        Subscribers subscribers1 = new Subscribers(7, "Naharavo", "REF3");
        Subscribers subscribers2 = new Subscribers(8, "Baba", "REF5");
        Subscribers subscribers3 = new Subscribers(9, "Be", "REF6");

        System.out.println("Tous les abonnés :");
        subscribersCrudOperations.findAll();
        subscribersCrudOperations.save(subscribers1);
        System.out.println("Abonné enregistré : " + subscribers1);

        List<Subscribers> subscribersToSave = new ArrayList<>();
        subscribersToSave.add(subscribers2);
        subscribersToSave.add(subscribers3);
        System.out.println("Enregistrement des abonnés :");
        for (Subscribers subscriber : subscribersToSave) {
            System.out.println(subscriber);
        }
        List<Subscribers> savedSubscribers = subscribersCrudOperations.saveAll(subscribersToSave);
        System.out.println("Abonnés enregistrés :");
        for (Subscribers savedSubscriber : savedSubscribers) {
            System.out.println(savedSubscriber);
        }

        Subscribers subscriberToDelete = subscribers1;
        System.out.println("Suppression de l'abonné : " + subscriberToDelete);
        Subscribers deletedSubscriber = subscribersCrudOperations.delete(subscriberToDelete);
        System.out.println("Abonné supprimé : " + deletedSubscriber);
    }
}
