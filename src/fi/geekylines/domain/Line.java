package fi.geekylines.domain;

public class Line {

	private String content;
	private String username;
	private String genre;
	private String keywords;
	
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
	public String getKeywords() {
		return keywords;
	}
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
}
