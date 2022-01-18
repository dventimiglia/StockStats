package com.davidaventimiglia.doordash.util;

import java.util.*;

/**
 * ReversedPriorityQueue has a different orientation from that of the
 * standard java.util.PriorityQueue, which is "min-oriented."  I.e.,
 * the standard PriorityQueue guarantees that the head of the queue
 * (accessed with either peek() or poll()) will be the "least"
 * element, either according to the natural ordering of the elements,
 * or according to the supplied Comparator.  Given that, the
 * ReversedPriorityQueue is "max-oriented."  I.e., it guarantees that
 * the head of the queue will be the "greatest" element, either
 * according to the natural ordering of the elements or according to
 * the supplied Comparator.
 */
public class ReversedPriorityQueue<E> extends PriorityQueue<E> {

    static final long serialVersionUID = 0L;

    // Reversing the orientation of the standard PriorityQueue is
    // achieved via a Mirror helper class, which reverses the sense of
    // any Comparator given to it.

    private static class Mirror<E> implements Comparator<E> {
	protected Comparator<? super E> comparator;
	public Mirror () {}
	public Mirror (Comparator<? super E> comp) {comparator = comp;}
	@SuppressWarnings("unchecked") public int compare (E o1, E o2) {
	    if (comparator==null) return -1*((Comparable)o1).compareTo((Comparable)o2);
	    return -1*comparator.compare(o1, o2);}}

    // Pass-through constructors

    public ReversedPriorityQueue () {
	super(Collections.reverseOrder());}

    public ReversedPriorityQueue (Collection<? extends E> c) {
	this();
	addAll(c);}

    public ReversedPriorityQueue (int initialCapacity) {
	super(initialCapacity, new Mirror<E>());}

    public ReversedPriorityQueue (int initialCapacity, Comparator<? super E> comparator) {
	super(initialCapacity, Collections.reverseOrder(comparator));}

    public ReversedPriorityQueue (PriorityQueue<? extends E> c) {
	super(c);}

    public ReversedPriorityQueue (SortedSet<? extends E> c) {
	super(c);}}

	
