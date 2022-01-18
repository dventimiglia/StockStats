package com.davidaventimiglia.doordash.model;

import java.io.*;
import java.net.*;

/**
 * QuoteStreamGenerator marks a type that can generate a raw stream of
 * quote feed data for a ticker symbol, somehow.
 */
public interface QuoteStreamGenerator {
    InputStream generate (String symbol) throws MalformedURLException, IOException;}


