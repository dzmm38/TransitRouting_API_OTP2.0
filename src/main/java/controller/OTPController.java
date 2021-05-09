package controller;

import org.apache.commons.io.FileUtils;
import org.opentripplanner.standalone.OTPMain;

import java.io.File;
import java.io.IOException;

/**
 * Class to build or Load an existing Graph with OpenTipPlanner
 */
public class OTPController {
    //------------------------------------------ Variable -------------------------------------------//
    //----------------------------------------- Constructor -----------------------------------------//
    //------------------------------------------- Methods -------------------------------------------//

    /**
     * Static Method to build a graph from a gtfs an osm file which needs to be specified by the File Name
     * The Method takes a selected Osm File from resources/Osm Folder and a Gtfs File from resources/GTFS Folder
     * Then builds a Folder to store these Files and the created Graph
     *
     * @param OsmFileName  String: the Name of the Osm File located in resources/Osm to build the street aspect of the graph
     * @param GtfsFileName String: the Name of the Gtfs File located in resources/Gtfs to build the transit aspect of the graph
     * @param FileFolderNr the name of the Folder under resources/var/otp/graphs/ in which the graph should be build and the files stored (names of the folder: please use numbers)
     */
    public static void buildGraph(String OsmFileName, String GtfsFileName, String FileFolderNr) {
        /*
        First for all Parameter we create some Files and especially for the FileFolderNr we further create an Folder in
        which we then store the associated Osm and Gtfs Files.
         */
        File OriginalOsmFile = new File("src\\main\\resources\\OSM\\" + OsmFileName);
        File OrignialGtfsFile = new File("src\\main\\resources\\GTFS\\" + GtfsFileName);
        String directoryPath = "src\\main\\resources\\Graphs\\" + FileFolderNr;                       //TODO: HERE

        File buildDirectory = new File(directoryPath);    //creating the Folder in which the build graph and the selected Osm and Gtfs Files are stored

        if (buildDirectory.exists()) {       //checking if the Folder exists already if true then end the process with an console Output
            System.out.println("The Router Folder: " + FileFolderNr + "already exists");
            System.out.println("Please take another name/number or delete the Folder to create it new");
            return;
        } else {
            buildDirectory.mkdir();     //creating the Folder in which the build graph and the selected Osm and Gtfs Files are stored
        }
        File copiedOsm = new File(directoryPath + "\\" + OsmFileName);      //creating a File for the new Location of the Osm File
        File copiedGtfs = new File(directoryPath + "\\" + GtfsFileName);    //creating a File for the new Location of the Gtfs File

        try {
            FileUtils.copyFile(OriginalOsmFile, copiedOsm);      //copy the original File (Osm) to the new Location from where the graph then can be build with all the associated Files
            FileUtils.copyFile(OrignialGtfsFile, copiedGtfs);    //copy the original File (Gtfs) to the new Location from where the graph then can be build with all the associated Files
        } catch (IOException e) {
            e.printStackTrace();
        }

        String[] buildArgs = {"--build", "src\\main\\resources\\Graphs\\"     //the base location is set here so you only need to have to specify the Folder
                + FileFolderNr, "--save"};     //TODO HERE

        System.out.println("Graph wird in " + FileFolderNr + " wird erstellt und gespeichert.....");

        doAction(buildArgs);       //Calls the main methode from OTP with the instructions what to do
    }

    /**
     * Static Method to load the graph in an grizzly server, from a Folder which contains the already build graph
     * Folder can be found unter resources/var/otp/graphs/
     *
     * @param GraphFolderNr the name of the folder whose graph should be loaded
     *                      Found under: resources/var/otp/graphs/
     */
    public static void loadGraph(String GraphFolderNr) {
        String[] loadGraph = {"--load", "src\\main\\resources\\Graphs\\" + GraphFolderNr, "--port","8086" ,"--securePort", "8087"};       //TODO HERE

        System.out.println("Graph aus dem Ordner " + GraphFolderNr + " wird auf den Server geladen.....");

        doAction(loadGraph);        //Calls the main methode from OTP with the instructions what to do
    }

    /**
     * Method which directly speak with the OTP and pass on the String Array with the instructions to the OTP.main
     *
     * @param commands represents the instructions that tells OpenTripPlanenr what to do
     */
    private static void doAction(String[] commands) {
        OTPMain.main(commands);
    }
    //--------------------------------------- Getter & Setter ---------------------------------------//
    //----------------------------------------- Additional ------------------------------------------//
}
