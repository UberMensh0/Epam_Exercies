package com.epam.rd.autocode.assessment.basics.service;

import com.epam.rd.autocode.assessment.basics.entity.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Mapper {

    public static Client csvToClient(String[] values) {
        return new Client(
                Long.parseLong(values[0]), // id
                values[1].isEmpty() ? null : values[1], // email
                values[2].isEmpty() ? null : values[2], // password
                values[3].isEmpty() ? null : values[3], // name
                values[4].isEmpty() ? BigDecimal.ZERO : new BigDecimal(values[4]), // balance
                values[5].isEmpty() ? null : values[5] // driverLicense
        );
    }

    public static Employee csvToEmployee(String[] values) {
        return new Employee(
                Long.parseLong(values[0]), // id
                values[1].isEmpty() ? null : values[1], // email
                values[2].isEmpty() ? null : values[2], // password
                values[3].isEmpty() ? null : values[3], // name
                values[4].isEmpty() ? null : values[4], // phone
                values[5].isEmpty() ? null : LocalDate.parse(values[5]) // dateOfBirth
        );
    }

    public static Vehicle csvToVehicle(String[] values) {
        return new Vehicle(
                Long.parseLong(values[0]), // id
                values[1].isEmpty() ? null : values[1], // make
                values[2].isEmpty() ? null : values[2], // model
                values[3].isEmpty() ? null : values[3], // characteristics
                Integer.parseInt(values[4]), // yearOfProduction
                Long.parseLong(values[5]), // odometer
                values[6].isEmpty() ? null : values[6], // color
                values[7].isEmpty() ? null : values[7], // licensePlate
                Integer.parseInt(values[8]), // numberOfSeats
                values[9].isEmpty() ? BigDecimal.ZERO : new BigDecimal(values[9]), // price
                values[10].isEmpty() ? 'A' : values[10].charAt(0), // requiredLicense
                BodyType.valueOf(values[11]) // bodyType
        );
    }

    public static Order csvToOrder(String[] values) {
        return new Order(
                Long.parseLong(values[0]), // id
                Long.parseLong(values[1]), // clientId
                Long.parseLong(values[2]), // employeeId
                Long.parseLong(values[3]), // vehicleId
                values[4].isEmpty() ? null : LocalDateTime.parse(values[4]), // startTime
                values[5].isEmpty() ? null : LocalDateTime.parse(values[5]), // endTime
                values[6].isEmpty() ? BigDecimal.ZERO : new BigDecimal(values[6]) // price
        );
    }

    public static String[] clientToCsv(Client client) {
        return new String[]{
                String.valueOf(client.getId()),
                client.getEmail() == null ? "" : client.getEmail(),
                client.getPassword() == null ? "" : client.getPassword(),
                client.getName() == null ? "" : client.getName(),
                client.getBalance() == null ? "" : client.getBalance().toString(),
                client.getDriverLicense() == null ? "" : client.getDriverLicense()
        };
    }

    public static String[] employeeToCsv(Employee employee) {
        return new String[]{
                String.valueOf(employee.getId()),
                employee.getEmail() == null ? "" : employee.getEmail(),
                employee.getPassword() == null ? "" : employee.getPassword(),
                employee.getName() == null ? "" : employee.getName(),
                employee.getPhone() == null ? "" : employee.getPhone(),
                employee.getDateOfBirth() == null ? "" : employee.getDateOfBirth().toString()
        };
    }

    public static String[] vehicleToCsv(Vehicle vehicle) {
        return new String[]{
                String.valueOf(vehicle.getId()),
                vehicle.getMake() == null ? "" : vehicle.getMake(),
                vehicle.getModel() == null ? "" : vehicle.getModel(),
                vehicle.getCharacteristics() == null ? "" : vehicle.getCharacteristics(),
                String.valueOf(vehicle.getYearOfProduction()),
                String.valueOf(vehicle.getOdometer()),
                vehicle.getColor() == null ? "" : vehicle.getColor(),
                vehicle.getLicensePlate() == null ? "" : vehicle.getLicensePlate(),
                String.valueOf(vehicle.getNumberOfSeats()),
                vehicle.getPrice() == null ? "" : vehicle.getPrice().toString(),
                String.valueOf(vehicle.getRequiredLicense()),
                vehicle.getBodyType() == null ? "" : vehicle.getBodyType().name()
        };
    }

    public static String[] orderToCsv(Order order) {
        return new String[]{
                String.valueOf(order.getId()),
                String.valueOf(order.getClientId()),
                String.valueOf(order.getEmployeeId()),
                String.valueOf(order.getVehicleId()),
                order.getStartTime() == null ? "" : order.getStartTime().toString(),
                order.getEndTime() == null ? "" : order.getEndTime().toString(),
                order.getPrice() == null ? "" : order.getPrice().toString()
        };
    }
}
