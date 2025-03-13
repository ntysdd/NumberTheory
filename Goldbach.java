import java.math.BigInteger;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Goldbach {

  public static BigInteger f(BigInteger n, BigInteger p) {
    BigInteger x = n.subtract(p);
    List<BigInteger> factors = BigIntegerFactorization.factors(x);
    return factors.get(0);
  }

  public static void main(String[] args) {
    if (args.length < 1) {
      System.out.println("Input a number");
      return;
    }
    BigInteger n = new BigInteger(args[0]);

    BigInteger n3 = n.subtract(BigInteger.valueOf(3));

    Map<BigInteger, Long> map = new TreeMap<>();

    for (BigInteger i = BigInteger.valueOf(3); i.compareTo(n3) <= 0;
         i = i.add(BigInteger.ONE)) {
      if (!BigIntegerFactorization.isPrime(i)) {
        continue;
      }

      BigInteger k = i;
      for (int j = 1; j <= 100; j++) {
        k = f(n, k);
      }
      System.out.println(i + " " + k);

      Long count = map.get(k);
      if (count == null) {
        count = 0L;
      }
      map.put(k, count + 1);
    }
    System.out.println();

    double total = 0;
    double hit = 0;
    for (var entry : map.entrySet()) {
      System.out.println(entry.getKey() + " " + entry.getValue());

      BigInteger k = entry.getKey();
      if (entry.getValue() == 1L && n.mod(k).signum() != 0) {
        total++;
        if (BigIntegerFactorization.isPrime(k) &&
            BigIntegerFactorization.isPrime(n.subtract(k))) {
          hit++;
        }
      }
    }
    System.out.println();
    System.out.println(hit / total);
  }
}
