package com.davidaventimiglia.doordash.util;

import java.util.*;

/**
 * MaxPriorityQueue is simply the ReversedPriorityQueue in disguise,
 * since the latter already is "max-oriented."
 */
public class MaxPriorityQueue<E> extends ReversedPriorityQueue<E> {

    static final long serialVersionUID = 0L;

    // Pass-through constructors

    public MaxPriorityQueue () {
	super();}

    public MaxPriorityQueue (Collection<? extends E> c) {
	super(c);}

    public MaxPriorityQueue (int initialCapacity) {
	super(initialCapacity);}

    public MaxPriorityQueue (int initialCapacity, Comparator<? super E> comparator) {
	super(initialCapacity, comparator);}

    public MaxPriorityQueue (PriorityQueue<? extends E> c) {
	super(c);}

    public MaxPriorityQueue (SortedSet<? extends E> c) {
	super(c);}}
