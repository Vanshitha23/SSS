import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.*;
import java.util.*;
import org.json.JSONObject;

public class SecretSharing {

    // Decode Y value from given base
    public static BigInteger decode(String value, int base) {
        return new BigInteger(value, base);
    }

    // Lagrange interpolation at x = 0
    public static BigInteger lagrangeInterpolation(List<BigInteger[]> points, int k) {
        BigInteger result = BigInteger.ZERO;

        for (int i = 0; i < k; i++) {
            BigInteger xi = points.get(i)[0];
            BigInteger yi = points.get(i)[1];

            BigInteger numerator = yi;
            BigInteger denominator = BigInteger.ONE;

            for (int j = 0; j < k; j++) {
                if (i != j) {
                    BigInteger xj = points.get(j)[0];
                    numerator = numerator.multiply(xj.negate());
                    denominator = denominator.multiply(xi.subtract(xj));
                }
            }

            // yi * Π(0 - xj)/(xi - xj)
            result = result.add(numerator.divide(denominator));
        }

        return result;
    }

    public static void solve(String filename) throws IOException {
        String content = Files.readString(Paths.get(filename));
        JSONObject obj = new JSONObject(content);

        JSONObject keys = obj.getJSONObject("keys");
        int n = keys.getInt("n");
        int k = keys.getInt("k");

        List<BigInteger[]> points = new ArrayList<>();

        for (String key : obj.keySet()) {
            if (key.equals("keys")) continue;
            JSONObject pair = obj.getJSONObject(key);

            int base = Integer.parseInt(pair.getString("base"));
            String value = pair.getString("value");

            BigInteger x = new BigInteger(key);
            BigInteger y = decode(value, base);

            points.add(new BigInteger[]{x, y});
        }

        // Sort by x value for consistency
        points.sort(Comparator.comparing(a -> a[0]));

        // Take only first k points
        List<BigInteger[]> subset = points.subList(0, k);

        BigInteger secret = lagrangeInterpolation(subset, k);
        System.out.println("Secret from " + filename + ": " + secret);
    }

    public static void main(String[] args) throws Exception {
        solve("testcase1.json");
        solve("testcase2.json");
    }
}
