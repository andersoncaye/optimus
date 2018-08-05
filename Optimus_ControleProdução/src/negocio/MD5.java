package negocio;
import java.security.*;
import java.math.*;

public class MD5 {

    public static void main(String args[]) throws Exception {
        
        System.out.println(criptoSenha(""));
        System.out.println(criptoSenha(" "));
        System.out.println(criptoSenha("  "));
        System.out.println(criptoSenha("Anderson"));
        System.out.println(criptoSenha("anderson"));
        System.out.println(criptoSenha("andersoN"));
        System.out.println(criptoSenha("Anderson"));
        
    }

    public static String criptoSenha(String s) throws Exception {
        MessageDigest m = MessageDigest.getInstance("MD5");
        m.update(s.getBytes(), 0, s.length());
        return (new BigInteger(1, m.digest()).toString(16));
    }
}

//minha senha criptografada.
