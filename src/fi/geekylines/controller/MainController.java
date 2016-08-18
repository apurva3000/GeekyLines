package fi.geekylines.controller;

import java.util.List;
import java.util.logging.Logger;

import fi.geekylines.domain.Line;
import fi.geekylines.service.ApiService;
import fi.geekylines.service.SearchingService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Role;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.google.appengine.api.taskqueue.Queue;
import com.google.appengine.api.taskqueue.QueueFactory;
import com.google.appengine.api.taskqueue.TaskOptions;
import com.google.gson.Gson;

@Controller
@RequestMapping("/api/lines")
public class MainController {

	private static final Logger log = Logger.getLogger(MainController.class.getName());
	
	@Autowired
	ApiService apiService;
	@Autowired
	SearchingService searchService;
	
	
	@RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody
	List<Line> getAllLines(){
		
		return apiService.getLines();
	}
	
	@RequestMapping(value = "{id}.json", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody
	Line searchById(@PathVariable("id") int id){
		
		Line line = apiService.getLines().get(id);
		
		return line;
		
	}
	
	@RequestMapping(value = "/admin", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody
	String getAdmin(){
		
		return "Admin";
	}
	
	@RequestMapping(value = "/query", method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.OK)
	public void queryIndex(){
		
			searchService.query_index("geekyindex", "roses are red");
		
		
	}
	
	@RequestMapping(value = "/submitLine", method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.OK)
	public void submitLine(@RequestHeader(value = "Authorization") String token, @RequestBody Line line){
		
		Gson gson = new Gson();
		String linejson = gson.toJson(line);
		
		log.info(linejson);
		log.info(token);
		//Queue queue = QueueFactory.getDefaultQueue();
	    //queue.add(TaskOptions.Builder.withUrl("/api/tasks/indexLine").header("authorization", "bearer " + access_token).param("linejson", linejson));

		
		
	}
}
