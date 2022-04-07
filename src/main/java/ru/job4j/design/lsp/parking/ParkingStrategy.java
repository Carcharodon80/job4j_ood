package ru.job4j.design.lsp.parking;

public interface ParkingStrategy {
    boolean checkFreePlaces(CarParking carParking, Car car);

    void parkCar(CarParking carParking, Car car);

    default boolean checkCarOnParking(CarParking carParking, Car car) {
        boolean carHasOnParking;
        carHasOnParking = checkIdInPlaces(car.getId(), carParking.getSedanPlaces()) ||
                checkIdInPlaces(car.getId(), carParking.getTruckPlaces());
        return carHasOnParking;
    }

    private boolean checkIdInPlaces(int id, int[] places) {
        boolean hasIdInPlaces = false;
        for (int place : places) {
            if (place == id) {
                hasIdInPlaces = true;
                break;
            }
        }
        return hasIdInPlaces;
    }

    /**
     * !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
     */
    default void unparkCar(CarParking carParking, Car car) {

    }


}
