package ch.fhnw.oop2.academy.controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;

import com.opencsv.CSVReader;

import ch.fhnw.oop2.academy.model.Movie;

/**
 * Movie controller
 * 
 * Manager for movie entities, such as initialize, update, save data.
 * 
 * @author kevin.kirn@students.fhnw.ch
 * @author roman.dyck@students.fhnw.ch
 */
public final class MovieController {

	// define where the data csv is stored
	private static String pathToCSV = "resources/data/movies.csv";
	
	private static ObservableList<Movie> movieData = FXCollections.observableArrayList();
	
	// Constructor
	public MovieController(){
		
		loadMovies(); // init movie list
	}
	
	/**
     * Read movies from file and return them as an list
     * 
     * @return ObservableList<Movie>
     */
	public static ObservableList<Movie> loadMovies(){
		
		// define csv column indexes
		final int MOVIE_ID = 0;
		final int MOVIE_TITLE = 1;
		final int MOVIE_YEAR_OF_AWARD = 2;
		final int MOVIE_DIRECTOR = 3;
		final int MOVIE_MAIN_ACTOR = 4;
		final int MOVIE_TITLE_ENGLISH = 5;
		final int MOVIE_YEAR_OF_PRODUCTION = 6;
		final int MOVIE_COUNTRY = 7;	
		final int MOVIE_DURATION = 8;	
		final int MOVIE_FSK = 9;	
		final int MOVIE_GENRE = 10;	
		final int MOVIE_START_DATE = 11;
		final int MOVIE_OSCARS = 12;
		
		try {
			
			// load data csv with a CSVRader
			CSVReader reader = new CSVReader(new InputStreamReader(new FileInputStream(pathToCSV), "UTF-8"), ';');
			
			// define a line holder
			String [] nextLine;
			
			// read lines of movies
			while ((nextLine = reader.readNext()) != null) {
		
				// a valid line contains 13 columns, so ignore invalid cols!
				if(nextLine.length == 13){
					
					// initialize new movie out of line
					Movie tmpMovie = new Movie(0);
					
					// set data for movie object
					tmpMovie.setMovieNr(Integer.parseInt(nextLine[MOVIE_ID]));
					tmpMovie.setTitle(nextLine[MOVIE_TITLE]);
					tmpMovie.setYearOfAward(nextLine[MOVIE_YEAR_OF_AWARD]);
					tmpMovie.setDirector(nextLine[MOVIE_DIRECTOR]);
					tmpMovie.setActors(nextLine[MOVIE_MAIN_ACTOR]);
					tmpMovie.setOriginalTitle(nextLine[MOVIE_TITLE_ENGLISH]);
					tmpMovie.setProductionYear(nextLine[MOVIE_YEAR_OF_PRODUCTION]);
					tmpMovie.setCountry(nextLine[MOVIE_COUNTRY]);
					tmpMovie.setPlayingTime(nextLine[MOVIE_DURATION]);
					tmpMovie.setFsk(nextLine[MOVIE_FSK]);
					tmpMovie.setGenre(nextLine[MOVIE_GENRE]);
					tmpMovie.setStartDate(nextLine[MOVIE_START_DATE]);
					tmpMovie.setOscars(nextLine[MOVIE_OSCARS]);
					
					// just make sure data is valid
					if(tmpMovie.isValid())
						movieData.add(tmpMovie);
				}
					
			}
			
			// close reader, don't need it anymore
			reader.close();
			
			return movieData;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	/**
	 * Save movie list to file
	 */
	public static void saveMovies(){
		
		try {
			
			// load movie file
			File fout = new File(pathToCSV);
			FileOutputStream fos = new FileOutputStream(fout);
		 
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
			
			// write movies
			for(Iterator<Movie> i = movieData.iterator(); i.hasNext(); ) {
			    Movie item = i.next();
			    bw.write(item.toString());
			    bw.newLine();
			}
			
			bw.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	/**
	 * Delete movie from the observed movie list
	 * 
	 * @param int selectedIndex
	 */
	public static void deleteMovie(int selectedIndex){
		movieData.remove(selectedIndex);
	}
	
	/**
	 * Add a new movie to the static observed movie list
	 */
	public static void addMovie(){
		
		// find greatest movie id
		final Comparator<Movie> comp = (p1, p2) -> Integer.compare( p1.getMovieNr(), p2.getMovieNr());
		int maxID = movieData.stream().max(comp).get().getMovieNr();

		movieData.add(new Movie(++maxID));
	}

	/**
	 * Find image resource for given movie by movieNr
	 * 
	 * @param movieNr
	 * @return Image of the Movie
	 */
	public static Image getImageForMovie(int movieNr)
	{
		return new Image("file:resources/poster/" + movieNr + ".jpg");
	}

	/**
	 * Itentify country flags of the given movie and return them to view controller
	 * 
	 * @param Movie item
	 * @return ArrayList<Image> flagIcons
	 */
	public static ArrayList<Image> getCountryFlags(Movie item) {
		
		try {
			
			// get all flag identifiers
			String flags = item.getCountry().get();
			
			// get each stored flag id
			String flagArray[] = flags.split("/");
			
			// build a image array which contains all images
			ArrayList<Image> flagIcons = new ArrayList<Image>();
			
			for( int i = 0; i < flagArray.length; i++)
			{
			    String flagID = flagArray[i];
			    
			    // load and add image to array
			    flagIcons.add(new Image("file:resources/flags_iso/24/" + flagID + ".png"));
			}
			
			return flagIcons;
			
		} catch (Exception e) {
			e.printStackTrace();
			
			return null;
		}
	}
	
}
