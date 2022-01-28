package com.davidaventimiglia.redacted.util;

import java.util.*;

/**
 * MinPriorityQueue is simply the standard java.util.PriorityQueue in
 * disguise, since the standard PriorityQueue already is
 * "min-oriented."  I.e., this is just the thinnest of
 * wrapper-by-extension sub-class, created for the sake of naming
 * consistency with MaxPriorityQueue.
 */
public class MinPriorityQueue<E> extends PriorityQueue<E> {

    static final long serialVersionUID = 0L;

    // Pass-through constructors

    public MinPriorityQueue () {
	super();}

    public MinPriorityQueue (Collection<? extends E> c) {
	super(c);}

    public MinPriorityQueue (int initialCapacity) {
	super(initialCapacity);}

    public MinPriorityQueue (int initialCapacity, Comparator<? super E> comparator) {
	super(initialCapacity, comparator);}

    public MinPriorityQueue (PriorityQueue<? extends E> c) {
	super(c);}

    public MinPriorityQueue (SortedSet<? extends E> c) {
	super(c);}}
