package ru.job4j.design.lsp.parking;

import java.util.ArrayList;
import java.util.List;

/**
 * Парковка состоит из 2-х линий, одна для легковых, другая для грузовиков,
 * Легковая машина может встать только на парковку для легковых и занять 1 место.
 * Грузовик или занимает 1 место на парковке для грузовых (независимо от размера),
 * или несколько легковых мест (зависит от размера грузовика)
 */
public class CarParking implements Parking {
    private final List<Car> sedanCars;
    private final List<Car> truckPlaces;
    private int freeSedanPlaces;
    private int freeTruckPlaces;

    public CarParking(int countSedanPlaces, int countTruckPlaces) {
        this.freeSedanPlaces = countSedanPlaces;
        this.freeTruckPlaces = countTruckPlaces;
        sedanCars = new ArrayList<>(countSedanPlaces);
        truckPlaces = new ArrayList<>(countTruckPlaces);
    }

    /**
     * Проверяем, что за тип машины (по размеру),
     * затем проверяем, есть ли пустые места,
     * если есть - паркуем (уменьшаем пустые места, добавляем машину в список машин)
     */
    @Override
    public boolean addCar(Car car) {
        boolean rsl = false;
        if (car.getSize() == Sedan.SIZE && freeSedanPlaces > 0) {
            rsl = parkSedan(car);
        } else if ((car.getSize() != Sedan.SIZE) && (freeTruckPlaces > 0 || freeSedanPlaces >= car.getSize())) {
            rsl = parkTruck(car);
        }
        return rsl;
    }

    private boolean parkSedan(Car car) {
        freeSedanPlaces--;
        return sedanCars.add(car);
    }

    private boolean parkTruck(Car car) {
        boolean isParking = false;
        if (freeTruckPlaces > 0) {
            freeTruckPlaces--;
            isParking = truckPlaces.add(car);
        } else if (freeSedanPlaces >= car.getSize()) {
            freeSedanPlaces -= car.getSize();
            for (int i = 0; i < car.getSize(); i++) {
                isParking = sedanCars.add(car);
            }
        }
        return isParking;
    }
}
