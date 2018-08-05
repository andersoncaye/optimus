/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apoio;

/**
 *
 * @author XorNOTE
 */
public class GemaMsg {
    public static String campoVazio(){
        return "Preencha todos os campos obrigatórios.\n\nSinalizados em amarelo.";
    }
    
    public static String erroSalvar (String onde){
        return "Erro ao salvar: " +  onde;
    }
    
    public static String salvo(String oQue){
        return oQue + " salvo com sucesso.";
    }
    
    public static String erroDeletado (String onde){
        return "Erro ao deletar: " +  onde;
    }
    
    public static String deletado(String oQue){
        return oQue + " deletado com sucesso.";
    }
}
