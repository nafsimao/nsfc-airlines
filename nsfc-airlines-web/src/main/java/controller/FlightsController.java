package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.fasterxml.jackson.databind.ObjectMapper;

import model.Filter;
import model.Flight;
import service.FlightService;

/**
 * 
 * @author naiara
 * API REST para acessar o recurso vôos
 * 
 */
@RestController
@RequestMapping(value = "/flights", produces = "application/json")
public class FlightsController{
	
	@Autowired
	private FlightService flightService;
 
   @RequestMapping(method = RequestMethod.GET)
   public @ResponseBody List<Flight> getFlights(@RequestParam(name = "filter", required=false) String filterJson) {
	List<Flight> flights = new ArrayList<Flight>();
       try {
           Filter filter = null;
           if(filterJson != null) {
               filter = new ObjectMapper().readValue(filterJson, Filter.class);
           }
           flights = flightService.filterFlights(filter);
       } catch (IOException e) {
           e.printStackTrace();
       }
       return flights;
   }

}
