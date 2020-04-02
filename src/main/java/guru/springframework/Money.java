package guru.springframework;

public class Money implements Expression {

    protected final int amount;
    protected final String currency;

    public Money(int amount, String currency) {
        this.currency = currency;
        this.amount = amount;
    }

    public static Money getDollar(int amount){
            return new Money(amount, "USD");
    }
    public static Money getFranc(int amount){
        return new Money(amount,"CHF");
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        Money money = (Money) o;
        return this.amount == money.amount && this.currency.equals(money.currency);
    }

    public  Expression times(int multiplier){
            return new Money(this.amount*multiplier, this.currency);
    }

    public Expression plus(Expression addmend) {
        return new Sum(this, addmend);
    }

    @Override
    public Money reduce(Bank bank,String to){
        //return this;
        int rate = bank.rate(this.currency,to);
        return new Money(amount/rate,to);

    }
}
