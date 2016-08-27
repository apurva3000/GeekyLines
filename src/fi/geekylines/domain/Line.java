package fi.geekylines.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class Line {

	private String content;
	private String username;
	private String genre;
	private List<String> keywords;
	private Date date_added;
	
	
	public Line(){
		
	}
	public Line(String content, String username, String metadata) {
		super();
		this.content = content;
		this.username = username;
		
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public List<String> getKeywords() {
		
		return keywords;
	}
	public String getKeywordsStr() {
		
		Iterator<String> it = keywords.iterator();
		String keywords_str = "";
		
		while(it.hasNext()){
			keywords_str = keywords_str + "," + it.next();
		}
		keywords_str = keywords_str.substring(1);
		return keywords_str;
	}
	public void setKeywords(String keywords_str) {
		String[] keywords_arr = keywords_str.split(",");
		List<String> keywords = new ArrayList<String>(); 
		for(String keyword : keywords_arr){
			keywords.add(keyword.trim());
		}
		this.keywords = keywords;
	}
	public Date getDate_added() {
		return date_added;
	}
	public void setDate_added(Date date_added) {
		this.date_added = date_added;
	}
}
