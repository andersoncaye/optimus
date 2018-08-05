/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import apoio.Data;
import apoio.Gema;
import apoio.GemaPostgresSql;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JTable;
import negocio.Fornecedor;
import negocio.ItemPDL;
import persistencia.ConexaoBD;
import persistencia.IDAO_T;

/**
 *
 * @author anderson.caye
 */
public class ItemPDLDao implements IDAO_T<ItemPDL> {
    
    ResultSet resultadoQ = null;

    @Override
    public String salvar(ItemPDL o) {
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            //Salvar cadastro
            String sql = null;
            String tabela = "item_producao";
            String campos = "dta, idfichaproducao, idfuncionario, idprodutomaterial, quantidade";
            String valores = o.getData().obterData() + ", " + o.getIdFicha() + ", " + o.getFuncionario().getIdFuncionario() + ", " + o.getProduto().getIdproduto() + ", " + o.getQuantidade();
            
            if(o.getIdItem() == -1){
                sql = GemaPostgresSql.insert(tabela, campos.split(", "), valores.split(", "));
            } else {
                sql = GemaPostgresSql.update(o.getIdItem(), "iditemproducao", tabela, campos.split(", "), valores.split(", "));
            }

            System.out.println("sql: " + sql);

            int resultado = st.executeUpdate(sql);

        } catch (Exception e) {
            System.out.println("Erro ao salvar os itens da ficha.\n\n" + e);
            return e.toString();
        }
        return null;
    }

    @Override
    public String atualizar(ItemPDL o) {
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            //Salvar cadastro
            String sql = null;
            String tabela = "item_producao";
            String campos = "dta, idfichaproducao, idfuncionario, idprodutomaterial, quantidade";
            String valores = o.getData().obterData() + ", " + o.getIdFicha() + ", " + o.getFuncionario().getIdFuncionario() + ", " + o.getProduto().getIdproduto() + ", " + o.getQuantidade();
            
            sql = GemaPostgresSql.update(o.getIdItem(), "iditemproducao", tabela, campos.split(", "), valores.split(", "));

            System.out.println("sql: " + sql);

            int resultado = st.executeUpdate(sql);

        } catch (Exception e) {
            System.out.println("Erro ao salvar os itens da ficha.\n\n" + e);
            return e.toString();
        }
        return null;
    }

    @Override
    public String excluir(int id) {
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            //Excluir cadastro
            String sql = null;
            String tabela = "item_producao";
            String join = "iditemproducao = "+id;
            sql = GemaPostgresSql.delete(tabela,join);

            System.out.println("sql: " + sql);

            int resultado = st.executeUpdate(sql);

        } catch (Exception e) {
            System.out.println("Erro ao deletar os itens da ficha.\n\n" + e);
            return e.toString();
        }
        return null;
    }

    @Override
    public ArrayList<ItemPDL> consultarTodos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<ItemPDL> consultar(String criterio) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ItemPDL consultarId(int id) {
        return null;
    }
    
    public ArrayList<ItemPDL> consultarIdFicha(int id) {
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = 
                    "SELECT * FROM item_producao WHERE idfichaproducao = " + id;

            System.out.println("sql: " + sql);

            resultadoQ = st.executeQuery(sql);
            ArrayList<ItemPDL> itens = new ArrayList();
            ItemPDL itemPdl;
            while (resultadoQ.next()) {
                itemPdl = new ItemPDL();
                
                itemPdl.setIdItem( resultadoQ.getInt("iditemproducao") );
                itemPdl.setData( new Data( Gema.inverteDataToDDMMYYYY( resultadoQ.getString("dta"), "-" ) ) );
                itemPdl.setIdFicha( resultadoQ.getInt("idfichaproducao") );
                itemPdl.setFuncionario( new FuncionarioDao().consultarId( resultadoQ.getInt("idfuncionario") ) );
                itemPdl.setProduto( new ProdutoMaterialDao().consultarId( resultadoQ.getInt("idprodutomaterial") ));
                itemPdl.setQuantidade( resultadoQ.getDouble("quantidade") );
                System.out.println(itemPdl.getQuantidade() + " - Valor Quantidade");
                
                
                itens.add(itemPdl);
            }
            
            return itens;

        } catch (Exception e) {
            System.out.println("Erro ao consultar ferramenta = " + e);
        }
        return null;
    }

    @Override
    public void popularTabela(JTable tabela, String criterio, int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void popularTabelaPesquisarSimples(JTable tabela, String criterio) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    

}
