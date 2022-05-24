package ru.job4j.tdd;

import org.junit.Ignore;
import org.junit.Test;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class CinemaTest {

    @Ignore
    @Test
    public void whenBuy() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        date.set(2020, 10, 10, 23, 00);
        Ticket ticket = cinema.buy(account, 1, 1, date);
        assertThat(ticket, is(new Ticket3D()));
    }

    @Ignore
    @Test
    public void whenFind() {
        Cinema cinema = new Cinema3D();
        cinema.add(new Session3D());
        List<Session> sessions = cinema.find(session -> true);
        assertThat(sessions, is(Arrays.asList(new Session3D())));
    }

    @Ignore
    @Test
    public void whenAdd() {
        Cinema cinema = new Cinema3D();
        Session session = new Session3D();
        cinema.add(session);
        assertTrue(cinema.SESSIONS.contains(session));
    }

    @Ignore
    @Test
    public void whenBuyAccountHasTicket() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        date.set(2020, 10, 10, 23, 00);
        Ticket ticket = cinema.buy(account, 1, 1, date);
        assertTrue(account.TICKETS.contains(ticket));
    }

    @Ignore
    @Test(expected = IllegalArgumentException.class)
    public void whenBuyWithInvalidRow() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        date.set(2020, 10, 10, 23, 00);
        Ticket ticket = cinema.buy(account, 100, 1, date);
    }

    @Ignore
    @Test(expected = IllegalArgumentException.class)
    public void whenBuyWithInvalidDate() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        date.set(2020, 13, 10, 25, 00);
        Ticket ticket = cinema.buy(account, 1, 1, date);
    }

    @Ignore
    @Test(expected = IllegalArgumentException.class)
    public void whenBuySoldedTicket() {
        Account account1 = new AccountCinema();
        Account account2 = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        date.set(2020, 10, 10, 23, 00);
        Ticket ticket1 = cinema.buy(account1, 1, 1, date);
        Ticket ticket2 = cinema.buy(account2, 1, 1, date);
    }
}