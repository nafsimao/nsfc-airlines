package controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 
 * @author naiara
 * Controlador responsável por retornar a página inicial
 */
@Controller
@RequestMapping("/route")
public class RouteController{
 
   @RequestMapping(method = RequestMethod.GET)
   public String getFlights(ModelMap model) {
      return "flights";
   }

}