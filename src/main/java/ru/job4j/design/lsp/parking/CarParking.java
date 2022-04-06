package ru.job4j.design.lsp.parking;

public class CarParking implements Parking{
    private int truckPlaces;
    private int sedanPlaces;
    ParkingStrategy parkingStrategy;

    public CarParking(int truckPlaces, int sedanPlaces) {
        this.truckPlaces = truckPlaces;
        this.sedanPlaces = sedanPlaces;
    }

    @Override
    public boolean checkFreePlaces(Car car) {
        return false;
    }

    @Override
    public void addCar(Car car) {
        if (car.isTruck()) {
            parkingStrategy = new TruckParkingStrategy();
        } else {
            parkingStrategy = new SedanParkingStrategy();
        }


    }

    @Override
    public void deleteCar(Car car) {

    }

    public int getTruckPlaces() {
        return truckPlaces;
    }

    public void setTruckPlaces(int truckPlaces) {
        this.truckPlaces = truckPlaces;
    }

    public int getSedanPlaces() {
        return sedanPlaces;
    }

    public void setSedanPlaces(int sedanPlaces) {
        this.sedanPlaces = sedanPlaces;
    }
}
