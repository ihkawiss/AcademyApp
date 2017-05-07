package ch.fhnw.oop2.academy.view;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

import com.aquafx_project.AquaFx;

import javafx.scene.control.TextField;
import ch.fhnw.oop2.academy.controller.MovieController;
import ch.fhnw.oop2.academy.model.Movie;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;	
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.text.Text;


/**
 * Entity movie
 * 
 * Representation of the movie entity which holds all informations
 * for a specific movie object.
 * 
 * @author kevin.kirn@students.fhnw.ch
 * @author roman.dyck@students.fhnw.ch
 */
public class MovieOverviewController {

	@FXML
    private TableView<Movie> movieTable;
	@FXML
    private TableColumn<Movie, String> yearColumn;
    @FXML
    private TableColumn<Movie, String> titleColumn;
    @FXML
    private TableColumn<Movie, String> directorColumn;
    @FXML
    private TableColumn<Movie, String> actorColumn;
    @FXML
    private TextField txtYearField, txtTitleField, txtReleaseDate, txtOscars;
    @FXML
    private TextField txtDirectorField, txtActorField, txtOriginalTileField, txtGenreField, txtCountryField, txtFSKField, txtYearOfProductionField, txtPlayingTimeField;
    @FXML
    private Text lblTitle;
    @FXML
    private Text lblYear;
    @FXML
    private ImageView imgPoster;
    @FXML
    private Button btnDelete, btnAdd, btnSave, btnLookAndFeel;
    @FXML
    private Text lblVon, lblMit;
    @FXML
    private HBox flagsBox, oscarsHBox;
    @FXML
    private MediaView mediaView;
    
    private Movie selectedItem; 
    
    private MediaPlayer mediaPlayer;
    
    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public MovieOverviewController() {

    }
    
    @FXML
    private void initialize() {
    	
    	// define table columns
    	yearColumn.setCellValueFactory(cellData -> cellData.getValue().getYearOfAward());
    	titleColumn.setCellValueFactory(cellData -> cellData.getValue().getTitle());
    	directorColumn.setCellValueFactory(cellData -> cellData.getValue().getDirector());
    	actorColumn.setCellValueFactory(cellData -> cellData.getValue().getActors());
  	
    }
    
    @FXML
    private void handleMovieSelected() {
    	
    	int selectedIndex = movieTable.getSelectionModel().getSelectedIndex();
    	Movie selected = movieTable.getItems().get(selectedIndex);
    	
    	// remove existing bindings on other objects
    	if(selectedItem != null){
    		txtTitleField.textProperty().unbindBidirectional(selectedItem.getTitle());
    		lblTitle.textProperty().unbindBidirectional(selectedItem.getTitle());
    		txtYearField.textProperty().unbindBidirectional(selectedItem.getYearOfAward());
    		lblYear.textProperty().unbindBidirectional(selectedItem.getYearOfAward());
    		txtDirectorField.textProperty().unbindBidirectional(selectedItem.getDirector());
    		txtActorField.textProperty().unbindBidirectional(selectedItem.getActors());
    		txtOriginalTileField.textProperty().unbindBidirectional(selectedItem.getOriginalTitle());
    		txtGenreField.textProperty().unbindBidirectional(selectedItem.getGenre());
        	txtFSKField.textProperty().unbindBidirectional(selectedItem.getFsk());
        	txtYearOfProductionField.textProperty().unbindBidirectional(selectedItem.getProductionYear());
        	txtReleaseDate.textProperty().unbindBidirectional(selectedItem.getStartDate());
        	txtCountryField.textProperty().unbindBidirectional(selectedItem.getCountry());
        	txtOscars.textProperty().unbindBidirectional(selectedItem.getOscars());
    	}
    		
    	// define bindings for selected object
    	txtTitleField.textProperty().bindBidirectional(selected.getTitle());
    	lblTitle.textProperty().bindBidirectional(selected.getTitle());
    	txtYearField.textProperty().bindBidirectional(selected.getYearOfAward());
    	lblYear.textProperty().bindBidirectional(selected.getYearOfAward());
    	txtDirectorField.textProperty().bindBidirectional(selected.getDirector());
    	txtActorField.textProperty().bindBidirectional(selected.getActors());
    	txtOriginalTileField.textProperty().bindBidirectional(selected.getOriginalTitle());
    	txtGenreField.textProperty().bindBidirectional(selected.getGenre());
    	txtFSKField.textProperty().bindBidirectional(selected.getFsk());
    	txtYearOfProductionField.textProperty().bindBidirectional(selected.getProductionYear());
    	txtPlayingTimeField.textProperty().bindBidirectional(selected.getPlayingTime());
    	txtReleaseDate.textProperty().bindBidirectional(selected.getStartDate());
    	txtCountryField.textProperty().bindBidirectional(selected.getCountry());
    	txtOscars.textProperty().bindBidirectional(selected.getOscars());
    	
    	imgPoster.setImage(MovieController.getImageForMovie(selected.getMovieNr()));
    	
    	lblVon.textProperty().bind(selected.getDirector());
    	lblMit.textProperty().bind(selected.getActors());
    	
    	// add country flags
    	addCountryFlags(selected);
    	
    	// add oscar icons
    	addOscarIcons(selected);
    	
    	// init trailer movie
    	setTrailer(selected);
    	
    	// persist selected movie to handle next selection
    	selectedItem = selected;

    }
    
