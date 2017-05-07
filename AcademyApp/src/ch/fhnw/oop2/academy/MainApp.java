package ch.fhnw.oop2.academy;

import java.io.IOException;

import ch.fhnw.oop2.academy.view.MovieOverviewController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * Main entry point
 * 
 * Main entry point of the AcademyApp, everything starts here.
 * 
 * @author kevin.kirn@students.fhnw.ch
 * @author roman.dyck@students.fhnw.ch
 */
public class MainApp extends Application {

	// define used member variables
	private Stage primaryStage;
    private BorderPane rootLayout;
	
	 /**
	 * Main method which starts the life circle
	 *  
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Application.launch(MainApp.class, (java.lang.String[])null);
    }

    @Override
    /**
     * Main entry point of JavaFX
     * 
     * -> initialize application here
     */
    public void start(Stage primaryStage) {
    	this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Academy App - Kevin Kirn, Roman Dyck - FHNW FS15 oop2");
    	
    	initRootLayout();
    }
    
    /**
     * Initializes the root layout.
     */
    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/MainView.fxml"));

            rootLayout = (BorderPane) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
            
            // initialize movie controller
            MovieOverviewController controller = loader.getController();
            controller.loadTable();
            controller.selectIndex(0);
           
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
