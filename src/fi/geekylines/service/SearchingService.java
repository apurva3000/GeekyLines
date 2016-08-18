package fi.geekylines.service;

import java.util.Date;

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

import fi.geekylines.domain.Line;


@Service
public class SearchingService {

	public void query_index(String indexName, String query){
		
		IndexSpec indexSpec = IndexSpec.newBuilder().setName(indexName).build();
		SearchService service = SearchServiceFactory.getSearchService(
		       SearchServiceConfig.newBuilder().setDeadline(10.0).setNamespace("").build());
		Index index = service.getIndex(indexSpec);

	    Results<ScoredDocument> results = index.search(query);

	    for (Document scored_doc : results){
	        System.out.println(scored_doc);
	    }
	}      
	public Document createDocument(Line line){
		
		Document doc = Document.newBuilder()
			    .addField(Field.newBuilder().setName("keywords").setText(line.getKeywords()))
			    .addField(Field.newBuilder().setName("content").setText(line.getContent()))
			    .addField(Field.newBuilder().setName("genre").setText(line.getGenre()))
			    .addField(Field.newBuilder().setName("date_added").setDate(new Date()))
			    .addField(Field.newBuilder().setName("author").setText(line.getUsername()))
			    .build();
		
		return doc;
	}
	
	public void indexADocument(String indexName, Document document)
		    throws InterruptedException {
		
		

		  IndexSpec indexSpec = IndexSpec.newBuilder().setName(indexName).build();
		  
		  SearchService service = SearchServiceFactory.getSearchService(
			       SearchServiceConfig.newBuilder().setDeadline(10.0).setNamespace("").build());
		  Index index = service.getIndex(indexSpec);
		  

		  
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
		  
		}
	
}
