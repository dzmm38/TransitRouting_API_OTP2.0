package OtpThreadHandling;

import OtpThreadHandling.model.ExampleRoutingRequests;
import OtpThreadHandling.model.RoutingRequest;;
import service.OTPFacade;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Main Class --> Start Point of the Testing Process
 * Unter Settings you can change the variables to adjust the testing settings
 */
public class Main {
    //------------------------------------------ Settings -------------------------------------------//
    int AmountOfThreads = 500;           //TODO: Dann testen mit 100/1tsd/10tsd/etc.
    int ThreadPool = 100;                //Wenn der Thread Pool = Anzahl der Threads dann werden alle gleichzeitig bearbeitet

    String ZoneId = "Europe/Berlin";
    int simulationYear = 2020;
    int simulationMonth = 8;
    int simulationDay = 6;
    int simulationHour = 7;
    int simulationMin = 0;

    //------------------------------------------ Variable -------------------------------------------//
    public ArrayList<RoutingRequest> testingRequests;   //List of all Requests created for testing --- in ExampleRoutingRequests.class
    public OTPFacade facade_class;                      //the PT_Facade_Class used to set the ones in the Threads / which then is used for Routing Methods  [OTP only]

    /**
     * Method to start the Benchmarking / Testing
     * Initialises the OTPFacade_class
     * Creates all testing Requests
     * Creates X Threads every one of them get an random Request out of testing ones.
     * the Thread then is started an routing begins -- after the Routing is done the Requests is written as JSON
     *
     * Creating a Graph is needed to be done separately in a test class
     * as well as starting the separat OTP Server
     * @param args
     */
    //----------------------------------------- Main Method -----------------------------------------//
    public static void main(String[] args) {
        Main main = new Main();

        main.testingRequests = new ExampleRoutingRequests().getTestRequest();   //creates the testingRequests and sets them
        main.OTPHandling(); //creates an initialises a OTPFacade.class //without starting a server [OTP only]

        main.createAndStartTest();    //Test Methode wurde als vorschlag betrachtet wird aber nicht verwendet während der Tests
    }

    //----------------------------------------- Constructor -----------------------------------------//
    //------------------------------------------- Methods -------------------------------------------//
    /*
    Initialises The Facade Class an loads the Graph
     */
    public void OTPHandling(){
        facade_class = new OTPFacade(ZoneId,simulationYear,simulationMonth,simulationDay,simulationHour,simulationMin);
    }

    /*
    Creates then Start the Threads nearly simultaneously
     */
    public void createAndStartTest(){
        ArrayList pickedRouteList = new ArrayList();
        int routeChoice;

        Random rand = new Random();
        ExecutorService executorService = Executors.newFixedThreadPool(ThreadPool);

        for(int i = 0; i<AmountOfThreads; i++) {
            routeChoice = rand.nextInt(10);

            int finalI = i;
            executorService.execute(new RoutingThread(i+1,testingRequests,routeChoice,facade_class));
            pickedRouteList.add(routeChoice++);

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        executorService.shutdown();
        try {
            executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        checkHowOftenWhichRoute2(pickedRouteList);
    }

    /*
    Prints a List which shows how often a Route is picked during the Test
     */
    public void checkHowOftenWhichRoute2(ArrayList<Integer> pickedRouteList){
        int route1 = 0;
        int route2 = 0;
        int route3 = 0;
        int route4 = 0;
        int route5 = 0;
        int route6 = 0;
        int route7 = 0;
        int route8 = 0;
        int route9 = 0;
        int route10 = 0;
        int error = 0;

        for (Integer i: pickedRouteList) {
            switch (i){
                case  0 : route1++; break;
                case  1 : route2++; break;
                case  2 : route3++; break;
                case  3 : route4++; break;
                case  4 : route5++; break;
                case  5 : route6++; break;
                case  6 : route7++; break;
                case  7 : route8++; break;
                case  8 : route9++; break;
                case  9 : route10++; break;
                default: error++; break;
            }
        }

        System.out.println("Printing how often each Route picked in this test");
        System.out.println("------------------------------------------------");
        System.out.println("Routing Request 1 picked: " + route1 +" times");
        System.out.println("Routing Request 2 picked: " + route2 +" times");
        System.out.println("Routing Request 3 picked: " + route3 +" times");
        System.out.println("Routing Request 4 picked: " + route4 +" times");
        System.out.println("Routing Request 5 picked: " + route5 +" times");
        System.out.println("Routing Request 6 picked: " + route6 +" times");
        System.out.println("Routing Request 7 picked: " + route7 +" times");
        System.out.println("Routing Request 8 picked: " + route8 +" times");
        System.out.println("Routing Request 9 picked: " + route9 +" times");
        System.out.println("Routing Request 10 picked: " + route10 +" times");
        System.out.println("Errors occurred: " + error + " times");
        System.out.println("------------------------------------------------");
    }
}
//--------------------------------------- Getter & Setter ---------------------------------------//
//----------------------------------------- Additional ------------------------------------------//
