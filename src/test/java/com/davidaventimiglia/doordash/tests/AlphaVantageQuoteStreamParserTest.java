package com.davidaventimiglia.doordash.tests;

import com.davidaventimiglia.doordash.model.*;
import com.davidaventimiglia.doordash.plugins.*;
import java.util.*;
import org.junit.*;
import static org.junit.Assert.*;

public class AlphaVantageQuoteStreamParserTest {
    @Test
    public void checkParsedSizesAndChecksums () {
	QuoteStreamParser p = new AlphaVantageQuoteStreamParser();
	List<Quote> msft = new ArrayList<>();
	for (Quote q : p.parse(getClass().getClassLoader().getResourceAsStream("msft.js")))
	    msft.add(q);
	assertFalse(msft.isEmpty());
	assertEquals(4514, msft.size());
	assertEquals(1803226498L, msft.stream().mapToLong(q -> q.high).sum());
	List<Quote> googl = new ArrayList<>();
	for (Quote q : p.parse(getClass().getClassLoader().getResourceAsStream("googl.js")))
	    googl.add(q);
	assertFalse(googl.isEmpty());
	assertEquals(3352, googl.size());
	assertEquals(19811438725L, googl.stream().mapToLong(q -> q.high).sum());}}
