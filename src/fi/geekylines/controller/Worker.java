package fi.geekylines.controller;

import java.util.List;
import java.util.logging.Logger;

import fi.geekylines.domain.Line;
import fi.geekylines.domain.TestForm;
import fi.geekylines.service.ApiService;
import fi.geekylines.service.SearchingService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Role;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequestMapping("/api/tasks")
public class Worker {

	private static final Logger log = Logger.getLogger(Worker.class.getName());
	
	@Autowired
	SearchingService searchService;
	
	
	
	@RequestMapping(value = "/indexLine", method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.OK)
	public void checkForm(@RequestParam String linejson){
		
		log.info("Indexing task recieved");
		System.out.println(linejson.toString());
		
	}
}
