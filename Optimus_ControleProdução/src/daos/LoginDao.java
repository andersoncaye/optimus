/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import apoio.GemaPostgresSql;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JTable;
import negocio.Login;
import negocio.Usuario;
import persistencia.ConexaoBD;
import persistencia.IDAO_T;

/**
 *
 * @author XorNOTE
 */
public class LoginDao {

    public String consulta(Login login) {
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = null;
            String tabela = "usuario";
            String campos = "nick, pass, ativo, deleted";
            String[] campo = campos.split(", ");
            String valores = login.getUsuario() + ", " + login.getSenha()+ ", true" + ", false";
            String[] valor = valores.split(", ");

            sql = "SELECT * FROM " + tabela + " WHERE "
                    + campo[0] + " = '" + valor[0] + "' AND "
                    + campo[1] + " = '" + valor[1] + "' AND "
                    + campo[2] + " = '" + valor[2] + "' AND "
                    + campo[3] + " = '" + valor[3] + "'"
                    ;
            
            ResultSet rs = null;
            rs = st.executeQuery(sql);

            if(rs.next()){
                return null;
            } else {
                return "false";
            }
        } catch (Exception e) {
            System.out.println("Erro salvar usuario = " + e);
            return e.toString();
        }
    }

    public String consultaId(Login login) {
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = null;
            String tabela = "usuario";
            String campos = "nick, pass, ativo, deleted";
            String[] campo = campos.split(", ");
            String valores = login.getUsuario() + ", " + login.getSenha()+ ", true" + ", false";
            String[] valor = valores.split(", ");

            sql = "SELECT * FROM " + tabela + " WHERE "
                    + campo[0] + " = '" + valor[0] + "' AND "
                    + campo[1] + " = '" + valor[1] + "' AND "
                    + campo[2] + " = '" + valor[2] + "' AND "
                    + campo[3] + " = '" + valor[3] + "'"
                    ;
            
            ResultSet rs = null;
            rs = st.executeQuery(sql);

            if(rs.next()){
                return rs.getString("idusuario");
            } else {
                return null;
            }
        } catch (Exception e) {
            System.out.println("Erro salvar usuario = " + e);
            return e.toString();
        }
    }
}
