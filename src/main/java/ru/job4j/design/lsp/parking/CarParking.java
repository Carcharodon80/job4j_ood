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
    private final List<Car> sedanCars = new ArrayList<>();
    private final List<Car> truckPlaces = new ArrayList<>();
    private int freeSedanPlaces;
    private int freeTruckPlaces;

    public CarParking(int countSedanPlaces, int countTruckPlaces) {
        this.freeSedanPlaces = countSedanPlaces;
        this.freeTruckPlaces = countTruckPlaces;
    }

    /**
     * Проверяем, что за тип машины (по размеру),
     * затем проверяем, есть ли пустые места,
     * если есть - паркуем (уменьшаем пустые места, добавляем машину в список машин)
     */
    @Override
    public boolean addCar(Car car) {
        boolean rsl = false;
        if (car.getSize() == Sedan.SIZE) {
            if (freeSedanPlaces > 0) {
                rsl = parkSedan(car);
            }
        } else {
            if (freeTruckPlaces > 0 || freeSedanPlaces >= car.getSize()) {
                rsl = parkTruck(car);
            }
        }
        return rsl;
    }

    private boolean parkSedan(Car car) {
        freeSedanPlaces--;
        return sedanCars.add(car);
    }

    private boolean parkTruck(Car car) {
        if (freeTruckPlaces > 0) {
            freeTruckPlaces--;
        } else if (freeSedanPlaces >= car.getSize()) {
            freeSedanPlaces -= car.getSize();
        }
        return truckPlaces.add(car);
    }
}
