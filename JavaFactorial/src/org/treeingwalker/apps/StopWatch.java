// Copyright (C) 2004-2009 Peter Luschny, MIT License applies.
// See http://en.wikipedia.org/wiki/MIT_License
// Visit http://www.luschny.de/math/factorial/FastFactorialFunctions.htm
// Comments mail to: peter(at)luschny.de
package org.treeingwalker.apps;

/**
 * StopWatch
 * 
 * @author Peter Luschny
 * @version 2001-05-12
 */
public class StopWatch {

    private long elapsedCount;
    private long startCount;

    /**
     * Start the time-counter.
     */
    public void start() {
        startCount = System.currentTimeMillis();
    }

    /**
     * Stop the time-counter
     */
    public void stop() {
        final long stopCount = System.currentTimeMillis();
        elapsedCount += (stopCount - startCount);
    }

    /**
     * Clear the elapsed time-counter.
     */
    public void clear() {
        elapsedCount = 0;
    }

    /**
     * Get the elapsed time converted to seconds.
     * 
     * @return elapsed time converted to seconds
     */
    public double getSeconds() {
        return elapsedCount / 1000.0;
    }

    /**
     * Get the elapsed time converted to milli seconds.
     * 
     * @return elapsed time converted to milli seconds
     */
    private long getMilliSeconds() {
        return elapsedCount;
    }

    /**
     * Get the elapsed time as a formatted string.
     * @return milli seconds as string
     */
    @Override
    public String toString() {
        return getMilliSeconds() + " ms";
    }
}
