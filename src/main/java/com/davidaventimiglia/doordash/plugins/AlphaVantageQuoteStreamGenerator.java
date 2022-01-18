package com.davidaventimiglia.doordash.plugins;

import com.davidaventimiglia.doordash.model.*;
import java.io.*;
import java.net.*;
import java.text.*;
import java.util.*;
import javax.script.*;

/**
 * AlphaVantageQuoteStreamGenerator is a QuoteStreamGenerator that
 * generates an InputStream of raw quote data for a given stock ticker
 * symbol, using the Alpha Vantage Quote service (see
 * https://www.alphavantage.co/).
 */
public class AlphaVantageQuoteStreamGenerator implements QuoteStreamGenerator {

    /**
     * API request template
     */
    public static String TEMPLATE = "https://www.alphavantage.co/query?function=TIME_SERIES_DAILY&symbol=%s&apikey=%s&outputsize=full";

    protected final String apiKey;
    
    /**
     * Constructor
     *
     * @param key - the API key
     */
    public AlphaVantageQuoteStreamGenerator (String key) {
	apiKey = key;}

    /**
     * Generate feed data using third-party API
     *
     * @param symbol - the ticker symbol
     */
    @Override
    public InputStream generate (String symbol)
	throws MalformedURLException,
	       IOException {
	return (new URL(String
			.format(TEMPLATE,
				symbol,
				apiKey))).openStream();}}

					     

