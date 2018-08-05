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
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import negocio.Usuario;
import persistencia.ConexaoBD;
import persistencia.IDAO_T;

/**
 *
 * @author XorNOTE
 */
public class UsuarioDao implements IDAO_T<Usuario> {

    ResultSet resultadoQ = null;

    @Override
    public String salvar(Usuario o) {
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();
            
            String sql = null;
            String tabela = "usuario";
            String campos = "nome, nick, ativo";
            String valores = o.getNome() + ", " + o.getNick() + ", " + o.isAtivo();

                        
            if (o.getIdusuario() == -1) {
                campos += ", pass";
                valores += ", " + o.getPass();
                sql = GemaPostgresSql.insert(tabela, campos.split(", "), valores.split(", "));
            } else {
                if( !this.consultarId(o.getIdusuario()).getPass().equals(o.getPass()) ){
                    campos += ", pass";
                    valores += ", " + o.getPass();
                }
                sql = GemaPostgresSql.update(o.getIdusuario(), "idusuario", tabela, campos.split(", "), valores.split(", "));
            }

            System.out.println("sql: " + sql);

            int resultado = st.executeUpdate(sql);

            return null;

        } catch (Exception e) {
            System.out.println("Erro salvar usuario = " + e);
            return e.toString();
        }
    }

    @Override
    public String atualizar(Usuario o) {
        return salvar(o);
    }

    @Override
    public String excluir(int id) {
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();
            
            String sql = null;
            String tabela = "usuario";
            String campos = "deleted";
            String valores = "true";

            sql = GemaPostgresSql.update(id, "idusuario", tabela, campos.split(", "), valores.split(", "));
            
            System.out.println("sql: " + sql);

            int resultado = st.executeUpdate(sql);

            return null;

        } catch (Exception e) {
            System.out.println("Erro salvar usuario = " + e);
            return e.toString();
        } 
    }

    @Override
    public ArrayList<Usuario> consultarTodos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Usuario> consultar(String criterio) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Usuario consultarId(int id) {
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "SELECT * FROM usuario "
                    + "WHERE idusuario = " + id;

            System.out.println("sql: " + sql);

            resultadoQ = st.executeQuery(sql);

            if (resultadoQ.next()) {
                Usuario usuario = new Usuario();

                usuario.setIdusuario(id);
                usuario.setNome(resultadoQ.getString("nome"));
                usuario.setNick(resultadoQ.getString("nick"));
                usuario.setPass(resultadoQ.getString("pass"));
                usuario.setAtivo(resultadoQ.getBoolean("ativo"));

                return usuario;
            }

        } catch (Exception e) {
            System.out.println("Erro atualizar usuario = " + e);
        }
        return null;
    }

    @Override
    public void popularTabela(JTable tabela, String criterio, int idUsuario) {
        // dados da tabela
        Object[][] dadosTabela = null;

        // cabecalho da tabela
        Object[] cabecalho = new Object[4];
        cabecalho[0] = "Código";
        cabecalho[1] = "Nome";
        cabecalho[2] = "Usuario";
        cabecalho[3] = "Situação";
        
        String sqlC = "SELECT count(*) FROM usuario WHERE NOME ILIKE '%" + criterio + "%'" + " AND deleted = 'false' " +  "AND idusuario != " + idUsuario;
        String sql = "SELECT * FROM usuario WHERE NOME ILIKE '%" + criterio + "%'" + " AND deleted = 'false' " +  "AND idusuario != " + idUsuario + "ORDER BY nome";
        
        // cria matriz de acordo com nº de registros da tabela
        try {
            ResultSet resultadoQ = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(sqlC);

            resultadoQ.next();

            dadosTabela = new Object[resultadoQ.getInt(1)][4];

        } catch (Exception e) {
            System.out.println("Erro ao consultar Usuario: " + e);
        }

        int lin = 0;

        // efetua consulta na tabela
        try {
            ResultSet resultadoQ = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(sql);

            while (resultadoQ.next()) {

                dadosTabela[lin][0] = resultadoQ.getInt("idusuario");
                dadosTabela[lin][1] = resultadoQ.getString("nome");
                dadosTabela[lin][2] = resultadoQ.getString("nick");
                if (resultadoQ.getString("ativo").equals("t")) {
                    dadosTabela[lin][3] = "Ativo";
                } else {
                    dadosTabela[lin][3] = "Inativo";
                }

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
                    column.setPreferredWidth(140);
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

    @Override
    public void popularTabelaPesquisarSimples(JTable tabela, String criterio) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
