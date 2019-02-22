package main;

import model.*;

import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Customer seller = new Customer("Jan Kowalski");
        Auction auction = new Auction.AuctionBuilder()
                .withId(1)
                .withItem("Coffee")
                .withDescription(null)
                .withLocalDateTime(LocalDateTime.now().plusDays(2L))
                .withSeller(seller)
                .withHighestBid(0)
                .withBuyer(null)
                .build();

        // new Auction(1,"Coffee",null,
        // LocalDateTime.now().plusDays(2L),seller,0,null);
        AuctionNotifier auctionNotifier = new AuctionNotifierImp();
        Customer customerOne = new Customer("Anna Kowalik");
        Customer customerTwo = new Customer("Krzysztof Adamowicz");
        Customer customerThree = new Customer("Krzysztof Kolumb");
        auctionNotifier.registerObserver(customerOne);
        auctionNotifier.registerObserver(customerTwo);
        auctionNotifier.registerObserver(customerThree);

        AuctionService auctionService = AuctionService.getInstance();
        auctionService.bid(auction, customerOne, 20);
        auctionService.bid(auction, customerTwo, 25);
        auctionService.bid(auction, customerThree, 30);

        auctionNotifier.notifyObservers();

        auctionService.bid(auction, customerTwo, 35);
        auctionNotifier.notifyObservers();
        customerOne.printNotifications();
        customerThree.printNotifications();
        customerTwo.printNotifications();

        System.out.println("Auction with id: " + auction.getId() + " finished. Highest bid: " + auction.getHighestBid() + " PLN.");
    }
}
