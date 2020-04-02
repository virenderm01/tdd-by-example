package guru.springframework;

import java.util.HashMap;
import java.util.Map;

public class Bank {
    private final Map<Pair,Integer> rateMap = new HashMap<>();
    public Money reduce(Expression source, String toCurrency) {
        return source.reduce(this,toCurrency);
           /*if(source instanceof  Money){
               return (Money) source;
           }
           Sum sum = (Sum) source;

            return sum.reduce(toCurrency);*/
    }

    public void addRate(String fromCurrency, String toCurrency, int rate) {
        rateMap.put(new Pair(fromCurrency,toCurrency),rate);
    }

    public int rate(String fromCurrency, String toCurrency){
        if(fromCurrency.equals(toCurrency)){
            return 1;
        }
        return rateMap.get(new Pair(fromCurrency,toCurrency));
    }


}
