package ru.job4j.design.lsp.parking;

public class TruckParkingStrategy implements ParkingStrategy{
    @Override
    public boolean checkFreePlaces() {
        return false;
    }
}
