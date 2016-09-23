package model;

import java.util.Date;
/**
 * 
 * @author naiara
 * Classe que representa o filtro de vôos recebido do client side
 * 
 */
public class Filter {
	private Date departureDateStart;
	private Date departureDateEnd;
	private Date arrivalDateStart;
	private Date arrivalDateEnd;
	private Status status;
	private Long arrivalAirportId;
	private Long departureAirportId;
	
	public Filter () {
		
	}

	public Filter(Date departureDateStart, Date departureDateEnd, Date arrivalDateStart, Date arrivalDateEnd, Status status, Long departureAirportId, Long arrivalAirportId) {
		this.departureDateStart = departureDateStart;
		this.departureDateEnd = departureDateEnd;
		this.arrivalDateStart = arrivalDateStart;
		this.arrivalDateEnd = arrivalDateEnd;
		this.status = status;
		this.arrivalAirportId = arrivalAirportId;
		this.departureAirportId = departureAirportId;
	}

	public Date getDepartureDateStart() {
		return departureDateStart;
	}
	public void setDepartureDateStart(Date departureDateStart) {
		this.departureDateStart = departureDateStart;
	}
	public Date getArrivalDateStart() {
		return arrivalDateStart;
	}
	public void setArrivalDateStart(Date arrivalDateStart) {
		this.arrivalDateStart = arrivalDateStart;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Date getDepartureDateEnd() {
		return departureDateEnd;
	}

	public void setDepartureDateEnd(Date departureDateEnd) {
		this.departureDateEnd = departureDateEnd;
	}

	public Date getArrivalDateEnd() {
		return arrivalDateEnd;
	}

	public void setArrivalDateEnd(Date arrivalDateEnd) {
		this.arrivalDateEnd = arrivalDateEnd;
	}

	public Long getArrivalAirportId() {
		return arrivalAirportId;
	}

	public void setArrivalAirportId(Long arrivalAirportId) {
		this.arrivalAirportId = arrivalAirportId;
	}

	public Long getDepartureAirportId() {
		return departureAirportId;
	}

	public void setDepartureAirportId(Long departureAirportId) {
		this.departureAirportId = departureAirportId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((arrivalDateEnd == null) ? 0 : arrivalDateEnd.hashCode());
		result = prime * result + ((arrivalDateStart == null) ? 0 : arrivalDateStart.hashCode());
		result = prime * result + ((departureDateEnd == null) ? 0 : departureDateEnd.hashCode());
		result = prime * result + ((departureDateStart == null) ? 0 : departureDateStart.hashCode());
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
		Filter other = (Filter) obj;
		if (arrivalDateEnd == null) {
			if (other.arrivalDateEnd != null)
				return false;
		} else if (!arrivalDateEnd.equals(other.arrivalDateEnd))
			return false;
		if (arrivalDateStart == null) {
			if (other.arrivalDateStart != null)
				return false;
		} else if (!arrivalDateStart.equals(other.arrivalDateStart))
			return false;
		if (departureDateEnd == null) {
			if (other.departureDateEnd != null)
				return false;
		} else if (!departureDateEnd.equals(other.departureDateEnd))
			return false;
		if (departureDateStart == null) {
			if (other.departureDateStart != null)
				return false;
		} else if (!departureDateStart.equals(other.departureDateStart))
			return false;
		if (status != other.status)
			return false;
		return true;
	}
}
