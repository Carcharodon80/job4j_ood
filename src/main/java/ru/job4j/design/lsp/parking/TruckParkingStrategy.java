package ru.job4j.design.lsp.parking;

public class TruckParkingStrategy implements ParkingStrategy{

    /**
     * проверяем, есть ли свободные места для грузовика (на грузовых или легковых местах)
     */
    @Override
    public boolean checkFreePlaces(CarParking carParking, Car car) {
        return checkFreeTruckPlaces(carParking) || checkFreeSedanPlaces(carParking, car);
    }

    /**
     * проверяем, есть ли свободные места для грузовика (на грузовых местах)
     */
    private boolean checkFreeTruckPlaces(CarParking carParking) {
        boolean hasFreePlaces = false;
        for (int place : carParking.getTruckPlaces()) {
            if (place == 0) {
                hasFreePlaces = true;
                break;
            }
        }
        return hasFreePlaces;
    }

    /**
     * проверяем, есть ли свободные места для грузовика (на легковых местах)
     */
    private boolean checkFreeSedanPlaces(CarParking carParking, Car car) {
        boolean hasFreePlaces = false;
        int[] sedanPlaces = carParking.getSedanPlaces();
        int countNull = 0;
        for (int i = 0; i < sedanPlaces.length; i++) {
            if (sedanPlaces[i] == 0) {
                countNull++;
                if (countNull == car.getSize()) {
                    hasFreePlaces = true;
                    break;
                }
            }
            else {
                countNull = 0;
            }
        }
        return hasFreePlaces;
    }

    @Override
    public void parkCar(CarParking carParking, Car car) {
        if (checkFreeTruckPlaces(carParking)) {
            int[] places = carParking.getTruckPlaces();
            for (int i = 0; i < places.length; i++) {
                if (places[i] == 0) {
                    places[i] = car.getId();
                    break;
                }
            }
        } else {
            int[] places = carParking.getSedanPlaces();
            int firstNullPlace = findFirstNullPlace(carParking, car);
            for (int i = 0; i < car.getSize(); i++) {
                places[firstNullPlace + i] = car.getId();
            }
        }

    }

    @Override
    public void unparkCar(CarParking carParking, Car car) {

    }

    private int findFirstNullPlace(CarParking carParking, Car car) {
        int[] sedanPlaces = carParking.getSedanPlaces();
        int countNull = 0;
        int firstNullPlace = 0;
        for (int i = 0; i < sedanPlaces.length; i++) {
            if (sedanPlaces[i] == 0) {
                countNull++;
                if (countNull == car.getSize()) {
                    firstNullPlace = i - car.getSize() + 1;
                    break;
                }
            }
            else {
                countNull = 0;
            }
        }
        return firstNullPlace;
    }
}
