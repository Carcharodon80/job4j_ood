package ru.job4j.design.lsp.parking;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class CarParkingTest {

    @Test
    public void whenParkTwoSedans() {
        CarParking carParking = new CarParking(3, 5);
        assertThat(carParking.addCar(new Sedan()), is(true));
        assertThat(carParking.addCar(new Sedan()), is(true));
        assertThat(carParking.getSedanPlaces()[0], is(1));
        assertThat(carParking.getSedanPlaces()[1], is(2));
        assertThat(carParking.getSedanPlaces()[2], is(0));
    }

    @Test
    public void whenNoSedanPlaces() {
        CarParking carParking = new CarParking(1, 3);
        assertThat(carParking.addCar(new Sedan()), is(true));
        assertThat(carParking.addCar(new Sedan()), is(false));
        assertThat(carParking.getSedanPlaces()[0], is(1));
    }

    @Test
    public void whenParkTwoTrucksForTruckPlaces() {
        CarParking carParking = new CarParking(3, 4);
        assertThat(carParking.addCar(new Truck(2)), is(true));
        assertThat(carParking.addCar(new Truck(5)), is(true));
        assertThat(carParking.getTruckPlaces()[0], is(1));
        assertThat(carParking.getTruckPlaces()[1], is(2));
        assertThat(carParking.getTruckPlaces()[2], is(0));
        assertThat(carParking.getTruckPlaces()[2], is(0));
    }

    @Test
    public void whenParkTwoTrucksForSedanPlaces() {
        CarParking carParking = new CarParking(7, 0);
        assertThat(carParking.addCar(new Truck(2)), is(true));
        assertThat(carParking.addCar(new Truck(3)), is(true));
        assertThat(carParking.getSedanPlaces()[0], is(1));
        assertThat(carParking.getSedanPlaces()[1], is(1));
        assertThat(carParking.getSedanPlaces()[2], is(2));
        assertThat(carParking.getSedanPlaces()[3], is(2));
        assertThat(carParking.getSedanPlaces()[4], is(2));
        assertThat(carParking.getSedanPlaces()[5], is(0));
        assertThat(carParking.getSedanPlaces()[6], is(0));
    }

    @Test
    public void whenParkThreeTrucksForAllPlaces() {
        CarParking carParking = new CarParking(7, 1);
        assertThat(carParking.addCar(new Truck(2)), is(true));
        assertThat(carParking.addCar(new Truck(2)), is(true));
        assertThat(carParking.addCar(new Truck(3)), is(true));
        assertThat(carParking.getTruckPlaces()[0], is(1));
        assertThat(carParking.getSedanPlaces()[0], is(2));
        assertThat(carParking.getSedanPlaces()[1], is(2));
        assertThat(carParking.getSedanPlaces()[2], is(3));
        assertThat(carParking.getSedanPlaces()[3], is(3));
        assertThat(carParking.getSedanPlaces()[4], is(3));
        assertThat(carParking.getSedanPlaces()[5], is(0));
        assertThat(carParking.getSedanPlaces()[6], is(0));
    }

    @Test
    public void whenNoTruckPlaces() {
        CarParking carParking = new CarParking(7, 2);
        assertThat(carParking.addCar(new Truck(2)), is(true));
        assertThat(carParking.addCar(new Truck(4)), is(true));
        assertThat(carParking.addCar(new Truck(8)), is(false));
        assertThat(carParking.getTruckPlaces()[0], is(1));
        assertThat(carParking.getTruckPlaces()[1], is(2));
    }
}