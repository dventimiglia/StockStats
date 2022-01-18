// Scratch script for the Java JShell interactive tool.  See:
// https://docs.oracle.com/javase/9/jshell/introduction-jshell.htm#JSHEL-GUID-630F27C8-1195-4989-9F6B-2C51D46F52C8

import com.davidaventimiglia.doordash.plugins.*;
import com.davidaventimiglia.doordash.model.*;
import java.io.*;
QuoteService qs = new AlphaVantageQuoteService("IYCIAEV4C0G4HQED");
Stats stats = new MaxProfitStats();
System.out.println(stats.getStats(qs.quotes(new FileInputStream("msft.js"))));
