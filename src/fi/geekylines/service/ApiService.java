package fi.geekylines.service;

import fi.geekylines.domain.Line;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class ApiService {

	 
	public List<Line> getLines(){
		
		List<Line> list = new ArrayList<Line>();
		list.add(new Line("Nice shoes, Wanna hangout" , "apydan", "random, flirty, short"));
		list.add(new Line("You make my hear go binary 1 0 0 1 1" , "apydan", "geeky, deep, binary, engineering"));
		
		return list;
	}
	
	
}
