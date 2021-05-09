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

    //----------------------------------------- Constructor -----------------------------------------//
    public RoutingThread(int threadNumber,RoutingRequest testRequest,OTPFacade otpFacade){
        this.testRequest = testRequest;   //sets the request from the testRequestList with the threadSelectionNr (Random form 1 to 10)
        this.otpFacade = new OTPFacade(otpFacade);

        this.threadNumber = threadNumber;
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
//        LocalTime starttime = LocalTime.now();
//        System.out.println(starttime + " ---- " + "Starting Thread Nr. " + threadNumber + "  -----  " + "Name: " + testRequest.getRoutingName());

        //Taking the testRequest and forming all necessary parts for an OTP request
        Location from = testRequest.getFrom();
        Location to = testRequest.getTo();
        LocalDateTime queryTime = testRequest.getQueryTime();
        Actions actions = testRequest.getActions();     //[OTP only]
        int routeAmount = testRequest.getRouteAmount(); //[OTP only]

        //Sending the Request to OTP for routing
        otpFacade.createSimpleRoute(from, to, queryTime, routeAmount, actions);

        //Printing a message to the console with the time the Thread is stopped + it´s number and description
        System.out.println(LocalTime.now() + " ---- " + "Stopping Thread Nr. " + threadNumber + "  -----  " + "Name: " + testRequest.getRoutingName());
    }

    //--------------------------------------- Getter & Setter ---------------------------------------//
    public int getThreadNumber() {
        return threadNumber;
    }

    public String getRoutingName() {
        return testRequest.getRoutingName();
    }
}

    //----------------------------------------- Additional ------------------------------------------//
