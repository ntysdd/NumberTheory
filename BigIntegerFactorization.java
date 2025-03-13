import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BigIntegerFactorization {
  public static final BigInteger TWO = BigInteger.valueOf(2);

  public static boolean isPrime(BigInteger n) {
    return n.signum() > 0 && n.isProbablePrime(100);
  }

  public static BigInteger factor(BigInteger n) {
    n = n.abs();
    if (n.compareTo(BigInteger.ONE) <= 0) {
      return BigInteger.ONE;
    }
    if (n.mod(TWO).signum() == 0) {
      return TWO;
    }
    if (isPrime(n)) {
      return n;
    }
    for (int i = 3; i < 300; i += 2) {
      if (!isPrime(BigInteger.valueOf(i))) {
        continue;
      }
      BigInteger val = BigInteger.valueOf(i);
      if (n.mod(val).signum() == 0) {
        return val;
      }
    }
    for (int c = 1; c <= 10; c++) {
      BigInteger x = TWO;
      BigInteger y = TWO;
      BigInteger d = BigInteger.ONE;
      BigInteger bc = BigInteger.valueOf(c);

      while (d.equals(BigInteger.ONE)) {
        x = x.pow(2).add(bc).mod(n);
        y = y.pow(2).add(bc).mod(n);
        y = y.pow(2).add(bc).mod(n);
        d = x.subtract(y).abs().gcd(n);
      }
      if (!d.equals(n)) {
        return d;
      }
    }
    for (BigInteger i = BigInteger.valueOf(301);; i = i.add(TWO)) {
      if (!isPrime(i)) {
        continue;
      }
      if (n.mod(i).signum() == 0) {
        return i;
      }
    }
  }

  public static List<BigInteger> factors(BigInteger n) {
    n = n.abs();
    List<BigInteger> result = new ArrayList<>();
    if (n.compareTo(BigInteger.ONE) <= 0) {
      return result;
    }

    BigInteger factor1 = factor(n);
    if (n.equals(factor1)) {
      result.add(n);
      return result;
    }
    BigInteger factor2 = n.divide(factor1);

    List<BigInteger> res1 = factors(factor1);
    List<BigInteger> res2 = factors(factor2);
    result.addAll(res1);
    result.addAll(res2);
    Collections.sort(result);
    return result;
  }

  public static void main(String[] args) {
    System.out.println(factors(new BigInteger("119214411671")));
  }
}
