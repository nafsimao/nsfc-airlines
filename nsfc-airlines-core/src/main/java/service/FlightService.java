package service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.transaction.annotation.Transactional;

import model.Airport;
import model.Filter;
import model.Flight;
import model.FlightInfo;
import model.Status;
/**
 * 
 * @author naiara
 * Serviço da entidade Flight
 *
 */
public class FlightService {
	
	@PersistenceContext
	private EntityManager em;
	
	/**
	 * Retorna uma lista de vôos filtrados de acordo com o parâmetro filter
	 * @param filter
	 * @return lista de vôos
	 */
	@Transactional
	public List<Flight> filterFlights (Filter filter) {
		CriteriaBuilder criteriaBuilder = this.em.getCriteriaBuilder();
		CriteriaQuery<Flight> criteriaQuery = criteriaBuilder.createQuery(Flight.class);
		Root<Flight> flight = criteriaQuery.from(Flight.class);

		if(filter != null){
		   Status filterStatus = filter.getStatus();
		   List<Predicate> predicates = new ArrayList<Predicate>();
		   if(filterStatus != null){
		      predicates.add(criteriaBuilder.equal(flight.<Status>get("status"), filterStatus));
		   }
		   Date filterArrivalDateStart = filter.getArrivalDateStart();
		   Date filterArrivalDateEnd = filter.getArrivalDateEnd();
		   if(filterArrivalDateStart != null && filterArrivalDateEnd != null &&
		         filterArrivalDateStart.before(filterArrivalDateEnd)){
		      predicates.add(criteriaBuilder.between(flight.<FlightInfo>get("arrival").<Date>get("date"), filterArrivalDateStart, filterArrivalDateEnd));
		   }
		   Date filterDepartureDateStart = filter.getDepartureDateStart();
		   Date filterDepartureDateEnd = filter.getDepartureDateEnd();
		   if(filterDepartureDateStart != null && filterDepartureDateEnd != null &&
		         filterDepartureDateStart.before(filterDepartureDateEnd)){
		      predicates.add(criteriaBuilder.between(flight.<FlightInfo>get("departure").<Date>get("date"), filterDepartureDateStart, filterDepartureDateEnd));
		   }
		   Long arrivalAirportId = filter.getArrivalAirportId();
		   if(arrivalAirportId != null){
		      predicates.add(criteriaBuilder.equal(
		            flight.<FlightInfo>get("arrival").<Airport>get("airport").<Long>get("id"), arrivalAirportId));
		   }
		   Long departureAirportId = filter.getDepartureAirportId();
		   if(departureAirportId != null){
		      predicates.add(criteriaBuilder.equal(
		            flight.<FlightInfo>get("departure").<Airport>get("airport").<Long>get("id"), departureAirportId));
		   }
		   if(!predicates.isEmpty()){
		      criteriaQuery.where(criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()])));
		   }
		}

		criteriaQuery.orderBy(
		      criteriaBuilder.asc(flight.<FlightInfo>get("departure").<Date>get("date")),
		      criteriaBuilder.asc(flight.<FlightInfo>get("arrival").<Date>get("date")),
		      criteriaBuilder.asc(flight.<FlightInfo>get("arrival").<Airport>get("airport").<String>get("city")));

		criteriaQuery.select(flight);
		TypedQuery<Flight> query = em.createQuery(criteriaQuery);
		return query.getResultList();

	}
}
