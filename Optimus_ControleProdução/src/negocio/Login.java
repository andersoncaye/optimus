/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import daos.LoginDao;

/**
 *
 * @author XorNOTE
 */
public class Login {
    private String senha;
    private String usuario;

    public Login() {

    }

    
    private String verificarLogin(){
        LoginDao dao = new LoginDao();
        
        return dao.consulta(this);
    }
    
    private int verificarLoginId(){
        LoginDao dao = new LoginDao();
        
        return Integer.parseInt(dao.consultaId(this));
    }

    public String getSenha() {
        return senha;
    }

    public String getUsuario() {
        return usuario;
    }
 
    public String logar(String senha, String usuario){
        this.senha = senha;
        this.usuario = usuario;
        return verificarLogin();
    }
    
    public int logarId(String senha, String usuario){
        this.senha = senha;
        this.usuario = usuario;
        return verificarLoginId();
    }
}
