package com.davidaventimiglia.redacted.tests;

import com.davidaventimiglia.redacted.model.*;
import com.davidaventimiglia.redacted.plugins.*;
import java.util.*;
import org.junit.*;
import java.time.*;
import static org.junit.Assert.*;

public class KadaneMaxProfitStatsTest {
    @Test
    public void checkMSFT () {
	QuoteStreamParser parser = new AlphaVantageQuoteStreamParser();
	Stats aggregator = new KadaneMaxProfitStats(100);
	Map<String, Stats.Pair> results =
	    aggregator
	    .getStats(parser
		      .parse(getClass().getClassLoader().getResourceAsStream("msft.js")));
	assertEquals(java.sql.Date.valueOf(LocalDate.of(2017,8,10)),results.get("min").date);
	assertEquals(java.sql.Date.valueOf(LocalDate.of(2017,10,27)),results.get("max").date);
	assertEquals(140100L, results.get("max").price);}

    @Test
    public void checkGOOGL () {
	QuoteStreamParser parser = new AlphaVantageQuoteStreamParser();
	Stats aggregator = new KadaneMaxProfitStats(100);
	Map<String, Stats.Pair> results =
	    aggregator
	    .getStats(parser
		      .parse(getClass().getClassLoader().getResourceAsStream("googl.js")));
	assertEquals(java.sql.Date.valueOf(LocalDate.of(2017,8,21)),results.get("min").date);
	assertEquals(java.sql.Date.valueOf(LocalDate.of(2017,11,28)),results.get("max").date);
	assertEquals(1517500L, results.get("max").price);}

    @Test
    public void checkConstantPrice () {
	QuoteStreamParser parser = new AlphaVantageQuoteStreamParser();
	Stats aggregator = new KadaneMaxProfitStats(1000);
	Map<String, Stats.Pair> results =
	    aggregator
	    .getStats(parser
		      .parse(getClass().getClassLoader().getResourceAsStream("test1.js")));
	assertEquals(java.sql.Date.valueOf(LocalDate.of(2017,12,8)),results.get("min").date);
	assertEquals(null,results.get("max").date); // No possible sell date!
	assertEquals(0L, results.get("max").price);} // Profit must be 0!

    @Test
    public void checkMonotonicallyIncreasingPrice () {
	QuoteStreamParser parser = new AlphaVantageQuoteStreamParser();
	Stats aggregator = new KadaneMaxProfitStats(1000);
	Map<String, Stats.Pair> results =
	    aggregator
	    .getStats(parser
		      .parse(getClass().getClassLoader().getResourceAsStream("test2.js")));
	assertEquals(java.sql.Date.valueOf(LocalDate.of(2017,1,3)),results.get("min").date);
	assertEquals(java.sql.Date.valueOf(LocalDate.of(2017,12,8)),results.get("max").date);
	assertEquals(236L, results.get("max").price);} // Profit must be 236!

    @Test
    public void checkMonotonicallyDecreasingPrice () {
	QuoteStreamParser parser = new AlphaVantageQuoteStreamParser();
	Stats aggregator = new KadaneMaxProfitStats(1000);
	Map<String, Stats.Pair> results =
	    aggregator
	    .getStats(parser
		      .parse(getClass().getClassLoader().getResourceAsStream("test3.js")));
	assertEquals(java.sql.Date.valueOf(LocalDate.of(2017,12,8)),results.get("min").date);
	assertEquals(null,results.get("max").date); // No possible sell date!
	assertEquals(0L, results.get("max").price);} // Profit must be 0!
    
    @Test
    public void checkMonotonicallySawtoothPrice () {
	QuoteStreamParser parser = new AlphaVantageQuoteStreamParser();
	Stats aggregator = new KadaneMaxProfitStats(1000);
	Map<String, Stats.Pair> results =
	    aggregator
	    .getStats(parser
		      .parse(getClass().getClassLoader().getResourceAsStream("test4.js")));
	assertEquals(java.sql.Date.valueOf(LocalDate.of(2017,1,3)),results.get("min").date);
	assertEquals(java.sql.Date.valueOf(LocalDate.of(2017,5,10)),results.get("max").date);
	assertEquals(88L, results.get("max").price);}} // Profit must be 88!
