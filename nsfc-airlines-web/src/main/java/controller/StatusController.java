package controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import model.Status;
/**
 * 
 * @author naiara
 * API REST para acessar o recurso status
 * 
 */
@RestController
@RequestMapping(value = "/status", produces = "application/json")
public class StatusController {
	   
	   @RequestMapping(method = RequestMethod.GET)
	   public @ResponseBody List<Status> getStatus(ModelMap model) {
	      return new ArrayList<Status>(Arrays.asList(Status.values()));
	   }
}
