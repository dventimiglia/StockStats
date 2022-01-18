package com.davidaventimiglia.doordash.plugins;

import com.davidaventimiglia.doordash.model.*;
import com.davidaventimiglia.doordash.util.*;
import java.util.*;

/**
 * KadaneMaxProfitStats is a Stats implementation that computes the
 * characteristics of the maximum possible profit for stocks over a
 * given recent interval, based on the high value for each day of
 * trading.
 *
 * It uses an adaptation of Kadane's algorithm (see:
 * https://en.wikipedia.org/wiki/Maximum_subarray_problem#Kadane's_algorithm_(Algorithm_3:_Dynamic_Programming)).
 */
public class KadaneMaxProfitStats implements Stats {

    /**
     * Helper method for taking the maximum over a Pair
     */
    public static Pair max (Pair p1, Pair p2) {
	return p1.price > p2.price ? p1 : p2;}


    /**
     * Helper method for taking the minimum over a Pair
     */
    public static Pair min (Pair p1, Pair p2) {
	return p1.price < p2.price ? p1 : p2;}

    protected final int k;


    /**
     * Constructor for the default recent interval, which extends
     * backward through a time-series of quotes, from the most recent
     * Quote to the kth-most recent Quote.  Here, k defaults to 11.
     */
    public KadaneMaxProfitStats () {
	this(11);}

    /**
     * Constructor for a custom recent interval, which extends
     * backward through a time-series of quotes, from the most recent
     * Quote to the kth-most recent Quote.  Here, k is given as a
     * parameter.
     *
     * @param kdays - days backward for the interval
     */
    public KadaneMaxProfitStats (int kdays) {
	k = kdays;}
    
    /**
     * Compute the stats:  {buy date, sell date, profit}
     */
    @Override
    public Map<String, Pair> getStats (Iterable<? extends Quote> quotes) {
	BoundedQueue<Quote> pq = new BoundedMinPriorityQueue<>(k);
	for (Quote q : quotes) pq.add(q);
	List<Quote> sorted = new ArrayList<>();
	sorted.addAll(pq);
	if (sorted.isEmpty()) return new HashMap<String, Pair>();
	Collections.sort(sorted);

	Pair maxProfitSoFar = new Pair(null, 0);
	Pair maxProfitEndingHere = new Pair(null, 0);
	Pair minPriceSoFar = new Pair(sorted.get(0).date, sorted.get(0).high);

	for (Quote q : sorted) {
	    maxProfitEndingHere = max(new Pair(null, 0), new Pair(q.date, q.high - minPriceSoFar.price));
	    minPriceSoFar = min(minPriceSoFar, new Pair(q.date, q.high));
	    maxProfitSoFar = max(maxProfitEndingHere, maxProfitSoFar);}
	Map<String, Pair> payload = new HashMap<>();
	payload.put("min", minPriceSoFar);
	payload.put("max", maxProfitSoFar);
	return payload;}}
