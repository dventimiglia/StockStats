package com.davidaventimiglia.doordash.model;

import java.util.*;

/**
 * Stats marks a type that can generate statistics data for a sequence
 * (Iterable) of Quote objects.
 */
public interface Stats {

    /**
     * Helper class that joins a date with a monetary value.  The
     * monetary value could represent a buy price, a sell price, or
     * the profit as of some date.
     */
    public static class Pair {
	public final Date date;
	public final long price;
	public Pair (Date d, long p) {
	    date = d;
	    price = p;}}

    /**
     * Get the statistics
     */
    Map<String, Pair> getStats (Iterable<? extends Quote> it);}
    
