package com.davidaventimiglia.redacted.main;

import com.davidaventimiglia.redacted.model.*;
import com.davidaventimiglia.redacted.plugins.*;
import static spark.Spark.*;

/**
 * Gather quote statistics for stocks whose symbols are provided as
 * web request parameters, and write the results as a web response.
 */
public class SymbolStatsServer {
    public static void main (String[] args) {
	int k = 180;
	try {k = Integer.parseInt(System.getenv("DAYS_BACK"));} catch (Exception e) {}
	QuoteStreamParser parser = new AlphaVantageQuoteStreamParser();
	Stats aggregator = new KadaneMaxProfitStats(k);
	QuoteStreamGenerator generator = new AlphaVantageQuoteStreamGenerator(System.getenv("API_KEY"));
	Renderer renderer = new BasicRenderer();
	get("/stats/:symbol",
	    (req, res) -> {
		return
		    renderer.render(
		    aggregator
		    .getStats(parser
			      .parse(generator
				     .generate(req.params(":symbol")))));});}}
