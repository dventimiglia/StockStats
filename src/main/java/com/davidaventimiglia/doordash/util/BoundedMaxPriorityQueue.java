package com.davidaventimiglia.doordash.util;

import java.util.*;

/**
 * BoundedMaxPriorityQueue is a BoundedQueue that evicts the "least"
 * element in order not to exceed its bounding capacity.
 *
 * NOTE: Thus, a "max-oriented" BoundedQueue like this one maintains
 * the "top-k" elements.
 */
public class BoundedMaxPriorityQueue<E> extends MaxPriorityQueue<E>
    implements BoundedQueue<E> {

    static final long serialVersionUID = 0L;

    protected int cap = DEFAULT_CAPACITY;

    public BoundedMaxPriorityQueue () {
	super();}

    public BoundedMaxPriorityQueue (Collection<? extends E> c) {
	super(c);}

    public BoundedMaxPriorityQueue (int capacity) {
	super(capacity);
	cap = capacity;}

    public BoundedMaxPriorityQueue (int capacity, Comparator<? super E> comparator) {
	super(capacity, comparator);
	cap = capacity;}

    public BoundedMaxPriorityQueue (PriorityQueue<? extends E> c) {
	super(c);}

    public BoundedMaxPriorityQueue (SortedSet<? extends E> c) {
	super(c);}

    @Override
    public int capacity () {
	return cap;}

    @Override
    public boolean add (E e) {
	if (!super.add(e)) return false;
	while (size()>capacity()) poll(); // eviction
	return true;}

    @Override
    public boolean offer (E e) {
	if (!super.offer(e)) return false;
	while (size()>capacity()) poll(); // eviction
	return true;}}
