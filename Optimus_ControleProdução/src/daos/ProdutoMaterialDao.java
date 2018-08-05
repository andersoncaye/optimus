/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import apoio.GemaMsg;
import apoio.GemaPostgresSql;
import java.sql.ResultSet;
import java.sql.Statement;
import negocio.ProdutoMaterial;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import persistencia.ConexaoBD;
import persistencia.IDAO_T;

/**
 *
 * @author anderson.caye
 */
public class ProdutoMaterialDao implements IDAO_T<ProdutoMaterial> {
    
    ResultSet resultadoQ = null;
    
    @Override
    public String salvar(ProdutoMaterial o) {
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            //Salvar cadastro
            String sql = null;
            String tabela = "produto_material";
            String campos = "codigo, descricao, unidade";
            String valores = o.getCodigo() + ", " + o.getDescricao() + ", " + o.getUnidade();

            if (o.getIdproduto() == -1) {
                sql = GemaPostgresSql.insert(tabela, campos.split(", "), valores.split(", "));
            } else {
                sql = GemaPostgresSql.update(o.getIdproduto(), "idprodutomaterial", tabela, campos.split(", "), valores.split(", "));
            }

            System.out.println("sql: " + sql);

            int resultado = st.executeUpdate(sql);

        } catch (Exception e) {
            System.out.println(GemaMsg.erroSalvar("Produto") + "\n\n" + e);
            return e.toString();
        }
        return null;
    }

    @Override
    public String atualizar(ProdutoMaterial o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String excluir(int id) {
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            //Deletar cadastro
            String sql = null;
            String tabela = "produto_material";
            String campos = "deleted";
            String valores = "true";

            sql = GemaPostgresSql.update(id, "idprodutomaterial", tabela, campos.split(", "), valores.split(", "));

            System.out.println("sql: " + sql);

            int resultado = st.executeUpdate(sql);

        } catch (Exception e) {
            System.out.println(GemaMsg.erroDeletado("Produto") + "\n\n" + e);
            return e.toString();
        }
        return null;
    }

    @Override
    public ArrayList<ProdutoMaterial> consultarTodos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<ProdutoMaterial> consultar(String criterio) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ProdutoMaterial consultarId(int id) {
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = 
                    "SELECT * FROM produto_material WHERE idprodutomaterial = " + id;

            System.out.println("sql: " + sql);

            resultadoQ = st.executeQuery(sql);

            if (resultadoQ.next()) {
                ProdutoMaterial produto = new ProdutoMaterial();
                produto.setIdproduto(id);
                produto.setCodigo(resultadoQ.getString("codigo"));
                produto.setDescricao(resultadoQ.getString("descricao"));
                produto.setUnidade(resultadoQ.getString("unidade"));
                produto.setAtivo(resultadoQ.getBoolean("ativo"));
                produto.setDeleted(resultadoQ.getBoolean("deleted"));
                
                return produto;
            }

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
// dados da tabela
        Object[][] dadosTabela = null;

        // cabecalho da tabela
        Object[] cabecalho = new Object[3];
        cabecalho[0] = "Id";
        cabecalho[1] = "Codigo";
        cabecalho[2] = "Descrição";

        String sqlC = "SELECT count(*) FROM produto_material WHERE descricao ILIKE '%" + criterio + "%'" + " AND deleted = 'false' ";
        String sql = "SELECT * FROM produto_material WHERE descricao ILIKE '%" + criterio + "%'" + " AND deleted = 'false' ";

        // cria matriz de acordo com nº de registros da tabela
        try {
            ResultSet resultadoQ = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(sqlC);

            resultadoQ.next();

            dadosTabela = new Object[resultadoQ.getInt(1)][3];

        } catch (Exception e) {
            System.out.println("Erro ao consultar Produto: " + e);
        }

        int lin = 0;

        // efetua consulta na tabela
        try {
            ResultSet resultadoQ = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(sql);

            while (resultadoQ.next()) {

                dadosTabela[lin][0] = resultadoQ.getInt("idprodutomaterial");
                dadosTabela[lin][1] = resultadoQ.getString("codigo");
                dadosTabela[lin][2] = resultadoQ.getString("descricao");

                //System.out.println(dadosTabela[lin][1].toString() + dadosTabela[lin][0] + dadosTabela[lin][2] + dadosTabela[lin][3] + "");
                // caso a coluna precise exibir uma imagem
//                if (resultadoQ.getBoolean("Situacao")) {
//                    dadosTabela[lin][2] = new ImageIcon(getClass().getClassLoader().getResource("Interface/imagens/status_ativo.png"));
//                } else {
//                    dadosTabela[lin][2] = new ImageIcon(getClass().getClassLoader().getResource("Interface/imagens/status_inativo.png"));
//                }
                lin++;
            }
        } catch (Exception e) {
            System.out.println("problemas para popular tabela...");
            System.out.println(e);
        }

        // configuracoes adicionais no componente tabela
        tabela.setModel(new DefaultTableModel(dadosTabela, cabecalho) {
            @Override
            // quando retorno for FALSE, a tabela nao é editavel
            public boolean isCellEditable(int row, int column) {
                return false;
                /*  
                 if (column == 3) {  // apenas a coluna 3 sera editavel
                 return true;
                 } else {
                 return false;
                 }
                 */
            }

            // alteracao no metodo que determina a coluna em que o objeto ImageIcon devera aparecer
            @Override
            public Class getColumnClass(int column) {

                if (column == 2) {
//                    return ImageIcon.class;
                }
                return Object.class;
            }
        });

        // permite seleção de apenas uma linha da tabela
        tabela.setSelectionMode(0);

        // redimensiona as colunas de uma tabela
        TableColumn column = null;
        for (int i = 0; i < tabela.getColumnCount(); i++) {
            column = tabela.getColumnModel().getColumn(i);
            switch (i) {
                case 0:
                    column.setPreferredWidth(17);
                    break;
                case 1:
                    column.setPreferredWidth(30);
                    break;
                case 2:
                    column.setPreferredWidth(250);
                    break;
//                case 2:
//                    column.setPreferredWidth(14);
//                    break;
            }
        }
        // renderizacao das linhas da tabela = mudar a cor
//        jTable1.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
//
//            @Override
//            public Component getTableCellRendererComponent(JTable table, Object value,
//                    boolean isSelected, boolean hasFocus, int row, int column) {
//                super.getTableCellRendererComponent(table, value, isSelected,
//                        hasFocus, row, column);
//                if (row % 2 == 0) {
//                    setBackground(Color.GREEN);
//                } else {
//                    setBackground(Color.LIGHT_GRAY);
//                }
//                return this;
//            }
//        });    }
    }
}
