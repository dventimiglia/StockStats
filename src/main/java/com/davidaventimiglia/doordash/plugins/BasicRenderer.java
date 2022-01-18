package com.davidaventimiglia.doordash.plugins;

import com.davidaventimiglia.doordash.model.*;
import java.util.*;

/**
 * BasicRenderer is a Renderer (for Quote objects and other related
 * representations) that is extremely basic.  It just formats the data
 * as JSON.
 */
public class BasicRenderer implements Renderer {
    // TODO:  Implement this method for real.
    @Override
    public String render (Quote q) {
	return "" + q;}

    // TODO:  Implement this method for real.
    @Override
    public String render (String s) {
	return s;}

    @Override
    public String render (Map<String, Stats.Pair> m) {
	return String
	    .format("{\"buy\":\"%s\", \"sell\":\"%s\", \"profit\":%s}",
		    m.get("min").date,
		    m.get("max").date,
		    m.get("max").price);}}
