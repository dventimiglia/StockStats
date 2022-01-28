package com.davidaventimiglia.redacted.model;

import java.io.*;
import java.net.*;
import java.util.*;

/**
 * Renderer marks a type that can translate various data
 * representations in a textual format.
 */
public interface Renderer {
    String render (Quote q);
    String render (String s);
    String render (Map<String, Stats.Pair> m);}


