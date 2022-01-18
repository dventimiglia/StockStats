package com.davidaventimiglia.doordash.main;

import com.davidaventimiglia.doordash.model.*;
import com.davidaventimiglia.doordash.plugins.*;

/**
 * Parse a text-based stream of quote data fed in on standard input,
 * and write the parsed quotes out to standard output.
 */
public class ParseQuotes {
    public static void main (String[] args) {
	QuoteStreamParser parser = new AlphaVantageQuoteStreamParser();
	for (Quote q : parser.parse(System.in)) System.out.println(q);}}
