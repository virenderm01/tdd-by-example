package guru.springframework;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class MoneyTest {
    @Test
    void testMultiplicationDollar(){
        Money five = Money.getDollar(5);
        assertEquals(Money.getDollar(10),five.times(2));
        assertEquals(Money.getDollar(15),five.times(3));
        Money fiveFranc = Money.getFranc(5);
        assertEquals(Money.getFranc(10),fiveFranc.times(2));
        assertEquals(Money.getFranc(15),fiveFranc.times(3));
    }

    @Test
    void testEqualityDollar() {
        Money one = Money.getDollar(5);
        assertEquals(one,Money.getDollar(5));
        assertNotEquals(one, Money.getDollar(10));
        assertNotEquals(Money.getDollar(5), Money.getFranc(5));
        Money franc = Money.getFranc(5);
        assertEquals(franc,Money.getFranc(5));
        assertNotEquals(franc, Money.getFranc(10));
    }

    @Test
    void testCurrency() {
        assertEquals("USD", Money.getDollar(1).currency);
        assertEquals("CHF", Money.getFranc(1).currency);

    }

    @Test
    void testAddition() {
        Money five = Money.getDollar(5);
        Expression sum = five.plus(five);
        Bank bank = new Bank();
        Money reduced = bank.reduce(sum,"USD");
        assertEquals(Money.getDollar(10),reduced);
    }

    @Test
    void testPlusReturnsSum(){
        Money five = Money.getDollar(5);
        Expression result = five.plus(five);
        Sum sum = (Sum)result;
        assertEquals(five,sum.augmend);
        assertEquals(five,sum.addmend);

    }

    @Test
    void testReducersSum(){
        Expression sum = new Sum(Money.getDollar(3), Money.getDollar(4));
        Bank bank = new Bank();
        Money result = bank.reduce(sum,"USD");
        assertEquals(Money.getDollar(7), result);
    }

    @Test
    void testReduceMoneyDifferentCurrency(){
        Bank bank = new Bank();
        bank.addRate("CHF","USD",2);
        Money result = bank.reduce(Money.getFranc(2),"USD");
        assertEquals(Money.getDollar(1), result);
    }

    @Test
    void testIdentityRate(){
        assertEquals(1, new Bank().rate("USD","USD"));
        assertEquals(1, new Bank().rate("CHF","CHF"));
    }

    @Test
    void testReduceMoney(){
            Bank bank = new Bank();
            Money result = bank.reduce(Money.getDollar(11),"USD");
            assertEquals(Money.getDollar(11), result);
    }

    @Test
    public void testMixedAddition(){
        Expression fiveDollar = Money.getDollar(5);
        Expression tenFrancs = Money.getFranc(10);
        Bank bank = new Bank();
        bank.addRate("CHF", "USD",2);
        Money result = bank.reduce(fiveDollar.plus(tenFrancs),"USD");
        assertEquals(Money.getDollar(10),result);
    }
    @Test
    public void testSumPlusMoney(){
        Expression fiveDollar = Money.getDollar(5);
        Expression tenFrancs = Money.getFranc(10);
        Bank bank = new Bank();
        bank.addRate("CHF", "USD",2);
        Expression sum = new Sum(fiveDollar,tenFrancs).plus(fiveDollar);
        Money result = bank.reduce(sum,"USD");
        assertEquals(Money.getDollar(15),result);
    }

    @Test
    public void testSumTimes(){
        Expression fiveDollar = Money.getDollar(5);
        Expression tenFrancs = Money.getFranc(10);
        Bank bank = new Bank();
        bank.addRate("CHF", "USD",2);
        Expression sum = new Sum(fiveDollar,tenFrancs).times(2);
        Money result = bank.reduce(sum,"USD");
        assertEquals(Money.getDollar(20),result);

    }

}
