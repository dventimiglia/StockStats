package com.davidaventimiglia.doordash.util;

import java.util.*;

/**
 * Largely a marker-interface for Queue implementations that have a
 * fixed size.  The policy for evicting elements in order not to
 * exceed the bounded capacity will depend on the implementation.  For
 * instance, PriorityQueue implementations might evict either the
 * "least" or the "greatest" element.
 */
public interface BoundedQueue<E> extends Queue<E> {

    // For the standard java.util.PriorityQueue, the internal capacity
    // (which is NOT a bound) defaults to 11, so we adopt that here.

    public final static int DEFAULT_CAPACITY = 11;

    /**
     * Report the bounding capacity.
     */
    int capacity ();}

    
