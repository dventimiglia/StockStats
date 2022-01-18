package com.davidaventimiglia.doordash.tests;

import com.davidaventimiglia.doordash.util.*;
import java.util.*;
import org.junit.*;
import static org.junit.Assert.*;

public class BoundedMinPriorityQueueTest {
    @Test
    public void defaultCapacityAndAdd () {
	BoundedQueue<Integer> p = new BoundedMinPriorityQueue<>();
	int probe = p.capacity();
	assertEquals(11, probe);
	for (int i = 100; i>=1; i--) p.add(i);
	probe = p.peek();
	assertEquals(90, probe);}

    @Test
    public void elevatedCapacityAndAdd () {
    	BoundedQueue<Integer> p = new BoundedMinPriorityQueue<>(100);
    	int probe = p.capacity();
    	assertEquals(100, probe);
    	for (int i = 200; i>=1; i--) p.add(i);
    	probe = p.peek();
    	assertEquals(101, probe);}

    @Test
    public void defaultCapacityAndOffer () {
	BoundedQueue<Integer> p = new BoundedMinPriorityQueue<>();
	int probe = p.capacity();
	assertEquals(11, probe);
	for (int i = 100; i>=1; i--) p.offer(i);
	probe = p.peek();
	assertEquals(90, probe);}

    @Test
    public void elevatedCapacityAndOffer () {
    	BoundedQueue<Integer> p = new BoundedMinPriorityQueue<>(100);
    	int probe = p.capacity();
    	assertEquals(100, probe);
    	for (int i = 200; i>=1; i--) p.offer(i);
    	probe = p.peek();
    	assertEquals(101, probe);}
    
    @Test
    public void elevatedCapacityChecksOut () {
	BoundedQueue<Integer> p = new BoundedMinPriorityQueue<>(100);
	int probe = p.capacity();
	assertEquals(100, probe);}}
