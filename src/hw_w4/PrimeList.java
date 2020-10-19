package hw_w4;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * 
 * @overview PrimeList is an auto-populated collection that generate first 10 prime numbers
 * @attributes primes 	LinkedList<Integer>
 */
public class PrimeList {
	
    private LinkedList<Integer> primes;

    /**
     * @effects initial prime linked list with very first prime number 
     */
    public PrimeList(){
    	LinkedList<Integer> prime  = new LinkedList<Integer>();
        prime.add(2);
        this.primes = prime;
    }
    
    /**
     * @effects return size of this
     * 
     */
    public int size(){
        return primes.size();
    }
    
    /**
     * @effect return the last element in this
     * 
     */
    public int lastPrime(){
        return primes.getLast();
    }
    
    /**
     * @effects return a clone object of this
     */
    public LinkedList<Integer> getPrimeList(){
        return (LinkedList<Integer>) primes.clone();
    }

    @Override
    public String toString() {
        return "PrimeList{" +
                "primes=" + primes +
                '}';
    }

    /**
     * @effects if posInt belongs to this
     * 				return true
     * 			else 
     * 				if posInt is a prime number
     * 					return true
     * 				return false
     * 				
     * @param posInt
     */
    private boolean isPrime(int posInt) {
        if (primes.contains(posInt)){
            return true;
        }
        
        for (int i = 2; i <= Math.sqrt(posInt); i++)
        {
            if (posInt%i != 0) {
                continue;
            }
            return false;
        }
        return true;
    }

    public Iterator<Integer> iterator(){
        return new PrimeListGen<Integer>(size(), lastPrime());
    }
    class PrimeListGen<Integer> implements Iterator<java.lang.Integer> {
        private int index;
        private int primeNumber;

        public PrimeListGen(int index, int primeNumber){
            this.index = index;
            this.primeNumber = primeNumber;
        }

        @Override
        public boolean hasNext() {
            return true;
        }

        @Override
        public java.lang.Integer next() {
            int nextPrimeNumber = primeNumber + 1;
            while(!isPrime(nextPrimeNumber)){
                nextPrimeNumber++;
            }
            index++;
            primes.add(nextPrimeNumber);
            return nextPrimeNumber;
        }
    }

}

