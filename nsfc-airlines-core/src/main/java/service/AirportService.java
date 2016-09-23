package service;

import model.*;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AirportService {
	
	@PersistenceContext
	private EntityManager em;
	
	@Transactional
	public List<Airport> loadAirports () {
		Query query = this.em.createQuery("from Airport");
		List<Airport> resultList = query.getResultList();
		return resultList;

	}
}
