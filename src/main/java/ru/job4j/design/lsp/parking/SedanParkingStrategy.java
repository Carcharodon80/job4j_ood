package ru.job4j.design.lsp.parking;

public class SedanParkingStrategy implements ParkingStrategy{
    @Override
    public boolean checkFreePlaces() {
        return false;
    }
}
