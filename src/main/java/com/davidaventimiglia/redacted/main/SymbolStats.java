package com.davidaventimiglia.redacted.main;

import com.davidaventimiglia.redacted.model.*;
import com.davidaventimiglia.redacted.plugins.*;

/**
 * Gather quote statistics for stocks whose symbols are provided as
 * command line arguments, and write the results to standard output.
 */
public class SymbolStats {
    public static void main (String[] args) {
	int k = 180;
	try {k = Integer.parseInt(System.getenv("DAYS_BACK"));} catch (Exception e) {}
	QuoteStreamParser parser = new AlphaVantageQuoteStreamParser();
	Stats aggregator = new KadaneMaxProfitStats(k);
	Renderer renderer = new BasicRenderer();
	QuoteStreamGenerator generator = new AlphaVantageQuoteStreamGenerator(System.getenv("API_KEY"));
	for (String s : args)
	    try {
		System.out.println(renderer
				   .render(aggregator
					   .getStats(parser
						     .parse(generator
							    .generate(s)))));}
	    catch (Exception e) {}}}
