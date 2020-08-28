package Datenbanken.a3;


import Datenbanken.hashing.HashFunction;

import java.util.Collection;

public class ColumnImprint extends BloomFilter<Double> {
    // TODO

    int buckedSize = 5;
    public ColumnImprint(int numBits, double min, double max) {
        super(numBits, new HashFunction<Double>() {

            /*

            @Override
            public int hash(Double element) {
                public int hash (Double element){
                    if (element < min + buckedSize)
                        return 0;
                    else if (element >= max - buckedSize)
                        return numBits - 1;
                    else
                        return (int) ((element - min) / buckedSize);
                }
            }

             */
        });
    }

}
