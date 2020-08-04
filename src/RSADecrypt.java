import java.math.BigInteger;

public class RSADecrypt
{
    public byte []dec (byte []c, BigInteger key, BigInteger n)
    {
        BigInteger temp = new BigInteger(c);
        return temp.modPow(key, n).toByteArray();
    }
}
