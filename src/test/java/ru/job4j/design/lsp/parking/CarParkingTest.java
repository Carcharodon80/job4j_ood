package ru.job4j.design.lsp.parking;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CarParkingTest {
    Parking carParking;

    @Before
    public void setUp() {
        carParking = new CarParking(5, 3);
    }

    @Test
    public void whenParkOneSedan() {
        assertTrue(carParking.addCar(new Sedan()));
    }

    @Test
    public void whenParkFiveSedans() {
        assertTrue(carParking.addCar(new Sedan()));
        assertTrue(carParking.addCar(new Sedan()));
        assertTrue(carParking.addCar(new Sedan()));
        assertTrue(carParking.addCar(new Sedan()));
        assertTrue(carParking.addCar(new Sedan()));
    }

    @Test
    public void whenParkSixSedans() {
        assertTrue(carParking.addCar(new Sedan()));
        assertTrue(carParking.addCar(new Sedan()));
        assertTrue(carParking.addCar(new Sedan()));
        assertTrue(carParking.addCar(new Sedan()));
        assertTrue(carParking.addCar(new Sedan()));
        assertFalse(carParking.addCar(new Sedan()));
    }

    @Test
    public void whenParkOneTruck() {
        assertTrue(carParking.addCar(new Truck(4)));
    }

    @Test
    public void whenParkThreeTrucks() {
        assertTrue(carParking.addCar(new Truck(4)));
        assertTrue(carParking.addCar(new Truck(8)));
        assertTrue(carParking.addCar(new Truck(2)));
    }

    @Test
    public void whenParkFourTrucks() {
        assertTrue(carParking.addCar(new Truck(4)));
        assertTrue(carParking.addCar(new Truck(8)));
        assertTrue(carParking.addCar(new Truck(2)));
        assertTrue(carParking.addCar(new Truck(5)));
    }

    @Test
    public void whenParkFourTrucksAndOneSedan() {
        assertTrue(carParking.addCar(new Truck(4)));
        assertTrue(carParking.addCar(new Truck(8)));
        assertTrue(carParking.addCar(new Truck(2)));
        assertTrue(carParking.addCar(new Truck(5)));
        assertFalse(carParking.addCar(new Sedan()));
    }

    @Test
    public void whenParkOneSedanAndFourTrucks() {
        assertTrue(carParking.addCar(new Sedan()));
        assertTrue(carParking.addCar(new Truck(4)));
        assertTrue(carParking.addCar(new Truck(8)));
        assertTrue(carParking.addCar(new Truck(2)));
        assertTrue(carParking.addCar(new Truck(4)));
    }

    @Test
    public void whenParkOneSedanAndFourTrucks2() {
        assertTrue(carParking.addCar(new Sedan()));
        assertTrue(carParking.addCar(new Truck(4)));
        assertTrue(carParking.addCar(new Truck(8)));
        assertTrue(carParking.addCar(new Truck(2)));
        assertFalse(carParking.addCar(new Truck(5)));
    }

    @Test (expected = IllegalArgumentException.class)
    public void whenInvalidSizeOfTruck() {
        Car truck = new Truck(1);
    }
}