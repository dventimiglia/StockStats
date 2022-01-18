package com.davidaventimiglia.doordash.model;

import java.util.*;

/**
 * Quote represents a stock market quote, aggregating the date along
 * with fiducial pricing data.
 *
 * NOTE: in this and in all other parts of the application, monetary
 * values (e.g., open, high, low, etc.) are integral values
 * representing ten-thousandths of a dollar (i.e., hundredths of a
 * penny).  First, the data are given with that resolution by the
 * third-party service.  Second, we eschew floating-point
 * representations to avoid round-off errors.
 */
public class Quote implements Comparable<Quote> {
    public final Date date;
    public final long open, high, low, close, volume;

    public Quote (Date d, long o, long h, long l, long c, long v) {
	if (d==null) throw new IllegalArgumentException("Date d cannot be null.");
	date = d;
	open = o;
	high = h;
	low = l;
	close = c;
	volume = v;}

    @Override
    public int compareTo (Quote e) {
	if (date.before(e.date)) return -1;
	if (date.after(e.date)) return 1;
	return 0;}

    @Override
    public boolean equals (Object o) {
	if (!(o instanceof Quote)) return false;
	if (compareTo((Quote)o)==0) return true;
	return false;}

    @Override
    public int hashCode () {
	return date.hashCode();}

    @Override
    public String toString () {
	return "" + Arrays.asList(date, open, high, low, close, volume);}}
