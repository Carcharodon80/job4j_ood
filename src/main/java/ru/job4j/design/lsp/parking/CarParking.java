package ru.job4j.design.lsp.parking;

/**
 * Парковка состоит из 2-х линий, одна для легковых, другая для грузовиков (2 массива)
 * Легковая машина может встать только на парковку для легковых и занять 1 место
 * Грузовая или занимает 1 место на парковке для грузовых,
 * или несколько мест, идущих подряд, на парковке для легковых (зависит от размера машины)
 */
public class CarParking implements Parking {
    private final int[] sedanPlaces;
    private final int[] truckPlaces;
    private int counterId = 1;
    ParkingStrategy parkingStrategy;

    public CarParking(int countSedanPlaces, int countTruckPlaces) {
        this.sedanPlaces = new int[countSedanPlaces];
        this.truckPlaces = new int[countTruckPlaces];
    }

    /**
     * Сначала устанавливаем стратегию парковки (зависит от типа машины),
     * затем проверяем пустые места,
     * затем паркуем машину,
     * увеличиваем счетчик id для машины
     */
    @Override
    public boolean addCar(Car car) {
        boolean rsl = false;
        if (car.isTruck()) {
            setParkingStrategy(new TruckParkingStrategy());
        } else {
            setParkingStrategy(new SedanParkingStrategy());
        }

        if (parkingStrategy.checkFreePlaces(this, car)) {
            car.setId(counterId);
            parkingStrategy.parkCar(this, car);
            incrementCounterId();
            rsl = true;
        } else {
            System.out.println("Сорян, свободных мест нет...");
        }

        return rsl;
    }

    /**
     * проверяем, есть ли машина с данным id на парковке (на грузовых и легковых местах), если есть -
     * удаляем данный id из грузовых или легковых мест (независимо от типа машины -  default методы в интерфейсе)
     */
    @Override
    public boolean removeCar(Car car) {
        boolean rsl = false;
        if (parkingStrategy.checkCarOnParking(this, car)) {
            parkingStrategy.unparkCar(this, car);
            rsl = true;
        }
        return rsl;
    }

    public int[] getSedanPlaces() {
        return sedanPlaces;
    }

    public int[] getTruckPlaces() {
        return truckPlaces;
    }

    public void setParkingStrategy(ParkingStrategy parkingStrategy) {
        this.parkingStrategy = parkingStrategy;
    }

    private void incrementCounterId() {
        counterId++;
    }
}
