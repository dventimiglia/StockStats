package com.davidaventimiglia.redacted.tests;

import com.davidaventimiglia.redacted.util.*;
import java.util.*;
import org.junit.*;
import static org.junit.Assert.*;

public class BoundedMaxPriorityQueueTest {
    @Test
    public void defaultCapacityAndAdd () {
	BoundedQueue<Integer> p = new BoundedMaxPriorityQueue<>();
	int probe = p.capacity();
	assertEquals(11, probe);
	for (int i = 100; i>=1; i--) p.add(i);
	probe = p.peek();
	assertEquals(11, probe);}

    @Test
    public void elevatedCapacityAndAdd () {
	BoundedQueue<Integer> p = new BoundedMaxPriorityQueue<>(100);
	int probe = p.capacity();
	assertEquals(100, probe);
	for (int i = 200; i>=1; i--) p.add(i);
	probe = p.peek();
	assertEquals(100, probe);}


    @Test
    public void defaultCapacityAndOffer () {
	BoundedQueue<Integer> p = new BoundedMaxPriorityQueue<>();
	int probe = p.capacity();
	assertEquals(11, probe);
	for (int i = 100; i>=1; i--) p.offer(i);
	probe = p.peek();
	assertEquals(11, probe);}

    @Test
    public void elevatedCapacityAndOffer () {
	BoundedQueue<Integer> p = new BoundedMaxPriorityQueue<>(100);
	int probe = p.capacity();
	assertEquals(100, probe);
	for (int i = 200; i>=1; i--) p.offer(i);
	probe = p.peek();
	assertEquals(100, probe);}
    
    @Test
    public void elevatedCapacityChecksOut () {
	BoundedQueue<Integer> p = new BoundedMaxPriorityQueue<>(100);
	int probe = p.capacity();
	assertEquals(100, probe);}}
