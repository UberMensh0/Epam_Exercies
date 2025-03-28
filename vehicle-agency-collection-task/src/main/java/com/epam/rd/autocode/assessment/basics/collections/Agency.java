package com.epam.rd.autocode.assessment.basics.collections;

import java.math.BigDecimal;
import java.util.*;

import com.epam.rd.autocode.assessment.basics.entity.BodyType;
import com.epam.rd.autocode.assessment.basics.entity.Client;
import com.epam.rd.autocode.assessment.basics.entity.Order;
import com.epam.rd.autocode.assessment.basics.entity.Vehicle;

public class Agency implements Find, Sort {

	private List<Vehicle> vehicles;

	private List<Order> orders;

	public Agency() {
		this.vehicles = new ArrayList<>();
		this.orders = new ArrayList<>();
	}

	public void addVehicle(Vehicle vehicle){
		vehicles.add(vehicle);
	}

	public void addOrder(Order order){
		orders.add(order);
	}


	@Override
	public List<Vehicle> sortByID() {
		List<Vehicle> result = new ArrayList<>(vehicles);
		Collections.sort(result, new Comparator<Vehicle>() {
			@Override
			public int compare(Vehicle v1, Vehicle v2) {
				return Long.compare(v1.getId(), v2.getId());
			}
		});
		return result;
	}

	@Override
	public List<Vehicle> sortByYearOfProduction() {
		List<Vehicle> result = new ArrayList<>(vehicles);
		Collections.sort(result, new Comparator<Vehicle>() {
			@Override
			public int compare(Vehicle v1, Vehicle v2) {
				return Integer.compare(v1.getYearOfProduction(), v2.getYearOfProduction());
			}
		});
		return result;
	}

	@Override
	public List<Vehicle> sortByOdometer() {
		List<Vehicle> result = new ArrayList<>(vehicles);
		Collections.sort(result, new Comparator<Vehicle>() {
			@Override
			public int compare(Vehicle v1, Vehicle v2) {
				return Long.compare(v1.getOdometer(), v2.getOdometer());
			}
		});
		return result;
	}

	@Override
	public Set<String> findMakers() {
		Set<String> result = new HashSet<>();
		for (Vehicle vehicle : vehicles) {
			result.add(vehicle.getMake());
		}
		return result;
	}

	@Override
	public Set<BodyType> findBodytypes() {
		Set<BodyType> result = new HashSet<>();
		for (Vehicle vehicle : vehicles) {
			result.add(vehicle.getBodyType());
		}
		return result;
	}

	@Override
	public Map<String, List<Vehicle>> findVehicleGrouppedByMake() {
		Map<String, List<Vehicle>> result = new HashMap<>();
		for (Vehicle vehicle : vehicles) {
			if (!result.containsKey(vehicle.getMake())) {
				result.put(vehicle.getMake(), new ArrayList<>());
			}
			result.get(vehicle.getMake()).add(vehicle);
		}
		return result;
	}

	@Override
	public List<Client> findTopClientsByPrices(List<Client> clients, int maxCount) {
		Map<Long, BigDecimal> result = new HashMap<>();
		for (Order order : orders) {
			long clientId = order.getClientId();
			if (!result.containsKey(clientId)) {
				result.put(clientId, BigDecimal.ZERO);
			}
			result.put(clientId, result.get(clientId).add(order.getPrice()));
		}

		Collections.sort(clients, new Comparator<Client>() {
			@Override
			public int compare(Client c1, Client c2) {
				return result.getOrDefault(c2.getId(), BigDecimal.ZERO)
						.compareTo(result.getOrDefault(c1.getId(), BigDecimal.ZERO));
			}
		});

		return clients.subList(0, Math.min(maxCount, clients.size()));
	}

	@Override
	public List<Client> findClientsWithAveragePriceNoLessThan(List<Client> clients, int average) {
		Map<Long, BigDecimal> clientTotalPrice = new HashMap<>();
		Map<Long, Integer> clientOrderCount = new HashMap<>();

		for (Order order : orders) {
			long clientId = order.getClientId();
			if (!clientTotalPrice.containsKey(clientId)) {
				clientTotalPrice.put(clientId, BigDecimal.ZERO);
			}
			clientTotalPrice.put(clientId, clientTotalPrice.get(clientId).add(order.getPrice()));
			clientOrderCount.put(clientId, clientOrderCount.getOrDefault(clientId, 0) + 1);
		}

		List<Client> filteredClients = new ArrayList<>();
		for (Client client : clients) {
			long clientId = client.getId();
			if (clientOrderCount.containsKey(clientId)) {
				BigDecimal avgPrice = clientTotalPrice.get(clientId).divide(BigDecimal.valueOf(clientOrderCount.get(clientId)), BigDecimal.ROUND_HALF_UP);
				if (avgPrice.intValue() >= average) {
					filteredClients.add(client);
				}
			}
		}
		return filteredClients;
	}

	@Override
	public List<Vehicle> findMostOrderedVehicles(int maxCount) {
		Map<Long, Integer> vehicleOrderCount = new HashMap<>();
		for (Order order : orders) {
			long vehicleId = order.getVehicleId();
			vehicleOrderCount.put(vehicleId, vehicleOrderCount.getOrDefault(vehicleId, 0) + 1);
		}

		List<Vehicle> sortedVehicles = new ArrayList<>(vehicles);
		Collections.sort(sortedVehicles, new Comparator<Vehicle>() {
			@Override
			public int compare(Vehicle v1, Vehicle v2) {
				int countCompare = Integer.compare(vehicleOrderCount.getOrDefault(v2.getId(), 0),
						vehicleOrderCount.getOrDefault(v1.getId(), 0));
				if (countCompare == 0) {
					return Long.compare(v1.getId(), v2.getId());
				}
				return countCompare;
			}
		});

		return sortedVehicles.subList(0, Math.min(maxCount, sortedVehicles.size()));
	}

}
