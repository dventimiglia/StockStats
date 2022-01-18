package com.davidaventimiglia.doordash.main;

import com.davidaventimiglia.doordash.model.*;
import com.davidaventimiglia.doordash.plugins.*;

/**
 * Stream text-based quote data to standard output for stock symbols
 * provided as command line arguments.
 */
public class StreamStats {
    public static void main (String[] args) {
	int k = 180;
	try {k = Integer.parseInt(System.getenv("DAYS_BACK"));} catch (Exception e) {}
	QuoteStreamParser parser = new AlphaVantageQuoteStreamParser();
	Stats aggregator = new KadaneMaxProfitStats(k);
	Renderer renderer = new BasicRenderer();
	System.out.println(renderer
			   .render(aggregator
				   .getStats(parser
					     .parse(System.in))));}}

