package fi.geekylines.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.stereotype.Service;

import com.google.appengine.api.NamespaceManager;
import com.google.appengine.api.search.Document;
import com.google.appengine.api.search.Field;
import com.google.appengine.api.search.Index;
import com.google.appengine.api.search.IndexSpec;
import com.google.appengine.api.search.PutException;
import com.google.appengine.api.search.Results;
import com.google.appengine.api.search.ScoredDocument;
import com.google.appengine.api.search.SearchService;
import com.google.appengine.api.search.SearchServiceConfig;
import com.google.appengine.api.search.SearchServiceFactory;
import com.google.appengine.api.search.StatusCode;

import fi.geekylines.controller.Worker;
import fi.geekylines.domain.Line;


@Service
public class SearchingService {

	private String INDEX_NAME = "geekyindex";
	private IndexSpec indexSpec;
	private SearchService service;
	private Index index;
	
	private static final Logger log = Logger.getLogger(SearchingService.class.getName());
	
	public SearchingService(){
		
		indexSpec = IndexSpec.newBuilder().setName(INDEX_NAME).build();
		service = SearchServiceFactory.getSearchService(
		       SearchServiceConfig.newBuilder().setDeadline(10.0).setNamespace("").build());
		index = service.getIndex(indexSpec);
	}
	public List<Line> query_index(String query){

		List<Line> resp_results = new ArrayList<Line>();
		Results<ScoredDocument> search_results = index.search(query);

	    for (Document scored_doc : search_results){
	    	Line line = new Line();
	        System.out.println(scored_doc);
	        for (Field field : scored_doc.getFields()){
	        	    switch(field.getName()){
	        	    	case "keywords":
	        	    		line.setKeywords(field.getText());
	        	    	case "content":
	        	    		line.setContent(field.getText());
	        	    	case "genre":
	        	    		line.setGenre(field.getText());
	        	    	case "author":
	        	    		line.setUsername(field.getText());
	        	    	case "date_added":
	        	    		line.setDate_added(field.getDate());
	        	    }
	        }
	        resp_results.add(line);
	    }
	    return resp_results;
	}      
	public Document createDocument(Line line){
		
		Document doc = Document.newBuilder()
			    .addField(Field.newBuilder().setName("keywords").setText(line.getKeywordsStr()))
			    .addField(Field.newBuilder().setName("content").setText(line.getContent()))
			    .addField(Field.newBuilder().setName("genre").setText(line.getGenre()))
			    .addField(Field.newBuilder().setName("date_added").setDate(new Date()))
			    .addField(Field.newBuilder().setName("author").setText(line.getUsername()))
			    .build();
		
		return doc;
	}
	
	public void indexADocument(Line line) throws InterruptedException {
		  
		
		  Document document = createDocument(line);
		  final int maxRetry = 3;
		  int attempts = 0;
		  int delay = 2;
		  while (true) {
		    try {
		    
		      index.put(document);
		    } catch (PutException e) {
		      if (StatusCode.TRANSIENT_ERROR.equals(e.getOperationResult().getCode())
		          && ++attempts < maxRetry) { // retrying
		        Thread.sleep(delay * 1000);
		        delay *= 2; // easy exponential backoff
		        continue;
		      } else {
		        throw e; // otherwise throw
		      }
		    }
		    break;
		  }
		 
		  	log.info("Indexing Completed");
		}
	
}
