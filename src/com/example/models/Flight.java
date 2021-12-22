package com.example.models;

import java.io.Serializable;
import java.util.Objects;

public class Flight extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	public enum FlightType {
		arrival,
		departure
	};

	private String sobt;
	private String sibt;
	private FlightType type;
	private String srcAirportId;
	private String destAirportId;
	private String airlineId;

	public Flight() {
	}

//	public Flight(String flightID, LocalDateTime sobt, LocalDateTime sibt, String type, String deprtAirportId,
//			String arvlAirportId,
//			String airlineId) {
//
//		this.flightID = flightID;
//		this.sibt = sibt;
//		this.sobt = sobt;
//		this.type = type;
//		this.deprtAirportId = deprtAirportId;
//		this.arvlAirportId = arvlAirportId;
//		this.airlineId = airlineId;
//	}

	public String getSibt() {
		return sibt;
	}

	public Flight setSibt(String sibt) {
		this.sibt = sibt;
		return this;
	}

	public String getSobt() {
		return sobt;
	}

	public Flight setSobt(String sobt) {
		this.sobt = sobt;
		return this;
	}

	public FlightType getType() {
		return this.type;
	}

	public Flight setType(FlightType type) {
		this.type = type;
		return this;
	}

	public String getSrcAirportId() {
		return srcAirportId;
	}

	public Flight setSrcAirportId(String srcAirportId) {
		this.srcAirportId = srcAirportId;
		return this;
	}

	public String getDestAirportId() {
		return destAirportId;
	}

	public Flight setDestAirportId(String destAirportId) {
		this.destAirportId = destAirportId;
		return this;
	}

	public String getAirlineId() {
		return airlineId;
	}

	public Flight setAirlineId(String airlineId) {
		this.airlineId = airlineId;
		return this;
	}

	@Override
	public String toString() {
		return "Flight [id=" + id + ", sobt=" + sobt + ", sibt=" + sibt + ", type=" + type
				+ ", srcAirportId=" + srcAirportId + ", destAirportId=" + destAirportId + ", airlineId=" + airlineId
				+ ", createdBy=" + createdBy + ", dateCreated=" + dateCreated + ", modifiedBy=" + modifiedBy
				+ ", dateModified=" + dateModified + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
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
		return Objects.equals(id, other.id);
	}

	public static class FlightBuilder {
		private Flight flight = new Flight();

		public FlightBuilder id(String flightID) {
			flight.id = flightID;
			return this;
		}

		public FlightBuilder sibt(String sibt) {
			flight.sibt = sibt;
			return this;
		}

		public FlightBuilder sobt(String sobt) {
			flight.sobt = sobt;
			return this;
		}

		public FlightBuilder type(FlightType type) {
			flight.type = type;
			return this;
		}

		public FlightBuilder srcAirportId(String srcAirportId) {
			flight.srcAirportId = srcAirportId;
			return this;
		}

		public FlightBuilder destAirportId(String destAirportId) {
			flight.destAirportId = destAirportId;
			return this;
		}

		public FlightBuilder airlineId(String airlineId) {
			flight.airlineId = airlineId;
			return this;
		}

		public Flight build() {
			return flight;
		}
	}
}
