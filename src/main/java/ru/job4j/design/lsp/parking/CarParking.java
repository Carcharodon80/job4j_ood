package ru.job4j.design.lsp.parking;

/**
 * Парковка состоит из 2-х линий, одна для легковых, другая для грузовиков,
 * Легковая машина может встать только на парковку для легковых и занять 1 место.
 * Грузовая или занимает 1 место на парковке для грузовых (независимо от размера),
 * или несколько легковых мест (зависит от размера грузовика)
 */
public class CarParking implements Parking {
    private int freeSedanPlaces;
    private int freeTruckPlaces;

    public CarParking(int sedanPlaces, int truckPlaces) {
        this.freeSedanPlaces = sedanPlaces;
        this.freeTruckPlaces = truckPlaces;
    }

    /**
     * Проверяем, что за тип машины (по размеру),
     * затем проверяем, есть ли пустые места,
     * если есть - паркуем (уменьшаем пустые места)
     */
    @Override
    public boolean addCar(Car car) {
        boolean rsl = false;
        if (car.getSize() == 1) {
            if (checkFreePlacesForSedan(car)) {
                parkSedan(car);
            }
            ;

        } else {
            if (checkFreePlacesForTruck(car)) {
                parkTruck(car);
            }
        }
        return rsl;
    }


    private boolean checkFreePlacesForSedan(Car car) {
        return false;
    }

    private void parkSedan(Car car) {

    }

    private boolean checkFreePlacesForTruck(Car car) {
        return false;
    }

    private void parkTruck(Car car) {
    }

    public int getFreeSedanPlaces() {
        return freeSedanPlaces;
    }

    public int getFreeTruckPlaces() {
        return freeTruckPlaces;
    }

    public void setSedanPlaces(int sedanPlaces) {
        this.freeSedanPlaces = sedanPlaces;
    }

    public void setTruckPlaces(int truckPlaces) {
        this.freeTruckPlaces = truckPlaces;
    }
}
