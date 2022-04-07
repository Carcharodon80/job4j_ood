package ru.job4j.design.lsp.parking;

public class SedanParkingStrategy implements ParkingStrategy{
    @Override
    public boolean checkFreePlaces(CarParking carParking, Car car) {
        boolean hasFreePlace = false;
        for (int place : carParking.getSedanPlaces()) {
            if (place == 0) {
                hasFreePlace = true;
                break;
            }
        }
        return hasFreePlace;
    }

    @Override
    public void parkCar(CarParking carParking, Car car) {
        int[] places = carParking.getSedanPlaces();
        for (int i = 0; i < places.length; i++) {
            if (places[i] == 0) {
                places[i] = car.getId();
                break;
            }
        }
    }

    @Override
    public void unparkCar(CarParking carParking, Car car) {

    }
}
