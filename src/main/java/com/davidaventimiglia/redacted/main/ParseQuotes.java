package com.davidaventimiglia.redacted.main;

import com.davidaventimiglia.redacted.model.*;
import com.davidaventimiglia.redacted.plugins.*;

/**
 * Parse a text-based stream of quote data fed in on standard input,
 * and write the parsed quotes out to standard output.
 */
public class ParseQuotes {
    public static void main (String[] args) {
	QuoteStreamParser parser = new AlphaVantageQuoteStreamParser();
	for (Quote q : parser.parse(System.in)) System.out.println(q);}}
