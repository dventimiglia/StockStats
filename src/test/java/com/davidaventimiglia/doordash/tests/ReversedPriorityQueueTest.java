package com.davidaventimiglia.doordash.tests;

import com.davidaventimiglia.doordash.util.*;
import java.util.*;
import org.junit.*;
import static org.junit.Assert.*;

public class ReversedPriorityQueueTest {
    @Test
    public void peekReturnsMost () {
	PriorityQueue<Integer> p = new ReversedPriorityQueue<>();
	for (int i = 10; i>=0; i--) p.add(i);
	int probe = p.peek();
	assertEquals(10, probe);}

    @Test
    public void pollReturnsMost () {
	PriorityQueue<Integer> p = new ReversedPriorityQueue<>();
	for (int i = 10; i>=0; i--) p.add(i);
	int probe = p.poll();
	assertEquals(10, probe);}}

