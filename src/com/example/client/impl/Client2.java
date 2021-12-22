package com.example.client.impl;

public class Client2 {
//	private static final String TOKEN = "112233";
//	private IFlightFacade facade;
//	
//	public Client2(IFlightFacade facade) {
//		this.facade = facade;
//	}
//	
//	public static void main(String[] args) throws Exception {
//
//		// define a flight and test its data
//		
//		System.out.println("1-Add Flight");
//		System.out.println("2-Remove Flight");
//		System.out.println("3-Update Flight");
//		System.out.println("4-Find Flight");
//		System.out.println("5-Find All Flights");
//		System.out.println("6-Print Options");
//		System.out.println("0-Exit");
//
//		String id;
//		Scanner in = new Scanner(System.in);
//
//		while (true) {
//
//			System.out.println("*******************");
//			System.out.print("Enter your action: ");
//
//			switch (in.nextInt()) {
//			case 1:
//				// supposing that a new flight was created in the UI then returned
//				facade.addFlight(createFlight(), TOKEN);
//				break;
//
//			case 2:
//				System.out.print("Enter the flight id: ");
//				id = in.next();
//				facade.removeFlight(id, TOKEN);
//				break;
//
//			case 3:
//				System.out.print("Enter the flight id: ");
//				id = in.next();
//				System.out.println("Updating flight of ID " + id + ":");
//				// supposing that the flight was updated in the UI then returned
//				facade.updateFlight(id, createFlight(), TOKEN);
//				break;
//
//			case 4:
//				System.out.print("Enter the flight id: ");
//				id = in.next();
//				Flight flight = facade.findFlight(id, TOKEN);
//				if(flight != null)
//					System.out.println(flight);
//				else
//					System.out.println("Flight Not Found");
//				break;
//
//			case 5:
//				System.out.println("Available Flights:");
//				facade.findAllFlights(TOKEN).forEach(System.out::println);
//				break;
//
//			case 6:
//				System.out.println("1-Add Flight");
//				System.out.println("2-Remove Flight");
//				System.out.println("3-Update Flight");
//				System.out.println("4-Find Flight");
//				System.out.println("5-Find All Flights");
//				System.out.println("6-Print Options");
//				System.out.println("0-Exit");
//				break;
//
//			case 0:
//				System.out.println("*******************");
//				System.out.println("End of Program");
//				return;
//
//			default:
//				System.out.println("Invalid Option!");
//				break;
//			}
//		}
//
//	}
//
//	private static Flight createFlight() {
//		Flight.FlightBuilder builder = new Flight.FlightBuilder();
//
//		Scanner in = new Scanner(System.in);
//		System.out.print("Enter the flight id: ");
//		String id = in.next();
//
//		String sobt = "January 02 2021 14:55";
//		String sibt = "January 03 2021 08:00";
//
//		System.out.print("Enter the flight type: ");
//		String typeStr = in.next();
//		
//		FlightType type;
//		if(typeStr.equalsIgnoreCase("arrival"))
//			type = FlightType.arrival;
//		else
//			type = FlightType.departure;
//		
//		System.out.print("Enter the flight source airport ID: ");
//		String deprtAirportId = in.next();
//
//		System.out.print("Enter the flight destination airport ID: ");
//		String arvlAirportId = in.next();
//
//		System.out.print("Enter the flight airline ID: ");
//		String airlineId = in.next();
//
//		return builder
//				.id(id)
//				.sobt(sobt)
//				.sibt(sibt)
//				.type(type)
//				.srcAirportId(deprtAirportId)
//				.destAirportId(arvlAirportId)
//				.airlineId(airlineId)
//				.build();
//
//	}

}
