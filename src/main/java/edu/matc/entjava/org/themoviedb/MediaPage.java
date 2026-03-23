package edu.matc.entjava.org.themoviedb;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * The type Media page.
 *
 * @param <T> the type parameter
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class MediaPage<T> {

	@JsonProperty("page")
	private int page;

	@JsonProperty("total_pages")
	private int totalPages;

	@JsonProperty("results")
	private List<T> results;

	@JsonProperty("total_results")
	private int totalResults;

	/**
	 * Gets page.
	 *
	 * @return the page
	 */
	public int getPage() { return page; }

	/**
	 * Sets page.
	 *
	 * @param page the page
	 */
	public void setPage(int page) { this.page = page; }

	/**
	 * Gets total pages.
	 *
	 * @return the total pages
	 */
	public int getTotalPages() { return totalPages; }

	/**
	 * Sets total pages.
	 *
	 * @param totalPages the total pages
	 */
	public void setTotalPages(int totalPages) { this.totalPages = totalPages; }

	/**
	 * Gets results.
	 *
	 * @return the results
	 */
	public List<T> getResults() { return results; }

	/**
	 * Sets results.
	 *
	 * @param results the results
	 */
	public void setResults(List<T> results) { this.results = results; }

	/**
	 * Gets total results.
	 *
	 * @return the total results
	 */
	public int getTotalResults() { return totalResults; }

	/**
	 * Sets total results.
	 *
	 * @param totalResults the total results
	 */
	public void setTotalResults(int totalResults) { this.totalResults = totalResults; }

	@Override
 	public String toString(){
		return 
			"MediaPage{" + 
			"page = '" + page + '\'' + 
			",total_pages = '" + totalPages + '\'' + 
			",results = '" + results + '\'' + 
			",total_results = '" + totalResults + '\'' + 
			"}";
		}
}