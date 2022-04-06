package ru.job4j.design.lsp.parking;

public class SedanParkingStrategy implements ParkingStrategy{
    @Override
    public boolean checkFreePlaces(CarParking carParking) {
        boolean hasFreePlace = false;
        for (int place : carParking.getSedanPlaces()) {
            if (place == 0) {
                hasFreePlace = true;
                break;
            }
        }
        return hasFreePlace;
    }
}
