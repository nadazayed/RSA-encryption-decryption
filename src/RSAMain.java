//SNDR KUA{e,n}    KRA{d,n}
//RCVR KUB{e,n}    KRB{d,n}
//ENC  C = (M pow e) mod n  => to encrypt with public key
//DEC  M = (C pow d) mod n  => to decrypt with private key
//DOUBLE ENC C = (E:KUB  =>  E:KRA)       D = (D:KUA  =>  D:KRB)

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;

public class RSAMain
{
    public static void main (String[] args)
    {
        //Generate sender public and private keys
        RSAGenKey sndr = new RSAGenKey();
        BigInteger []KUA = sndr.KU(); // public {e,n}
        BigInteger []KRA = sndr.KR(); // private {d,n}

        //Generate receiver public and private keys
        RSAGenKey rcvr = new RSAGenKey();
        BigInteger []KUB = rcvr.KU(); // public {e,n}
        BigInteger []KRB = rcvr.KR(); // private {d,n}

        System.out.println("Sender KUA: {e:"+ KUA[0]+",n:"+KUA[1]+"} KRA: {d:"+KRA[0]+",n:"+KRA[1]+"}");
        System.out.println("Receiver KUB: {e:"+ KUB[0]+",n:"+KUB[1]+"} KRB: {d:"+KRB[0]+",n:"+KRB[1]+"}\n");

        //BigInteger M = BigInteger.valueOf(65);
        byte []M = "nada".getBytes();
        System.out.println("Message Before Decryption: "+new String(M)+"\n");

        ////////////////--------ENCRYPTION--------///////////////
        //(Message, key, n)
        RSAEncrypt encrypt = new RSAEncrypt();

        //Encrypt [1] msg with private key of sender "KRA"
        byte []c1 = encrypt.enc(M, KRA[0], KRA[1]);
        System.out.println("First Encryption: "+c1); //10 > 19

        //Encrypt [2] msg with public key of reciever "KUB"
        byte []c2 = encrypt.enc(c1, KUB[0], KUB[1]);
        System.out.println("Second Encryption: "+c2+"\n"); //19 > 24


        //////////////--------DECRYPTION--------///////////////
        //(Message, key, n)
        RSADecrypt decrypt = new RSADecrypt();

       // Decrypt [1] cipher with private key of reciever "KRB"
        byte []p1 = decrypt.dec(c2, KRB[0], KRB[1]);
        System.out.println("First Decryption: "+p1);

        //Decrypt [2] cipher with public key of sender "KUA"
        byte []p2 = decrypt.dec(p1, KUA[0], KUA[1]);
        System.out.println("Second Decryption: "+p2);

        String str="";
        for(int i: p2)
        {
            str += Character.toString((char)i);
        }
        System.out.println("Message After Decryption: "+str);

    }

}

