// Copyright (C) 2004-2009 Peter Luschny, MIT License applies.
// See http://en.wikipedia.org/wiki/MIT_License
// Visit http://www.luschny.de/math/factorial/FastFactorialFunctions.htm
// Comments mail to: peter(at)luschny.de
package org.treeingwalker.math.factorial;

import org.treeingwalker.math.Xmath;
import org.treeingwalker.math.arithmetic.Xint;
import org.treeingwalker.math.primes.IPrimeIteration;
import org.treeingwalker.math.primes.IPrimeSieve;
import org.treeingwalker.math.primes.PrimeSieve;

public class FactorialPrimeSchoenhage implements IFactorialFunction {

    private int[] primeList;
    private int[] multiList;

    public FactorialPrimeSchoenhage() {
    }

    @Override
    public String getName() {
        return "PrimeSchoenhage   ";
    }

    @Override
    public Xint factorial(int n) {
        // For very small n the 'NaiveFactorial' is ok.
        if (n < 20) {
            return Xmath.smallFactorial(n);
        }

        int log2n = 31 - Integer.numberOfLeadingZeros(n);
        int piN = 2 + (15 * n) / (8 * (log2n - 1));

        primeList = new int[piN];
        multiList = new int[piN];

        int len = primeFactors(n);
        return nestedSquare(len).shiftLeft(n - Integer.bitCount(n));
    }

    private Xint nestedSquare(int len) {
        if (len == 0) {
            return Xint.ONE;
        }

        // // multiList and primeList initialized in function 'factorial'!
        int i = 0, mult = multiList[0];

        while (mult > 1) {
            if ((mult & 1) == 1) // is mult odd ?
            {
                primeList[len++] = primeList[i];
            }

            multiList[i++] = mult / 2;
            mult = multiList[i];
        }
        if (len <= i) {
            return nestedSquare(i).square();
        }

        return Xint.product(primeList, i, len - i).multiply(nestedSquare(i).square());
    }

    private int primeFactors(int n) {
        IPrimeSieve sieve = new PrimeSieve(n);
        IPrimeIteration pIter = sieve.getIteration(3, n);

        int maxBound = n / 2, count = 0;

        for (int prime : pIter) {
            int m = prime > maxBound ? 1 : 0;

            if (prime <= maxBound) {
                int q = n;
                while (q >= prime) {
                    m += q /= prime;
                }
            }

            // multiList and primeList initialized in function 'factorial'!
            primeList[count] = prime;
            multiList[count++] = m;
        }
        return count;
    }
} // endOfFactorialPrimeSchoenhage
