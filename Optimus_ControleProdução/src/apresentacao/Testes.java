/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apresentacao;

import daos.UsuarioDao;
import negocio.Usuario;

/**
 *
 * @author XorNOTE
 */
public class Testes {
    public static void main(String[] args) {
        Usuario u = new Usuario();
        
        u.setNick("alan.caye");
        u.setPass("al");
        
        UsuarioDao us = new UsuarioDao();
        us.salvar(u);
//        u = us.consultarId(1);
        
        System.out.println(u);
    }
    
}
