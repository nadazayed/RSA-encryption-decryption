import java.math.BigInteger;

public class RSAEncrypt
{
    public byte[] enc (byte []M, BigInteger key, BigInteger n)
    {
        BigInteger temp = new BigInteger(M);
        return temp.modPow(key, n).toByteArray();
    }
}
