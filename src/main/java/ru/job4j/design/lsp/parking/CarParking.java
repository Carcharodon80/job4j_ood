package ru.job4j.design.lsp.parking;

/**
 * Парковка состоит из 2-х линий, одна для легковых, другая для грузовиков (2 массива, заполняются id машин)
 * Легковая машина может встать только на парковку для легковых и занять 1 место.
 * Грузовая или занимает 1 место на парковке для грузовых (независимо от размера),
 * или несколько мест, идущих подряд, на парковке для легковых (зависит от размера машины)
 */
public class CarParking implements Parking {
    private final int[] sedanPlaces;
    private final int[] truckPlaces;
    private int counterId = 1;

    public CarParking(int countSedanPlaces, int countTruckPlaces) {
        this.sedanPlaces = new int[countSedanPlaces];
        this.truckPlaces = new int[countTruckPlaces];
    }

    /** Проверяем, что за тип машины (по размеру),
     * затем проверяем, есть ли пустые места,
     * присваиваем машине id,
     * затем паркуем машину,
     * увеличиваем счетчик id для машины
     */
    @Override
    public boolean addCar(Car car) {
        if (car.getSize() == 1) {
            checkFreePlacesForSedan();
            car.setId(counterId);
            parkSedan(car);
        } else {
            checkFreePlacesForTruck(car);
            car.setId(counterId);
            parkTruck();
        }
        incrementCounterId();
        return false;
    }

    private void parkTruck() {
    }

    private boolean checkFreePlacesForTruck(Car car) {
        return true;
    }

    private void parkSedan(Car car) {
    }

    private boolean checkFreePlacesForSedan() {
        return true;
    }

    private void incrementCounterId() {
        counterId++;
    }

    /**
     * проверяем, есть ли машина с данным id на парковке (на грузовых и легковых местах), если есть -
     * удаляем данный id из грузовых и легковых мест (не зависит от типа машины)
     */
    @Override
    public boolean removeCar(Car car) {
        checkIdOnParking(car);
        unparkCar(car);
        return false;
    }

    private void unparkCar(Car car) {
    }

    private boolean checkIdOnParking(Car car) {
        return true;
    }

    public int[] getSedanPlaces() {
        return sedanPlaces;
    }

    public int[] getTruckPlaces() {
        return truckPlaces;
    }
}
