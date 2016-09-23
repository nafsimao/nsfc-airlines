package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import model.Airport;
import service.AirportService;

/**
 * 
 * @author naiara
 * API REST para acessar o recurso aeroportos
 *
 */
@RestController
@RequestMapping(value = "/airports", produces = "application/json")
public class AirportsController{

    @Autowired
    private AirportService airportService;
 
   @RequestMapping(method = RequestMethod.GET)
   public @ResponseBody List<Airport> getAirports() {
       List<Airport> airports = airportService.loadAirports();
       return airports;
   }

}