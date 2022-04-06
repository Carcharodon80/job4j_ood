package ru.job4j.design.lsp.parking;


import java.util.Arrays;

/**
 * Парковка состоит из 2-х линий, одна для легковых, другая для грузовиков (2 массива)
 * Легковая машина может встать только на парковку для легковых и занять 1 место
 * Грузовая или занимает 1 место на парковке для грузовых,
 * или несколько мест на парковке для легковых (зависит от размера машины)
 */
public class CarParking implements Parking {
    private int[] sedanPlaces;
    private int[] truckPlaces;
    private int counterId = 1;

    public CarParking(int countSedanPlaces, int countTruckPlaces) {
        this.sedanPlaces = new int[countSedanPlaces];
        this.truckPlaces = new int[countTruckPlaces];
    }

    @Override
    public void addCar(Car car) {
        ParkingStrategy parkingStrategy;
        if (car.isTruck()) {
            parkingStrategy = new TruckParkingStrategy();
        } else {
            parkingStrategy = new SedanParkingStrategy();
        }
        if (parkingStrategy.checkFreePlaces(this)) {
            parkCar(car);
        } else {
            System.out.println("Сорян, свободных мест нет...");
        }
    }

    @Override
    public void deleteCar(Car car) {

    }

    private void parkCar(Car car) {

    }

    public int[] getSedanPlaces() {
        return Arrays.copyOf(sedanPlaces, sedanPlaces.length);
    }

    public int[] getTruckPlaces() {
        return Arrays.copyOf(truckPlaces, truckPlaces.length);
    }
}
