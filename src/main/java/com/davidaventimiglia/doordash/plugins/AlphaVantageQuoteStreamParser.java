package com.davidaventimiglia.doordash.plugins;

import com.davidaventimiglia.doordash.model.*;
import java.io.*;
import java.net.*;
import java.text.*;
import java.util.*;
import javax.script.*;

/**
 * AlphaVantageQuoteStreamParser is a QuoteStreamParser that parses
 * raw quote feed data in the Alpha Vantage format (see
 * https://www.alphavantage.co/), translating the data into an
 * Iterable over Quote objects.
 */
public class AlphaVantageQuoteStreamParser implements QuoteStreamParser {

    // Exploiting the bundled JavaScript interpreter is a quick hack
    // for parsing JSON, until Java gets a bona-fide JSON parser.
    private static ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");

    // Date parser
    private static SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd");

    @SuppressWarnings("unchecked") // Going from Nashorn-land to Java-land involves ugly casts.
    @Override
    public Iterable<Quote> parse (InputStream in) {
	// TODO: We use a Scanner with the EOF delimiter \A to soak up
	// all the content in one go.  That's fine, but the memory
	// footprint could be better.  May want to play with the
	// Scanner delimiters in order to break up the data into a
	// stream of individual quote objects.
	return new Iterable<Quote> () {
	    @Override
	    public Iterator<Quote> iterator () {
		return new Iterator<Quote> () {
		    private Iterator<Map.Entry<String, Map<String, String>>> it;
		    @Override
		    public boolean hasNext () {
			try {
			    if (it==null)
				it = ((Map<String, Map<String, Map<String, String>>>)
				      engine.eval(String
						  .format("JSON.parse('%s')",
							  new Scanner(in)
							  .useDelimiter("\\A")
							  .next()
							  .replaceAll("\n", ""))))
				    .get("Time Series (Daily)")
				    .entrySet()
				    .iterator();
			    return it.hasNext();}
			catch (Exception e) {throw new RuntimeException(e);}}
		    @Override
		    public Quote next () {
			try {
			    if (!hasNext()) throw new NoSuchElementException();
			    Map.Entry<String, Map<String, String>> e = it.next();
			    return new Quote(parser.parse(e.getKey()),
					     Long.parseLong(e
							    .getValue()
							    .get("1. open")
							    .replaceAll("\\.", "")),
					     Long.parseLong(e
							    .getValue()
							    .get("2. high")
							    .replaceAll("\\.", "")),
					     Long.parseLong(e
							    .getValue()
							    .get("3. low")
							    .replaceAll("\\.", "")),
					     Long.parseLong(e
							    .getValue()
							    .get("4. close")
							    .replaceAll("\\.", "")),
					     Long.parseLong(e
							    .getValue()
							    .get("5. volume")
							    .replaceAll("\\.", "")));}
			catch (Exception e) {throw new RuntimeException(e);}}};}};}}
					     