    /**
     * Method which loads table item from MovieController
     */
    public void loadTable(){
    	movieTable.setItems(MovieController.loadMovies());
    }
    
    /**
     * Helper method to select a index in the TableView
     */
    public void selectIndex(int index)
    {
    	movieTable.getSelectionModel().select(index);
    	
    	// refresh detail view
    	handleMovieSelected();
    }
    
    @FXML
    private void deleteAction() {	// delete movie action, called from button delete
    	
    	// just remove the selected movie from the list
    	// changes will only be saved on save action!
    	// call movie controller to do this work
    	MovieController.deleteMovie(movieTable.getSelectionModel().getSelectedIndex());
    	
    	// update selection
    	handleMovieSelected();
    }
    
    @FXML
    private void addAction() {		// add movie action, called from button add
    	
    	// just add the movie to the list
    	// changes will only be saved on save action!
    	// call movie controller to do this work
    	MovieController.addMovie();
    	
    	// update selection
    	movieTable.getSelectionModel().selectLast();
    	handleMovieSelected();
    }
    
    @FXML
    private void saveAction(){		// save movie action, called from button save
    	
    	// call movie controller to do this work
    	MovieController.saveMovies();
    	
    	// give some user feedback
    	// TODO: show modal window
    	// funktionierte leider nicht sofort wie gewünscht
    }
    
    /**
     * Set AquaFX as Look and Feel
     */
    @FXML void changeLookAndFeel(){
    	
    	AquaFx.style();
    	btnLookAndFeel.setText("App neu starten für Default-Style");
    	btnLookAndFeel.setDisable(true);
    	
    }
    
    /**
     * Add country flags to detail pane for given movie item
     * 
     * @param Movie item
     */
    private void addCountryFlags(Movie item){
    	
    	flagsBox.getChildren().clear(); // clear all contents first
    	
    	ArrayList<Image> images = MovieController.getCountryFlags(item);
    	
    	for(Iterator<Image> i = images.iterator(); i.hasNext(); ) {
		    ImageView tmpFlagView = new ImageView();
		    tmpFlagView.setImage(i.next());
	        flagsBox.getChildren().add(tmpFlagView);
		}
    }
    
    /**
     * Add oscar icons to view according given movie item
     * 
     * @param Movie item
     */
    private void addOscarIcons(Movie item){
    	
    	// clear all contents first
    	oscarsHBox.getChildren().clear();
    	
    	// get oscar count
    	int oscarsReceived = 1;
    	if(item.getOscars().get() != null && item.getOscars().get() != "")
    		oscarsReceived = Integer.parseInt(item.getOscars().get());
    	
    	// add for each oscar an icon
    	for(int i = 0; i < oscarsReceived; i++){
    		ImageView tmpOscarView = new ImageView();
    		tmpOscarView.setImage(new Image("file:resources/Oscar-logo.jpg"));
		    oscarsHBox.getChildren().add(tmpOscarView);
    	}
    	
    }
    
    /**
     * Listen for oscar count changed.
     * 
     * Update amount of oscar icons on change
     */
    @FXML
    private void oscarsCountChanged()
    {
    	// check if value is numeric and not null
    	if(txtOscars.getText() != null && 
    			txtOscars.getText() != "" && 
    			txtOscars.getText().matches("[-+]?\\d*\\.?\\d+") &&
    			Integer.parseInt(txtOscars.getText()) < 10){
    		
    		int selectedIndex = movieTable.getSelectionModel().getSelectedIndex();
        	Movie selected = movieTable.getItems().get(selectedIndex);
        	addOscarIcons(selected);
    	}
    }
    
    /**
     * Get the movie from resources and add it to view
     * 
     * @param Movie item
     */
    private void setTrailer(Movie item){
    	
    	// does file exist?
    	File trailer = new File("resources/trailer/" + item.getYearOfAward().get() + ".mp4");
    	
    	if(trailer.exists() && !trailer.isDirectory()) {
    		
    		// stop media if player exists
    		if(mediaPlayer != null)
        		mediaPlayer.stop();
    		
    		Media media = new Media(trailer.toURI().toString());
        	this.mediaPlayer = new MediaPlayer(media);
        	this.mediaPlayer.setAutoPlay(true);
        	
        	mediaView.setMediaPlayer(this.mediaPlayer);
        	
        	this.mediaPlayer.play();
    	}

    }
}
