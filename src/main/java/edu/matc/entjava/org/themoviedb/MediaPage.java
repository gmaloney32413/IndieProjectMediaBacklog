package edu.matc.entjava.org.themoviedb;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * The type Media page.
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

	public int getPage() { return page; }
	public void setPage(int page) { this.page = page; }

	public int getTotalPages() { return totalPages; }
	public void setTotalPages(int totalPages) { this.totalPages = totalPages; }

	public List<T> getResults() { return results; }
	public void setResults(List<T> results) { this.results = results; }

	public int getTotalResults() { return totalResults; }
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