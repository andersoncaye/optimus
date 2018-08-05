/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import apoio.Data;
import apoio.Gema;
import apoio.GemaMsg;
import apoio.GemaPostgresSql;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import negocio.Funcionario;
import persistencia.ConexaoBD;
import persistencia.IDAO_T;

/**
 *
 * @author anderson.caye
 */
public class FuncionarioDao implements IDAO_T<Funcionario> {

    ResultSet resultadoQ = null;
    
    @Override
    public String salvar(Funcionario o) {
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            //Salvar cadastro
            String sql = null;
            String tabela = "funcionario";
            String campos = "nome, data_ingresso";
            String valores = o.getNome() + ", " + o.getDataIngresso().obterData();
            
            if (!o.getApelido().trim().isEmpty()) {
                campos += ", apelido";
                valores += ", " + o.getApelido();
            }

            if (!o.getCpf().trim().isEmpty() && o.getCpf() != null) {
                campos += ", cpf";
                valores += ", " + o.getCpf();
            }
            
            if (!o.getRg().trim().isEmpty()) {
                campos += ", rg";
                valores += ", " + o.getRg();
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
            
            if (!o.getFuncao().trim().isEmpty()) {
                campos += ", funcao";
                valores += ", " + o.getFuncao();
            }
            
            if (o.getSalario() > 0) {
                campos += ", salario";
                valores += ", " + o.getSalario();
            }
            
            if (!o.getDataAdmissao().isEmpty()) {
                campos += ", data_admissao";
                valores += ", " + o.getDataAdmissao().obterData();
            }
            
            if (!o.getDataDemissao().isEmpty()) {
                campos += ", data_demissao";
                valores += ", " + o.getDataDemissao().obterData();
            }
            
            if (o.getIdFuncionario() == -1) {
                sql = GemaPostgresSql.insert(tabela, campos.split(", "), valores.split(", "));
            } else {
                sql = GemaPostgresSql.update(o.getIdFuncionario(), "idfuncionario", tabela, campos.split(", "), valores.split(", "));
            }

            System.out.println("sql: " + sql);

            int resultado = st.executeUpdate(sql);

        } catch (Exception e) {
            System.out.println(GemaMsg.erroSalvar("Funcionário") + "\n\n" + e);
            return e.toString();
        }
        return null;
    }

    @Override
    public String atualizar(Funcionario o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String excluir(int id) {
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            //Salvar cadastro
            String sql = null;
            String tabela = "funcionario";
            String campos = "deleted";
            String valores = "true";

            sql = GemaPostgresSql.update(id, "idfuncionario", tabela, campos.split(", "), valores.split(", "));

            System.out.println("sql: " + sql);

            int resultado = st.executeUpdate(sql);

        } catch (Exception e) {
            System.out.println(GemaMsg.erroSalvar("Funcionário") + "\n\n" + e);
            return e.toString();
        }
        return null;
    }

    @Override
    public ArrayList<Funcionario> consultarTodos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Funcionario> consultar(String criterio) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Funcionario consultarId(int id) {
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = 
                    "SELECT * FROM funcionario WHERE idfuncionario = " + id;

            System.out.println("sql: " + sql);

            resultadoQ = st.executeQuery(sql);

            if (resultadoQ.next()) {
                Funcionario funcionario = new Funcionario();
                
                funcionario.setIdFuncionario(id);
                funcionario.setNome( resultadoQ.getString("nome") );
                funcionario.setApelido( resultadoQ.getString("apelido") );
                funcionario.setCpf( resultadoQ.getString("cpf") );
                funcionario.setRg( resultadoQ.getString("rg") );
                funcionario.setEndereco( resultadoQ.getString("endereco") );
                funcionario.setCidade( resultadoQ.getString("cidade") );
                funcionario.setEstado( resultadoQ.getString("estado") );
                if ( resultadoQ.getString("data_ingresso") != null ){
                    funcionario.setDataIngresso( new Data( Gema.inverteDataToDDMMYYYY( resultadoQ.getString("data_ingresso"), "-" ) ) );
                } else {
                    funcionario.setDataIngresso( new Data(0) );
                }
                
                if ( resultadoQ.getString("data_admissao") != null ){
                    funcionario.setDataAdmissao( new Data( Gema.inverteDataToDDMMYYYY( resultadoQ.getString("data_admissao"), "-" ) ) );
                } else {
                    funcionario.setDataAdmissao( new Data(0) );
                }
                
                if ( resultadoQ.getString("data_demissao") != null ){
                    funcionario.setDataDemissao( new Data( Gema.inverteDataToDDMMYYYY( resultadoQ.getString("data_admissao"), "-" ) ) );
                } else {
                    funcionario.setDataDemissao( new Data(0) );
                }
                
                funcionario.setSalario( resultadoQ.getDouble("salario") );
                funcionario.setFuncao( resultadoQ.getString("funcao") );
                funcionario.setAtivo( resultadoQ.getBoolean("ativo") );
                funcionario.setDeleted( resultadoQ.getBoolean("deleted") );
                

                return funcionario;
            }

        } catch (Exception e) {
            System.out.println("Erro ao consultar funcionario = " + e);
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
        cabecalho[1] = "Nome";
        cabecalho[2] = "Função";

        String sqlC = "SELECT count(*) FROM funcionario WHERE nome ILIKE '%" + criterio + "%'" + " AND deleted = 'false' ";
        String sql = "SELECT * FROM funcionario WHERE nome ILIKE '%" + criterio + "%'" + " AND deleted = 'false' ";

        // cria matriz de acordo com nº de registros da tabela
        try {
            ResultSet resultadoQ = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(sqlC);

            resultadoQ.next();

            dadosTabela = new Object[resultadoQ.getInt(1)][3];

        } catch (Exception e) {
            System.out.println("Erro ao consultar Funcionario: " + e);
        }

        int lin = 0;

        // efetua consulta na tabela
        try {
            ResultSet resultadoQ = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(sql);

            while (resultadoQ.next()) {

                dadosTabela[lin][0] = resultadoQ.getInt("idfuncionario");
                dadosTabela[lin][1] = resultadoQ.getString("nome");
                dadosTabela[lin][2] = resultadoQ.getString("funcao");

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
//        });
    }

   
    
}
