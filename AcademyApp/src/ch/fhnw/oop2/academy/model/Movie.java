package ch.fhnw.oop2.academy.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Entity movie class
 * 
 * Representation of the movie entity which holds all informations
 * for a specific movie object.
 * 
 * @author kevin.kirn@students.fhnw.ch
 * @author roman.dyck@students.fhnw.ch
 */
public class Movie {

	private IntegerProperty movieNr;
	private StringProperty yearOfAward;
	private StringProperty playingTime;
	private StringProperty fsk;
	private StringProperty title; 
	private StringProperty originalTitle;
	private StringProperty director;
	private StringProperty genre;
	private StringProperty country;
	private StringProperty coverImage;
	private StringProperty actors;
	private StringProperty productionYear;
	private StringProperty startDate;
	private StringProperty oscars;
	
	public Movie(int newMovieNr){
		
		// initialize properties since they are objects
		movieNr = new SimpleIntegerProperty(newMovieNr);
		yearOfAward = new SimpleStringProperty("");
		playingTime = new SimpleStringProperty("");
		fsk = new SimpleStringProperty("");
		title = new SimpleStringProperty("");
		productionYear = new SimpleStringProperty("");
		originalTitle = new SimpleStringProperty("");
		director = new SimpleStringProperty("");
		genre = new SimpleStringProperty("");
		country = new SimpleStringProperty("");
		coverImage = new SimpleStringProperty("");
		actors = new SimpleStringProperty("");
		startDate = new SimpleStringProperty("");
		oscars = new SimpleStringProperty("");
	}
	
	/*-----------------------------------------
	 * Getter & Setter for member variables
	 -----------------------------------------*/
	
	public int getMovieNr() {
		return movieNr.get();
	}

	public void setMovieNr(int movieNr) {
		this.movieNr.set(movieNr);
	}

	public StringProperty getProductionYear() {
		return productionYear;
	}

	public void setProductionYear(String publishYear) {
		this.productionYear.set(publishYear);
	}

	public StringProperty getYearOfAward() {
		return yearOfAward;
	}

	public void setYearOfAward(String productionYear) {
		this.yearOfAward.set(productionYear);
	}

	public StringProperty getPlayingTime() {
		return playingTime;
	}

	public void setPlayingTime(String playingTime) {
		this.playingTime.set(playingTime);
	}

	public StringProperty getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title.set(title);
	}

	public StringProperty  getOriginalTitle() {
		return originalTitle;
	}

	public void setOriginalTitle(String originalTitle) {
		this.originalTitle.set(originalTitle);
	}

	public StringProperty  getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director.set(director);
	}

	public StringProperty  getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre.set(genre);
	}

	public StringProperty  getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country.set(country);
	}

	public StringProperty  getCoverImage() {
		return coverImage;
	}

	public void setCoverImage(String coverImage) {
		this.coverImage.set(coverImage);
	}

	public StringProperty getActors() {
		return actors;
	}

	public void setActors(String actors) {
		this.actors.set(actors);
	}

	public StringProperty getFsk() {
		return fsk;
	}

	public void setFsk(String fsk) {
		this.fsk.set(fsk);
	}

	public StringProperty getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate.set(startDate);
	}
	
	public StringProperty getOscars() {
		return this.oscars;
	}

	public void setOscars(String _oscars) {
		this.oscars.set(_oscars);
	}
	
	/**
	 * serialization method for object
	 * target: csv 
	 */
	@Override
	public String toString(){
		
		String serialization = 	getMovieNr() + ";" +
								getTitle().get() + ";" +
								getYearOfAward().get() + ";" +
								getDirector().get() + ";" +
								getActors().get() + ";" +
								getOriginalTitle().get() + ";" +
								getProductionYear().get() + ";" +
								getCountry().get() + ";" +
								getPlayingTime().get() + ";" +
								getFsk().get() + ";" +
								getGenre().get() + ";" +
								getStartDate().get() + ";" +
								getOscars().get();
		
		return serialization;
	}
	
	/**
	 * Helper function to make sure data is correct
	 */
	public boolean isValid(){
		if(getTitle().get() != null &&
		   getYearOfAward().get() != null){
			return true;
		}
		else{
			return false;
		}
	}
}
