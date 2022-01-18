package com.davidaventimiglia.doordash.model;

import java.io.*;
import java.net.*;

/**
 * QuoteStreamParser marks a type that can parse a stream of raw quote
 * data into bona-fide Quote objects.
 */
public interface QuoteStreamParser {
    Iterable<Quote> parse (InputStream in);}

