package fi.geekylines.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequestMapping("/check")
public class TestController {

	@Autowired
	ApiService apiService;
	@Autowired
	SearchingService searchService;
	
	
	@RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody
	List<Line> getAllLines(){
		
		return apiService.getLines();
	}
	
	
	@RequestMapping(value = "/form", method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.OK)
	public void checkForm(@RequestBody TestForm testform){
		
		System.out.println(testform.toString());
		
	}
}
