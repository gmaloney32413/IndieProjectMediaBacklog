package edu.matc.entjava.org.themoviedb;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * The type Tv item.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class TVItem {


	@JsonProperty("title")
	private String title;

	@JsonProperty("first_air_date")
	private String firstAirDate;

	@JsonProperty("overview")
	private String overview;

	@JsonProperty("original_language")
	private String originalLanguage;

	@JsonProperty("genre_ids")
	private List<Integer> genreIds;

	@JsonProperty("poster_path")
	private String posterPath;

	@JsonProperty("origin_country")
	private List<String> originCountry;

	@JsonProperty("backdrop_path")
	private String backdropPath;

	@JsonProperty("media_type")
	private String mediaType;

	@JsonProperty("original_name")
	private String originalName;

	@JsonProperty("popularity")
	private Object popularity;

	@JsonProperty("vote_average")
	private Object voteAverage;

	@JsonProperty("name")
	private String name;

	@JsonProperty("id")
	private int id;

	@JsonProperty("adult")
	private boolean adult;

	@JsonProperty("vote_count")
	private int voteCount;

	/**
	 * Set title.
	 *
	 * @param title the title
	 */
	public void setTitle(String title){
		this.title = title;
	}

	/**
	 * Get title string.
	 *
	 * @return the string
	 */
	public String getTitle(){
		return title;
	}

	/**
	 * Set first air date.
	 *
	 * @param firstAirDate the first air date
	 */
	public void setFirstAirDate(String firstAirDate){
		this.firstAirDate = firstAirDate;
	}

	/**
	 * Get first air date string.
	 *
	 * @return the string
	 */
	public String getFirstAirDate(){
		return firstAirDate;
	}

	/**
	 * Set overview.
	 *
	 * @param overview the overview
	 */
	public void setOverview(String overview){
		this.overview = overview;
	}

	/**
	 * Get overview string.
	 *
	 * @return the string
	 */
	public String getOverview(){
		return overview;
	}

	/**
	 * Set original language.
	 *
	 * @param originalLanguage the original language
	 */
	public void setOriginalLanguage(String originalLanguage){
		this.originalLanguage = originalLanguage;
	}

	/**
	 * Get original language string.
	 *
	 * @return the string
	 */
	public String getOriginalLanguage(){
		return originalLanguage;
	}

	/**
	 * Set genre ids.
	 *
	 * @param genreIds the genre ids
	 */
	public void setGenreIds(List<Integer> genreIds){
		this.genreIds = genreIds;
	}

	/**
	 * Get genre ids list.
	 *
	 * @return the list
	 */
	public List<Integer> getGenreIds(){
		return genreIds;
	}

	/**
	 * Set poster path.
	 *
	 * @param posterPath the poster path
	 */
	public void setPosterPath(String posterPath){
		this.posterPath = posterPath;
	}

	/**
	 * Get poster path string.
	 *
	 * @return the string
	 */
	public String getPosterPath(){
		return posterPath;
	}

	/**
	 * Set origin country.
	 *
	 * @param originCountry the origin country
	 */
	public void setOriginCountry(List<String> originCountry){
		this.originCountry = originCountry;
	}

	/**
	 * Get origin country list.
	 *
	 * @return the list
	 */
	public List<String> getOriginCountry(){
		return originCountry;
	}

	/**
	 * Set backdrop path.
	 *
	 * @param backdropPath the backdrop path
	 */
	public void setBackdropPath(String backdropPath){
		this.backdropPath = backdropPath;
	}

	/**
	 * Get backdrop path string.
	 *
	 * @return the string
	 */
	public String getBackdropPath(){
		return backdropPath;
	}

	/**
	 * Set media type.
	 *
	 * @param mediaType the media type
	 */
	public void setMediaType(String mediaType){
		this.mediaType = mediaType;
	}

	/**
	 * Get media type string.
	 *
	 * @return the string
	 */
	public String getMediaType(){
		return mediaType;
	}

	/**
	 * Set original name.
	 *
	 * @param originalName the original name
	 */
	public void setOriginalName(String originalName){
		this.originalName = originalName;
	}

	/**
	 * Get original name string.
	 *
	 * @return the string
	 */
	public String getOriginalName(){
		return originalName;
	}

	/**
	 * Set popularity.
	 *
	 * @param popularity the popularity
	 */
	public void setPopularity(Object popularity){
		this.popularity = popularity;
	}

	/**
	 * Get popularity object.
	 *
	 * @return the object
	 */
	public Object getPopularity(){
		return popularity;
	}

	/**
	 * Set vote average.
	 *
	 * @param voteAverage the vote average
	 */
	public void setVoteAverage(Object voteAverage){
		this.voteAverage = voteAverage;
	}

	/**
	 * Get vote average object.
	 *
	 * @return the object
	 */
	public Object getVoteAverage(){
		return voteAverage;
	}

	/**
	 * Set name.
	 *
	 * @param name the name
	 */
	public void setName(String name){
		this.name = name;
	}

	/**
	 * Get name string.
	 *
	 * @return the string
	 */
	public String getName(){
		return name;
	}

	/**
	 * Set id.
	 *
	 * @param id the id
	 */
	public void setId(int id){
		this.id = id;
	}

	/**
	 * Get id int.
	 *
	 * @return the int
	 */
	public int getId(){
		return id;
	}

	/**
	 * Set adult.
	 *
	 * @param adult the adult
	 */
	public void setAdult(boolean adult){
		this.adult = adult;
	}

	/**
	 * Is adult boolean.
	 *
	 * @return the boolean
	 */
	public boolean isAdult(){
		return adult;
	}

	/**
	 * Set vote count.
	 *
	 * @param voteCount the vote count
	 */
	public void setVoteCount(int voteCount){
		this.voteCount = voteCount;
	}

	/**
	 * Get vote count int.
	 *
	 * @return the int
	 */
	public int getVoteCount(){
		return voteCount;
	}

	@Override
	public String toString(){
		return
				"ResultsItem{" +
						"first_air_date = '" + firstAirDate + '\'' +
						",overview = '" + overview + '\'' +
						",original_language = '" + originalLanguage + '\'' +
						",title = '" + title + '\'' +
						",genre_ids = '" + genreIds + '\'' +
						",poster_path = '" + posterPath + '\'' +
						",origin_country = '" + originCountry + '\'' +
						",backdrop_path = '" + backdropPath + '\'' +
						",media_type = '" + mediaType + '\'' +
						",original_name = '" + originalName + '\'' +
						",popularity = '" + popularity + '\'' +
						",vote_average = '" + voteAverage + '\'' +
						",name = '" + name + '\'' +
						",id = '" + id + '\'' +
						",adult = '" + adult + '\'' +
						",vote_count = '" + voteCount + '\'' +
						"}";
	}
}
