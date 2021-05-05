package OtpThreadHandling;

import OtpThreadHandling.model.RoutingRequest;
import model.Routemodel.Actions;
import model.Routemodel.Location;
import service.OTPFacade;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 * A Class Representing a Thread with extra information about the Routing Requests
 * which should be calculated
 * and all things necessary for routing
 */
public class RoutingThread implements Runnable {
    //------------------------------------------ Variable -------------------------------------------//
    public RoutingRequest testRequest;      //A random test request (one of 10 different to choose from) --- in ExampleRoutingRequests
    private OTPFacade otpFacade;            //A copy of the PT_Facade_Class for the Routing Method which can called by this thread [OTP only]

    private int threadNumber;               //The number of the Thread which is created
    private String routingName;             //The name describing the Routing request of this thread
    public Thread thread;

    //----------------------------------------- Constructor -----------------------------------------//
    public RoutingThread(int threadNumber, ArrayList<RoutingRequest> testRequestList, int threadSelectionNr,OTPFacade otpFacade){
        testRequest = testRequestList.get(threadSelectionNr);   //sets the request from the testRequestList with the threadSelectionNr (Random form 1 to 10)

        try {
            this.otpFacade = (OTPFacade) otpFacade.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        this.routingName = testRequest.getRoutingName();
        this.threadNumber = threadNumber;

        this.thread = new Thread(this, routingName);    //creates the thread with this class and the routingName
    }

    //------------------------------------------- Methods -------------------------------------------//
    /**
     * The run Method of the RoutingThread class calls the routing Method of the
     * own OTPFacade.class with the given testRequest -- also saves the calculated Route as a
     * JSON File
     */
    @Override
    public void run() {
        //Printing a message to the console with the time the Thread is starting + it´s number and description Name
//        LocalTime startTime = LocalTime.now();
//        System.out.println(startTime + " ---- " + "Starting Thread Nr. " + threadNumber + "  -----  " + "Name: " + routingName);

        //Taking the testRequest and forming all necessary parts for an OTP request
        Location from = testRequest.getFrom();
        Location to = testRequest.getTo();
        LocalDateTime queryTime = testRequest.getQueryTime();
        Actions actions = testRequest.getActions();     //[OTP only]
        int routeAmount = testRequest.getRouteAmount(); //[OTP only]

        //Sending the Request to OTP for routing
        otpFacade.createSimpleRoute(from, to, queryTime, routeAmount, actions);

        //Printing a message to the console with the time the Thread is stopped + it´s number and description
        LocalTime endTime = LocalTime.now();
        System.out.println(LocalTime.now() + " ---- " + "Stopping Thread Nr. " + threadNumber + "  -----  " + "Name: " + routingName);
    }

    //--------------------------------------- Getter & Setter ---------------------------------------//
    public int getThreadNumber() {
        return threadNumber;
    }

    public String getRoutingName() {
        return routingName;
    }
}

    //----------------------------------------- Additional ------------------------------------------//
