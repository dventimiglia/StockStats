package com.davidaventimiglia.doordash.tests;

import com.davidaventimiglia.doordash.model.*;
import java.time.*;
import java.util.*;
import org.junit.*;
import static org.junit.Assert.*;

public class QuoteTest {
    @Test
    public void compareToTest () {
	LocalTime midnight = LocalTime.MIDNIGHT;
	LocalDate today = LocalDate.now();
	LocalDateTime todayMidnight = LocalDateTime.of(today, midnight);
	LocalDateTime tomorrowMidnight = todayMidnight.plusDays(1);	
	LocalDateTime yesterdayMidnight = todayMidnight.plusDays(-1);	
	Quote q1 = new Quote(java.sql.Date.valueOf(todayMidnight.toLocalDate()), 1, 1, 1, 1, 1);
	Quote q2 = new Quote(java.sql.Date.valueOf(todayMidnight.toLocalDate()), 2, 2, 2, 2, 2);
	Quote q3 = new Quote(java.sql.Date.valueOf(tomorrowMidnight.toLocalDate()), 2, 2, 2, 2, 2);
	assertEquals(0, q1.compareTo(q2));
	assertEquals(-1, q1.compareTo(q3));
	assertEquals(1, q3.compareTo(q1));}

    @Test
    public void toStringTest () {
	Quote q = new Quote(java.sql.Date.valueOf(LocalDate.of(2017, 1, 1)), 1, 2, 3, 4, 5);
	assertEquals("[2017-01-01, 1, 2, 3, 4, 5]", q.toString());}}
