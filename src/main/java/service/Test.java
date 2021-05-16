package service;

import model.Routemodel.Actions;
import model.Routemodel.Location;
import java.time.LocalDateTime;

public class Test {

    public static void main(String[] args) {
        runFriedberg();
    }

    public static void runFriedberg () {
        //OTPFacade.buildGraph("friedberg.pbf", "gtfs_friedberg.zip", "1");
        OTPFacade otp = new OTPFacade("Europe/Berlin", 2021, 4, 30, 10, 0);
        Location from = new Location(50.43280, 8.66358);
        Location to = new Location(50.33108, 8.75919);
        LocalDateTime queryTime = LocalDateTime.of(2021, 4, 30, 10, 0);
        otp.createSimpleRoute(from, to, queryTime,1, Actions.JSONOBEJCT_AS_JSON);
    }
}
