/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apoio;

import apresentacao.IjdPesquisaSimples;
import java.math.BigInteger;
import java.security.MessageDigest;
import persistencia.IDAO_T;

/**
 *
 * @author XorNOTE
 */
public class Gema {
    
    /**
     * 
     * @param trecho
     * @param tamanho
     * @return 
     */
    public static boolean vazio (String trecho, int tamanho){
        boolean result = false;
        
        if(trecho.trim().length() >= tamanho){
            result = true;
        }
        
        return result;
    }
    
    public static String inverteDataToDDMMYYYY (String data, String regex){
        String []dta = data.split(regex);
        return dta[2] +"/"+ dta[1] +"/"+ dta[0];
    }
    
    public static String inverteDataToDDMMYYYY_isNull (String data, String regex){
        String r = null;
        if(data != null){
            r = inverteDataToDDMMYYYY(data, regex);
        }
        return r;
    }
    
    public static String criptoSenha(String s) throws Exception {
        MessageDigest m = MessageDigest.getInstance("MD5");
        m.update(s.getBytes(), 0, s.length());
        return (new BigInteger(1, m.digest()).toString(16));
    }
    
}
