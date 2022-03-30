package ru.job4j.design.lsp.parking;

public interface Parking {
    boolean checkFreePlaces(Car car);

    void addCar(Car car);

    void deleteCar(Car car);

    public int getTruckPlaces();

    public int getSedanPlaces();
}
