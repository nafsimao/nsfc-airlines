package service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import model.Airplane;
import model.Airport;
import model.Filter;
import model.Flight;
import model.FlightInfo;
import model.Status;
import service.FlightService;

/**
 * 
 * @author naiara
 * Suit de testes da classe FlightService
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/spring-context.xml")
@Transactional
public class FlightServiceTest {

	@PersistenceContext
	private EntityManager em;
	
	@Autowired
	private FlightService flightService;
	
	@Test
	public void filterFlighTest() throws ParseException {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault());

		Date departureDateStart = sdf.parse("2016-10-20 21:50");
		Date departureDateEnd = sdf.parse("2016-10-20 23:59");
		Date arrivalDateStart = sdf.parse("2016-10-21 05:08");
		Date arrivalDateEnd = sdf.parse("2016-10-21 10:05");
		
		String flightCode1 = "AA55";
		String flightCode2 = "55AA";
		String pilotName = "John";

		Airport airportGRU = new Airport("Internacional de Guarlhos", "Guarulhos", "SP", "Brasil", "GRU");
		Airport airportCDG = new Airport("Charles de Gaule", "Paris", "Comuna de Paris", "França", "CDG");
		
		this.em.persist(airportGRU);
		this.em.persist(airportCDG);

		FlightInfo departure1 = new FlightInfo(airportGRU, sdf.parse("2016-10-20 22:50"));
		FlightInfo departure2 = new FlightInfo(airportGRU, sdf.parse("2016-10-20 10:50"));
		FlightInfo arrival1 = new FlightInfo(airportCDG, sdf.parse("2016-10-21 06:50"));
		FlightInfo arrival2 = new FlightInfo(airportCDG, sdf.parse("2016-10-21 22:50"));

		this.em.persist(departure1);
		this.em.persist(departure2);
		this.em.persist(arrival1);
		this.em.persist(arrival2);
		
		Airplane airplane1 = new Airplane("55151asdfas454", "Boeing 777-300");
		Airplane airplane2 = new Airplane("qweasd123123456", "Boeing 747-300");
		
		this.em.persist(airplane1);
		this.em.persist(airplane2);
		
		Flight flight1 = new Flight(flightCode1, departure1, arrival1, Status.CONFIRMED, pilotName, airplane1);
		Flight flight2 = new Flight(flightCode2, departure2, arrival2, Status.CONFIRMED, pilotName, airplane2);
		
		this.em.persist(flight1);
		this.em.persist(flight2);

		Filter filter = new Filter(departureDateStart, departureDateEnd, arrivalDateStart, arrivalDateEnd, Status.CONFIRMED, null, null);
		
		List <Flight> flights = flightService.filterFlights(filter);
		Assert.assertNotNull(flights);
		Assert.assertEquals(1, flights.size());

		Flight flight = flights.get(0);
		Assert.assertEquals(flightCode1, flight.getCode());
		Assert.assertEquals(pilotName, flight.getPilotName());
		Assert.assertEquals(departure1, flight.getDeparture());
		Assert.assertEquals(arrival1, flight.getArrival());
		Assert.assertEquals(Status.CONFIRMED, flight.getStatus());
	}
	
	@Test
	public void noFilterFlighTest() throws ParseException {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault());

		String flightCode1 = "AA55";
		String flightCode2 = "55AA";
		String pilotName = "John";

		Airport airportGRU = new Airport("Internacional de Guarlhos", "Guarulhos", "SP", "Brasil", "GRU");
		Airport airportCDG = new Airport("Charles de Gaule", "Paris", "Comuna de Paris", "França", "CDG");
		
		this.em.persist(airportGRU);
		this.em.persist(airportCDG);

		FlightInfo departure1 = new FlightInfo(airportGRU, sdf.parse("2016-10-20 22:50"));
		FlightInfo departure2 = new FlightInfo(airportGRU, sdf.parse("2016-10-20 10:50"));
		FlightInfo arrival1 = new FlightInfo(airportCDG, sdf.parse("2016-10-21 06:50"));
		FlightInfo arrival2 = new FlightInfo(airportCDG, sdf.parse("2016-10-21 22:50"));

		this.em.persist(departure1);
		this.em.persist(departure2);
		this.em.persist(arrival1);
		this.em.persist(arrival2);
		
		Airplane airplane1 = new Airplane("55151asdfas454", "Boeing 777-300");
		Airplane airplane2 = new Airplane("qweasd123123456", "Boeing 747-300");
		
		this.em.persist(airplane1);
		this.em.persist(airplane2);
		
		Flight flight1 = new Flight(flightCode1, departure1, arrival1, Status.CONFIRMED, pilotName, airplane1);
		Flight flight2 = new Flight(flightCode2, departure2, arrival2, Status.CONFIRMED, pilotName, airplane2);
		
		this.em.persist(flight1);
		this.em.persist(flight2);

		Filter filter = null;
		
		List <Flight> flights = flightService.filterFlights(filter);
		Assert.assertNotNull(flights);
		Assert.assertEquals(2, flights.size());

		Flight flight = flights.get(0);
		Assert.assertEquals(flightCode2, flight.getCode());
		Assert.assertEquals(pilotName, flight.getPilotName());
		Assert.assertEquals(departure2, flight.getDeparture());
		Assert.assertEquals(arrival2, flight.getArrival());
		Assert.assertEquals(Status.CONFIRMED, flight.getStatus());
		
		flight = flights.get(1);
		Assert.assertEquals(flightCode1, flight.getCode());
		Assert.assertEquals(pilotName, flight.getPilotName());
		Assert.assertEquals(departure1, flight.getDeparture());
		Assert.assertEquals(arrival1, flight.getArrival());
		Assert.assertEquals(Status.CONFIRMED, flight.getStatus());
	}
	
	@Test
	public void FilterByArrivalTest() throws ParseException {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault());

		String flightCode1 = "AA55";
		String flightCode2 = "55AA";
		String pilotName = "John";

		Airport airportGRU = new Airport("Internacional de Guarlhos", "Guarulhos", "SP", "Brasil", "GRU");
		Airport airportCDG = new Airport("Charles de Gaule", "Paris", "Comuna de Paris", "França", "CDG");
		
		this.em.persist(airportGRU);
		this.em.persist(airportCDG);

		FlightInfo departure1 = new FlightInfo(airportGRU, sdf.parse("2016-10-20 22:50"));
		FlightInfo departure2 = new FlightInfo(airportGRU, sdf.parse("2016-10-20 10:50"));
		FlightInfo arrival1 = new FlightInfo(airportCDG, sdf.parse("2016-10-21 06:50"));
		FlightInfo arrival2 = new FlightInfo(airportCDG, sdf.parse("2016-10-21 22:50"));

		this.em.persist(departure1);
		this.em.persist(departure2);
		this.em.persist(arrival1);
		this.em.persist(arrival2);
		
		Airplane airplane1 = new Airplane("55151asdfas454", "Boeing 777-300");
		Airplane airplane2 = new Airplane("qweasd123123456", "Boeing 747-300");
		
		this.em.persist(airplane1);
		this.em.persist(airplane2);
		
		Flight flight1 = new Flight(flightCode1, departure1, arrival1, Status.CONFIRMED, pilotName, airplane1);
		Flight flight2 = new Flight(flightCode2, departure2, arrival2, Status.CANCELED, pilotName, airplane2);
		
		this.em.persist(flight1);
		this.em.persist(flight2);

		Filter filter = new Filter(null, null, null, null, Status.CANCELED, null, null);
		
		List <Flight> flights = flightService.filterFlights(filter);
		Assert.assertNotNull(flights);
		Assert.assertEquals(1, flights.size());

		Flight flight = flights.get(0);
		Assert.assertEquals(flightCode2, flight.getCode());
		Assert.assertEquals(pilotName, flight.getPilotName());
		Assert.assertEquals(departure2, flight.getDeparture());
		Assert.assertEquals(arrival2, flight.getArrival());
		Assert.assertEquals(Status.CANCELED, flight.getStatus());
	}
	
	@Test
	public void filterByDepartureDateTest() throws ParseException {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault());

		Date departureDateStart = sdf.parse("2016-10-20 21:50");
		Date departureDateEnd = sdf.parse("2016-10-20 23:59");
		
		String flightCode1 = "AA55";
		String flightCode2 = "55AA";
		String pilotName = "John";

		Airport airportGRU = new Airport("Internacional de Guarlhos", "Guarulhos", "SP", "Brasil", "GRU");
		Airport airportCDG = new Airport("Charles de Gaule", "Paris", "Comuna de Paris", "França", "CDG");
		
		this.em.persist(airportGRU);
		this.em.persist(airportCDG);

		FlightInfo departure1 = new FlightInfo(airportGRU, sdf.parse("2016-10-20 22:50"));
		FlightInfo departure2 = new FlightInfo(airportGRU, sdf.parse("2016-10-20 10:50"));
		FlightInfo arrival1 = new FlightInfo(airportCDG, sdf.parse("2016-10-21 06:50"));
		FlightInfo arrival2 = new FlightInfo(airportCDG, sdf.parse("2016-10-21 22:50"));

		this.em.persist(departure1);
		this.em.persist(departure2);
		this.em.persist(arrival1);
		this.em.persist(arrival2);
		
		Airplane airplane1 = new Airplane("55151asdfas454", "Boeing 777-300");
		Airplane airplane2 = new Airplane("qweasd123123456", "Boeing 747-300");
		
		this.em.persist(airplane1);
		this.em.persist(airplane2);
		
		Flight flight1 = new Flight(flightCode1, departure1, arrival1, Status.CONFIRMED, pilotName, airplane1);
		Flight flight2 = new Flight(flightCode2, departure2, arrival2, Status.CONFIRMED, pilotName, airplane2);
		
		this.em.persist(flight1);
		this.em.persist(flight2);

		Filter filter = new Filter(departureDateStart, departureDateEnd,  null, null, null, null, null);
		
		List <Flight> flights = flightService.filterFlights(filter);
		Assert.assertNotNull(flights);
		Assert.assertEquals(1, flights.size());

		Flight flight = flights.get(0);
		Assert.assertEquals(flightCode1, flight.getCode());
		Assert.assertEquals(pilotName, flight.getPilotName());
		Assert.assertEquals(departure1, flight.getDeparture());
		Assert.assertEquals(arrival1, flight.getArrival());
		Assert.assertEquals(Status.CONFIRMED, flight.getStatus());
	}
	
	@Test
	public void filterByArrivalDateTest() throws ParseException {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault());

		Date arrivalDateStart = sdf.parse("2016-10-21 05:08");
		Date arrivalDateEnd = sdf.parse("2016-10-21 10:05");
		
		String flightCode1 = "AA55";
		String flightCode2 = "55AA";
		String pilotName = "John";

		Airport airportGRU = new Airport("Internacional de Guarlhos", "Guarulhos", "SP", "Brasil", "GRU");
		Airport airportCDG = new Airport("Charles de Gaule", "Paris", "Comuna de Paris", "França", "CDG");
		
		this.em.persist(airportGRU);
		this.em.persist(airportCDG);

		FlightInfo departure1 = new FlightInfo(airportGRU, sdf.parse("2016-10-20 22:50"));
		FlightInfo departure2 = new FlightInfo(airportGRU, sdf.parse("2016-10-20 10:50"));
		FlightInfo arrival1 = new FlightInfo(airportCDG, sdf.parse("2016-10-21 06:50"));
		FlightInfo arrival2 = new FlightInfo(airportCDG, sdf.parse("2016-10-21 22:50"));

		this.em.persist(departure1);
		this.em.persist(departure2);
		this.em.persist(arrival1);
		this.em.persist(arrival2);
		
		Airplane airplane1 = new Airplane("55151asdfas454", "Boeing 777-300");
		Airplane airplane2 = new Airplane("qweasd123123456", "Boeing 747-300");
		
		this.em.persist(airplane1);
		this.em.persist(airplane2);
		
		Flight flight1 = new Flight(flightCode1, departure1, arrival1, Status.CONFIRMED, pilotName, airplane1);
		Flight flight2 = new Flight(flightCode2, departure2, arrival2, Status.CONFIRMED, pilotName, airplane2);
		
		this.em.persist(flight1);
		this.em.persist(flight2);

		Filter filter = new Filter(null, null, arrivalDateStart, arrivalDateEnd, Status.CONFIRMED, null, null);
		
		List <Flight> flights = flightService.filterFlights(filter);
		Assert.assertNotNull(flights);
		Assert.assertEquals(1, flights.size());

		Flight flight = flights.get(0);
		Assert.assertEquals(flightCode1, flight.getCode());
		Assert.assertEquals(pilotName, flight.getPilotName());
		Assert.assertEquals(departure1, flight.getDeparture());
		Assert.assertEquals(arrival1, flight.getArrival());
		Assert.assertEquals(Status.CONFIRMED, flight.getStatus());
	}
	
	@Test
	public void filterByDepartureAndStatusTest() throws ParseException {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault());

		Date departureDateStart = sdf.parse("2016-10-20 21:50");
		Date departureDateEnd = sdf.parse("2016-10-20 23:59");
		
		String flightCode1 = "AA55";
		String flightCode2 = "55AA";
		String pilotName = "John";

		Airport airportGRU = new Airport("Internacional de Guarlhos", "Guarulhos", "SP", "Brasil", "GRU");
		Airport airportCDG = new Airport("Charles de Gaule", "Paris", "Comuna de Paris", "França", "CDG");
		
		this.em.persist(airportGRU);
		this.em.persist(airportCDG);

		FlightInfo departure1 = new FlightInfo(airportGRU, sdf.parse("2016-10-20 22:50"));
		FlightInfo departure2 = new FlightInfo(airportGRU, sdf.parse("2016-10-20 10:50"));
		FlightInfo arrival1 = new FlightInfo(airportCDG, sdf.parse("2016-10-21 06:50"));
		FlightInfo arrival2 = new FlightInfo(airportCDG, sdf.parse("2016-10-21 22:50"));

		this.em.persist(departure1);
		this.em.persist(departure2);
		this.em.persist(arrival1);
		this.em.persist(arrival2);
		
		Airplane airplane1 = new Airplane("55151asdfas454", "Boeing 777-300");
		Airplane airplane2 = new Airplane("qweasd123123456", "Boeing 747-300");
		
		this.em.persist(airplane1);
		this.em.persist(airplane2);
		
		Flight flight1 = new Flight(flightCode1, departure1, arrival1, Status.SCHEDULED, pilotName, airplane1);
		Flight flight2 = new Flight(flightCode2, departure2, arrival2, Status.CONFIRMED, pilotName, airplane2);
		
		this.em.persist(flight1);
		this.em.persist(flight2);

		Filter filter = new Filter(departureDateStart, departureDateEnd, null, null, Status.SCHEDULED, null, null);
		
		List <Flight> flights = flightService.filterFlights(filter);
		Assert.assertNotNull(flights);
		Assert.assertEquals(1, flights.size());

		Flight flight = flights.get(0);
		Assert.assertEquals(flightCode1, flight.getCode());
		Assert.assertEquals(pilotName, flight.getPilotName());
		Assert.assertEquals(departure1, flight.getDeparture());
		Assert.assertEquals(arrival1, flight.getArrival());
		Assert.assertEquals(Status.SCHEDULED, flight.getStatus());
	}

	@Test
	public void filterByDepartureAirport() throws ParseException {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault());

		String flightCode1 = "AA55";
		String flightCode2 = "55AA";
		String pilotName = "John";

		Airport airportGRU = new Airport("Internacional de Guarlhos", "Guarulhos", "SP", "Brasil", "GRU");
		Airport airportCDG = new Airport("Charles de Gaule", "Paris", "Comuna de Paris", "França", "CDG");

		this.em.persist(airportGRU);
		this.em.persist(airportCDG);

		FlightInfo departure1 = new FlightInfo(airportGRU, sdf.parse("2016-10-20 22:50"));
		FlightInfo departure2 = new FlightInfo(airportCDG, sdf.parse("2016-10-20 10:50"));
		FlightInfo arrival1 = new FlightInfo(airportCDG, sdf.parse("2016-10-21 06:50"));
		FlightInfo arrival2 = new FlightInfo(airportGRU, sdf.parse("2016-10-21 22:50"));

		this.em.persist(departure1);
		this.em.persist(departure2);
		this.em.persist(arrival1);
		this.em.persist(arrival2);

		Airplane airplane1 = new Airplane("55151asdfas454", "Boeing 777-300");
		Airplane airplane2 = new Airplane("qweasd123123456", "Boeing 747-300");

		this.em.persist(airplane1);
		this.em.persist(airplane2);

		Flight flight1 = new Flight(flightCode1, departure1, arrival1, Status.CONFIRMED, pilotName, airplane1);
		Flight flight2 = new Flight(flightCode2, departure2, arrival2, Status.CONFIRMED, pilotName, airplane2);

		this.em.persist(flight1);
		this.em.persist(flight2);

		Filter filter = new Filter(null, null, null, null, null, airportGRU.getId(), null);

		List <Flight> flights = flightService.filterFlights(filter);
		Assert.assertNotNull(flights);
		Assert.assertEquals(1, flights.size());

		Flight flight = flights.get(0);
		Assert.assertEquals(flightCode1, flight.getCode());
		Assert.assertEquals(pilotName, flight.getPilotName());
		Assert.assertEquals(departure1, flight.getDeparture());
		Assert.assertEquals(arrival1, flight.getArrival());
		Assert.assertEquals(Status.CONFIRMED, flight.getStatus());
	}

	@Test
	public void filterByArrivalAirport() throws ParseException {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault());


		String flightCode1 = "AA55";
		String flightCode2 = "55AA";
		String pilotName = "John";

		Airport airportGRU = new Airport("Internacional de Guarlhos", "Guarulhos", "SP", "Brasil", "GRU");
		Airport airportCDG = new Airport("Charles de Gaule", "Paris", "Comuna de Paris", "França", "CDG");

		this.em.persist(airportGRU);
		this.em.persist(airportCDG);

		FlightInfo departure1 = new FlightInfo(airportGRU, sdf.parse("2016-10-20 22:50"));
		FlightInfo departure2 = new FlightInfo(airportCDG, sdf.parse("2016-10-20 10:50"));
		FlightInfo arrival1 = new FlightInfo(airportCDG, sdf.parse("2016-10-21 06:50"));
		FlightInfo arrival2 = new FlightInfo(airportGRU, sdf.parse("2016-10-21 22:50"));

		this.em.persist(departure1);
		this.em.persist(departure2);
		this.em.persist(arrival1);
		this.em.persist(arrival2);

		Airplane airplane1 = new Airplane("55151asdfas454", "Boeing 777-300");
		Airplane airplane2 = new Airplane("qweasd123123456", "Boeing 747-300");

		this.em.persist(airplane1);
		this.em.persist(airplane2);

		Flight flight1 = new Flight(flightCode1, departure1, arrival1, Status.CONFIRMED, pilotName, airplane1);
		Flight flight2 = new Flight(flightCode2, departure2, arrival2, Status.CONFIRMED, pilotName, airplane2);

		this.em.persist(flight1);
		this.em.persist(flight2);

		Filter filter = new Filter(null, null, null, null, null, null, airportGRU.getId());

		List <Flight> flights = flightService.filterFlights(filter);
		Assert.assertNotNull(flights);
		Assert.assertEquals(1, flights.size());

		Flight flight = flights.get(0);
		Assert.assertEquals(flightCode2, flight.getCode());
		Assert.assertEquals(pilotName, flight.getPilotName());
		Assert.assertEquals(departure2, flight.getDeparture());
		Assert.assertEquals(arrival2, flight.getArrival());
		Assert.assertEquals(Status.CONFIRMED, flight.getStatus());
	}
	
}
