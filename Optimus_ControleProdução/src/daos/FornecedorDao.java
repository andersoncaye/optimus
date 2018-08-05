/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import apoio.Data;
import apoio.GemaMsg;
import apoio.GemaPostgresSql;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import negocio.Ferramenta;
import negocio.Fornecedor;
import persistencia.ConexaoBD;
import persistencia.IDAO_T;

/**
 *
 * @author XorNOTE
 */
public class FornecedorDao implements IDAO_T<Fornecedor> {

    ResultSet resultadoQ = null;

    @Override
    public String salvar(Fornecedor o) {
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            //Salvar cadastro
            String sql = null;
            String tabela = "fornecedor";
            String campos = "razaosocial";
            String valores = o.getRazaoSocial();

            if (!o.getNomeFantasia().trim().isEmpty()) {
                campos += ", nomefantasia";
                valores += ", " + o.getNomeFantasia();
            }

            if (!o.getCnpj().trim().isEmpty()) {
                campos += ", cnpj";
                valores += ", " + o.getCnpj();
            }

            if (!o.getIe().trim().isEmpty()) {
                campos += ", ie";
                valores += ", " + o.getIe();
            }

            if (!o.getEndereco().trim().isEmpty()) {
                campos += ", endereco";
                valores += ", " + o.getEndereco();
            }

            if (!o.getCidade().trim().isEmpty()) {
                campos += ", cidade";
                valores += ", " + o.getCidade();
            }

            if (!o.getEstado().trim().isEmpty()) {
                campos += ", estado";
                valores += ", " + o.getEstado();
            }

            if (o.getIdFornecedor() == -1) {
                sql = GemaPostgresSql.insert(tabela, campos.split(", "), valores.split(", "));
            } else {
                sql = GemaPostgresSql.update(o.getIdFornecedor(), "idfornecedor", tabela, campos.split(", "), valores.split(", "));
            }

            System.out.println("sql: " + sql);

            int resultado = st.executeUpdate(sql);

        } catch (Exception e) {
            System.out.println(GemaMsg.erroSalvar("Fornecedor") + "\n\n" + e);
            return e.toString();
        }
        return null;
    }

    @Override
    public String atualizar(Fornecedor o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String excluir(int id) {
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = null;
            String tabela = "fornecedor";
            String campos = "deleted";
            String valores = "true";

            sql = GemaPostgresSql.update(id, "idfornecedor", tabela, campos.split(", "), valores.split(", "));

            System.out.println("sql: " + sql);

            int resultado = st.executeUpdate(sql);

        } catch (Exception e) {
            System.out.println(GemaMsg.erroSalvar("Fornecedor") + "\n\n" + e);
            return e.toString();
        }
        return null;

    }

    @Override
    public ArrayList<Fornecedor> consultarTodos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Fornecedor> consultar(String criterio) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Fornecedor consultarId(int id) {
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql
                    = "SELECT * FROM fornecedor WHERE idfornecedor = " + id;

            System.out.println("sql: " + sql);

            resultadoQ = st.executeQuery(sql);

            if (resultadoQ.next()) {
                Fornecedor fornecedor = new Fornecedor();

                fornecedor.setIdFornecedor(id);
                System.out.println(id);

                fornecedor.setRazaoSocial(resultadoQ.getString("razaosocial"));
                System.out.println(resultadoQ.getString("razaosocial"));
                if (resultadoQ.getString("nomefantasia") != null) {
                    fornecedor.setNomeFantasia(resultadoQ.getString("nomefantasia"));
                }
                if (resultadoQ.getString("cnpj") != null) {
                    fornecedor.setCnpj(resultadoQ.getString("cnpj"));
                }
                if (resultadoQ.getString("ie") != null) {
                    fornecedor.setIe(resultadoQ.getString("ie"));
                }
                if (resultadoQ.getString("endereco") != null) {
                    fornecedor.setEndereco(resultadoQ.getString("endereco"));
                }
                if (resultadoQ.getString("cidade") != null) {
                    fornecedor.setCidade(resultadoQ.getString("cidade"));
                }
                if (resultadoQ.getString("estado") != null) {
                    fornecedor.setEstado(resultadoQ.getString("estado"));
                }
                fornecedor.setAtivo(resultadoQ.getBoolean("ativo"));
                fornecedor.setDeleted(resultadoQ.getBoolean("deleted"));

                return fornecedor;
            }

        } catch (Exception e) {
            System.out.println("Erro ao consultar fornecedor = " + e);
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
        cabecalho[1] = "Razao Social";
        cabecalho[2] = "Nome Fantasia";

        String sqlC = "SELECT count(*) FROM fornecedor WHERE razaosocial ILIKE '%" + criterio + "%'" + " AND deleted = 'false' ";
        String sql = "SELECT * FROM fornecedor WHERE razaosocial ILIKE '%" + criterio + "%'" + " AND deleted = 'false' ORDER BY idfornecedor";

        // cria matriz de acordo com nº de registros da tabela
        try {
            ResultSet resultadoQ = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(sqlC);

            resultadoQ.next();

            dadosTabela = new Object[resultadoQ.getInt(1)][3];

        } catch (Exception e) {
            System.out.println("Erro ao consultar fornecedor: " + e);
        }

        int lin = 0;

        // efetua consulta na tabela
        try {
            ResultSet resultadoQ = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(sql);

            while (resultadoQ.next()) {

                dadosTabela[lin][0] = resultadoQ.getInt("idfornecedor");
                dadosTabela[lin][1] = resultadoQ.getString("razaosocial");
                dadosTabela[lin][2] = resultadoQ.getString("nomefantasia");

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
                    column.setPreferredWidth(180);
                    break;
                case 2:
                    column.setPreferredWidth(100);
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
//        });
    }

}
