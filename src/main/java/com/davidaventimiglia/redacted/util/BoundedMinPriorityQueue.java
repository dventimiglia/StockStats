package com.davidaventimiglia.redacted.util;

import java.util.*;

/**
 * BoundedMinPriorityQueue is a BoundedQueue that evicts the "least"
 * element in order not to exceed its bounding capacity.
 *
 * NOTE: Thus, a "min-oriented" BoundedQueue like this one maintains
 * the "top-k" elements.
 */
public class BoundedMinPriorityQueue<E> extends MinPriorityQueue<E>
    implements BoundedQueue<E> {

    static final long serialVersionUID = 0L;

    protected boolean min;
    protected int cap = DEFAULT_CAPACITY;

    public BoundedMinPriorityQueue () {
	super();}

    public BoundedMinPriorityQueue (Collection<? extends E> c) {
	super(c);}

    public BoundedMinPriorityQueue (int capacity) {
	super(capacity);
	cap = capacity;}

    public BoundedMinPriorityQueue (int capacity, Comparator<? super E> comparator) {
	super(capacity, comparator);
	cap = capacity;}

    public BoundedMinPriorityQueue (PriorityQueue<? extends E> c) {
	super(c);}

    public BoundedMinPriorityQueue (SortedSet<? extends E> c) {
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
