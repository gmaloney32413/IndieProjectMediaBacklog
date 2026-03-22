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

	public void setFirstAirDate(String firstAirDate){
		this.firstAirDate = firstAirDate;
	}

	public String getFirstAirDate(){
		return firstAirDate;
	}

	public void setOverview(String overview){
		this.overview = overview;
	}

	public String getOverview(){
		return overview;
	}

	public void setOriginalLanguage(String originalLanguage){
		this.originalLanguage = originalLanguage;
	}

	public String getOriginalLanguage(){
		return originalLanguage;
	}

	public void setGenreIds(List<Integer> genreIds){
		this.genreIds = genreIds;
	}

	public List<Integer> getGenreIds(){
		return genreIds;
	}

	public void setPosterPath(String posterPath){
		this.posterPath = posterPath;
	}

	public String getPosterPath(){
		return posterPath;
	}

	public void setOriginCountry(List<String> originCountry){
		this.originCountry = originCountry;
	}

	public List<String> getOriginCountry(){
		return originCountry;
	}

	public void setBackdropPath(String backdropPath){
		this.backdropPath = backdropPath;
	}

	public String getBackdropPath(){
		return backdropPath;
	}

	public void setMediaType(String mediaType){
		this.mediaType = mediaType;
	}

	public String getMediaType(){
		return mediaType;
	}

	public void setOriginalName(String originalName){
		this.originalName = originalName;
	}

	public String getOriginalName(){
		return originalName;
	}

	public void setPopularity(Object popularity){
		this.popularity = popularity;
	}

	public Object getPopularity(){
		return popularity;
	}

	public void setVoteAverage(Object voteAverage){
		this.voteAverage = voteAverage;
	}

	public Object getVoteAverage(){
		return voteAverage;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setAdult(boolean adult){
		this.adult = adult;
	}

	public boolean isAdult(){
		return adult;
	}

	public void setVoteCount(int voteCount){
		this.voteCount = voteCount;
	}

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
