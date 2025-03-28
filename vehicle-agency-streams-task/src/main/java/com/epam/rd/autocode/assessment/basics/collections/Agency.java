package com.epam.rd.autocode.assessment.basics.collections;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

import com.epam.rd.autocode.assessment.basics.entity.BodyType;
import com.epam.rd.autocode.assessment.basics.entity.Client;
import com.epam.rd.autocode.assessment.basics.entity.Order;
import com.epam.rd.autocode.assessment.basics.entity.Vehicle;

public class Agency implements Find, Sort {

	private final List<Vehicle> vehicles = new ArrayList<>();
	private final List<Order> orders = new ArrayList<>();

	public void addVehicle(Vehicle vehicle) {
		vehicles.add(vehicle);
	}

	public void addOrder(Order order) {
		orders.add(order);
	}

	@Override
	public List<Vehicle> sortByID() {
		return vehicles.stream()
				.sorted(Comparator.comparingLong(Vehicle::getId))
				.collect(Collectors.toList());
	}

	@Override
	public List<Vehicle> sortByYearOfProduction() {
		return vehicles.stream()
				.sorted(Comparator.comparingInt(Vehicle::getYearOfProduction))
				.collect(Collectors.toList());
	}

	@Override
	public List<Vehicle> sortByOdometer() {
		return vehicles.stream()
				.sorted(Comparator.comparingLong(Vehicle::getOdometer))
				.collect(Collectors.toList());
	}

	@Override
	public Set<String> findMakers() {
		return vehicles.stream()
				.map(Vehicle::getMake)
				.collect(Collectors.toSet());
	}

	@Override
	public Set<BodyType> findBodytypes() {
		return vehicles.stream()
				.map(Vehicle::getBodyType)
				.collect(Collectors.toSet());
	}

	@Override
	public Map<String, List<Vehicle>> findVehicleGrouppedByMake() {
		return vehicles.stream()
				.collect(Collectors.groupingBy(Vehicle::getMake));
	}

	@Override
	public List<Client> findTopClientsByPrices(List<Client> clients, int maxCount) {
		return clients.stream()
				.sorted(Comparator.comparing(
						c -> orders.stream()
								.filter(o -> o.getClientId() == c.getId())
								.map(Order::getPrice)
								.reduce(BigDecimal.ZERO, BigDecimal::add),
						Comparator.reverseOrder()))
				.limit(maxCount)
				.collect(Collectors.toList());
	}

	@Override
	public List<Client> findClientsWithAveragePriceNoLessThan(List<Client> clients, int average) {
		return clients.stream()
				.filter(client -> {
					BigDecimal totalPrice = orders.stream()
							.filter(order -> order.getClientId() == client.getId())
							.map(Order::getPrice)
							.reduce(BigDecimal.ZERO, BigDecimal::add);
					long orderCount = orders.stream()
							.filter(order -> order.getClientId() == client.getId())
							.count();
					return orderCount > 0 &&
							totalPrice.divide(BigDecimal.valueOf(orderCount), BigDecimal.ROUND_HALF_UP).intValue() >= average;
				})
				.collect(Collectors.toList());
	}

	@Override
	public List<Vehicle> findMostOrderedVehicles(int maxCount) {
		return vehicles.stream()
				.sorted(Comparator.comparing(
						v -> orders.stream()
								.filter(o -> o.getVehicleId() == v.getId())
								.count(),
						Comparator.reverseOrder()))
				.limit(maxCount)
				.collect(Collectors.toList());
	}
}
