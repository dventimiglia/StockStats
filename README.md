# README #

### Introduction ####

This project offers a solution to the following coding challenge.

    In your language of choice, write a web service that takes in a stock
    symbol (e.g. AAPL, GOOG) and returns a single buy and sell date that
    yields the max profit over the last 180 days. You may use any
    third-party api to retrieve historical stock prices, but only consider
    the high price for the day. Please include a README describing your
    web service and appropriate documentation.
	
	Correctly and efficiently return the max profit.
	
This challenge contains some ambiguity.  First, we are commanded to
return "a single buy *and* sell date *[emphasis added]*".  That's a
little confusing, as it's unclear if we are return a single item or
two items.  I guessed that we are to return a single composite item,
containing the two requested values.  Second, we are commanded to
return dates (buy and sell), but then later we are commanded to return
the (max) profit.  Which are we to return?  Dates or a profit?  Again,
I guessed that we are to return a composite object comprising the buy
and sell dates, and the profit between them.
	
### Build ###

    mvn package
	
### Use ####

Access the Alpha Vantage third-party API and fetch the last 20 years
of historical data (!!!) for stock symbols (possibly multiple, using
an API key.  The API key is provided via an environment variable
configuration parameter API_KEY (see: https://12factor.net/config).
One or more stock ticker symbols can be provided on the command-line.
Data are written in Alpha Vantage's raw JSON format.

NOTE: Alpha Vantage doesn't give us much control over the amount of
data that we can retrieve.  Either we get (up to) 20 years of
historical data, or we only get the last 100 days.  As the latter is
not enough to calculate statistics over the last 180 days, we are
forced for now to get all the data.  For this reason alone, it would
be worth finding and plugging in a different third-party data
provider.

    API_KEY=<Alpha Vantage API key> java -cp target/stockstats-1.0-SNAPSHOT.jar com.davidaventimiglia.redacted.FetchQuotes <ticker symbol>... 
	
Parse raw quote feed data into quote objects, taking the data in from
standard input, and writing the rendered quote data (in an extremely
simple rendering) to standard output.

    cat <file> | java -cp target/stockstats-1.0-SNAPSHOT.jar com.davidaventimiglia.redacted.main.ParseQuotes
	
Sample files for MSFT and GOOGL are provided in this directory.

Generate stats (max profit potential over last 180 days) for quote
feed data being fed in from standard input.

    cat <file> | java -cp target/stockstats-1.0-SNAPSHOT.jar com.davidaventimiglia.redacted.main.StreamStats
	
Generate stats (max profit potential over last 180 days) for quote
feed data fetched live from the third-party data service.

    API_KEY=<API key> java -cp target/stockstats-1.0-SNAPSHOT.jar com.davidaventimiglia.redacted.main.SymbolStats <stock ticker symbol>...
	
	
Start a web service that generates and returns stats (max profit over
the last 180 days) for quote feed data fetched live from the
third-party data service.

    API_KEY=IYCIAEV4C0G4HQED java -cp target/stockstats-1.0-SNAPSHOT.jar com.davidaventimiglia.redacted.main.SymbolStatsServer
	
Get stats data for a symbol using the web service.

    curl -s http://localhost:4567/stats/<stock ticker symbol>
	
Note that you can also use a browser.

Note also, that anywhere an API key is needed, you can use mine, which
is IYCIAEV4C0G4HQED.

### Implementation Comments ###

Choosing the Alpha Vantage third-party data service presented an
interesting challenge.  As I said, there's little choice other than to
use their "full" API, which gets all of the historical data.  But
then, we want only to regard the last 180 days.  The naive and wrong
thing to do would just be to take the last 180 (or "the last k")
quotes.  However, though in my testing the data have always appeared
in chronological order, nothing about the API guarantees this.  A
naive and correct thing to do would be to soak up all of the data into
an in-memory data structure, sort it, and then take the top k
elements.  A less naive and correct thing to do is to use a bounded
data structure that maintains the "top k" elements on-the-fly.  This
is the strategy I adopted, and is the reason that I wrote the
BoundedQueue implementations and related types that are found in the
util package.

### Extensions ###

* Refactor the code
* ~~Write more tests~~
* Make the port number configurable
* ~~Make the number of days configurable~~
* Chunk the data in order to lower the memory footprint
* Fix up the Stats data model, which is a little clunky.

<!--  LocalWords:  cp IYCIAEV HQED BoundedQueue util
 -->
