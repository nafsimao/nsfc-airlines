package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * 
 * @author naiara
 * Entidade que representa vôo
 *
 */
@Entity
@Table(name = "FLIGHT")
public class Flight {

	private Long id;
	private String code;
	private FlightInfo departure;
	private FlightInfo arrival;
	private Status status;
	private String pilotName;
	private Airplane airplane;

	public Flight() {
	}

	public Flight(String code, FlightInfo departure, FlightInfo arrival, Status status, String pilotName,
				  Airplane airplane) {
		this.code = code;
		this.departure = departure;
		this.arrival = arrival;
		this.status = status;
		this.pilotName = pilotName;
		this.airplane = airplane;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@OneToOne
	@JoinColumn(name = "departure", referencedColumnName = "id")
	public FlightInfo getDeparture() {
		return departure;
	}

	public void setDeparture(FlightInfo departure) {
		this.departure = departure;
	}

	@OneToOne
	@JoinColumn(name = "arrival", referencedColumnName = "id")
	public FlightInfo getArrival() {
		return arrival;
	}

	public void setArrival(FlightInfo arrival) {
		this.arrival = arrival;
	}
	@Column(name = "code")
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Enumerated(EnumType.STRING)
	@Column(name="status")
	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	@Column(name="pilotname")
	public String getPilotName() {
		return pilotName;
	}

	public void setPilotName(String pilotName) {
		this.pilotName = pilotName;
	}

	@ManyToOne
	@JoinColumn(name="airplane")
	public Airplane getAirplane() {
		return airplane;
	}

	public void setAirplane(Airplane airplane) {
		this.airplane = airplane;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((airplane == null) ? 0 : airplane.hashCode());
		result = prime * result + ((arrival == null) ? 0 : arrival.hashCode());
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result + ((departure == null) ? 0 : departure.hashCode());
		result = prime * result + ((pilotName == null) ? 0 : pilotName.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Flight other = (Flight) obj;
		if (airplane == null) {
			if (other.airplane != null)
				return false;
		} else if (!airplane.equals(other.airplane))
			return false;
		if (arrival == null) {
			if (other.arrival != null)
				return false;
		} else if (!arrival.equals(other.arrival))
			return false;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		if (departure == null) {
			if (other.departure != null)
				return false;
		} else if (!departure.equals(other.departure))
			return false;
		if (pilotName == null) {
			if (other.pilotName != null)
				return false;
		} else if (!pilotName.equals(other.pilotName))
			return false;
		if (status != other.status)
			return false;
		return true;
	}
}
