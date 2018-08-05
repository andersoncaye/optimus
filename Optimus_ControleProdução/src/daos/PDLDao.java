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
import java.text.DecimalFormat;
import negocio.PDL;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import negocio.Ferramenta;
import negocio.Funcionario;
import negocio.ItemPDL;
import persistencia.ConexaoBD;
import persistencia.IDAO_T;

/**
 *
 * @author anderson.caye
 */
public class PDLDao implements IDAO_T<PDL> {

    ResultSet resultadoQ = null;

    @Override
    public String salvar(PDL o) {
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();
        
            //Salvar cadastro
            String sql = null;
            String tabela = "ficha_producao";
            String campos = "idferramenta, idfuncionario";
            String valores = o.getFerramenta().getIdferramenta() + ", " + o.getFuncionario().getIdFuncionario();
            String dataAbertura;
            String dataFechamento;

            if (o.getDataRetiradaIsEmpty()) {
                dataAbertura = null;
            } else {
                dataAbertura = o.getDataRetirada();
                campos += ", dataretirada";
                valores += ", " + dataAbertura;
            }

            if (o.getDataDevolucaoIsEmpty()) {
                dataFechamento = null;
            } else {
                dataFechamento = o.getDataDevolucao();
                campos += ", datadevolucao, motivodevolucao";
                valores += ", " + dataFechamento + ", " + o.getMotivoDevolucao();
            }

            if (o.getIdFicha() == -1) {
                sql = GemaPostgresSql.insert(tabela, campos.split(", "), valores.split(", "));
            } else {
                sql = GemaPostgresSql.update(o.getIdFicha(), "idfichaproducao", tabela, campos.split(", "), valores.split(", "));
            }

            System.out.println("sql: " + sql);
            int newIdFicha = 0;
            int resultado = st.executeUpdate(sql);
            resultadoQ = st.executeQuery("SELECT MAX(idfichaproducao) AS idfichaproducao FROM " + tabela);
            if (resultadoQ.next()) {
                newIdFicha = Integer.parseInt(resultadoQ.getString("idfichaproducao"));
            }

            for (int i = 0; i < o.getItens().size(); i++) {
                if (o.getItens().get(i).getIdFicha() == -1) {
                    o.getItens().get(i).setIdFicha(newIdFicha);
                }
                new ItemPDLDao().salvar(o.getItens().get(i));
            }
            
            //Itens a remover
            if(o.getRemoverItens().size() > 0){
                for(int i = 0; i < o.getRemoverItens().size(); i++){
                    new ItemPDLDao().excluir(o.getRemoverItens().get(i));
                }
            }

        } catch (Exception e) {
            System.out.println("Erro ao salvar a ficha.\n\n" + e);
            return e.toString();
        }
        return null;

    }

    @Override
    public String atualizar(PDL o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String excluir(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<PDL> consultarTodos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<PDL> consultar(String criterio) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PDL consultarId(int id) {
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql
                    = "SELECT * FROM ficha_producao WHERE idfichaproducao = " + id;

            System.out.println("sql: " + sql);

            resultadoQ = st.executeQuery(sql);

            if (resultadoQ.next()) {
                PDL pdl = new PDL();

                pdl.setIdFicha(id);

                if (resultadoQ.getString("dataretirada") != null) {
                    pdl.setDataRetirada(new Data(Gema.inverteDataToDDMMYYYY(resultadoQ.getString("dataretirada"), "-")));
                    System.out.println(pdl.getDataRetirada());
                } else {
                    pdl.setDataRetirada(new Data(0));
                }

                if (resultadoQ.getString("datadevolucao") != null) {
                    pdl.setDataDevolucao(new Data(Gema.inverteDataToDDMMYYYY(resultadoQ.getString("datadevolucao"), "-")));
                } else {
                    pdl.setDataDevolucao(new Data(0));
                }

                pdl.setMotivoDevolucao(resultadoQ.getString("motivodevolucao"));

                pdl.setFuncionario(new FuncionarioDao().consultarId(resultadoQ.getInt("idfuncionario")));

                pdl.setFerramenta(new FerramentaDao().consultarId(resultadoQ.getInt("idferramenta")));

                pdl.setItens(new ItemPDLDao().consultarIdFicha(id));

                return pdl;
            }

        } catch (Exception e) {
            System.out.println("Erro ao consultar lançamento = " + e);
        }
        return null;
    }

    /**
     *
     * @param tabela
     * @param criterio - composto pelo (critério, dtaInicial, dtaFinal).
     * @param id
     */
    @Override
    public void popularTabela(JTable tabela, String criterio, int id) {
        if (criterio.length() == 21) {
            String[] data = criterio.split(",");
            Data dt = new Data(data[0]);
            int rs = dt.compareTo(new Data(data[1]));

            if (rs == -1) { 
                criterio = "dataretirada >= '" + data[0] + "' AND dataretirada <= '" + data[1] + "' AND ";
            } else if (rs == 1) { 
                criterio = "dataretirada >= '" + data[1] + "' AND dataretirada <= '" + data[0] + "' AND ";
            } else { //datas iguais
                criterio = "dataretirada = '" + data[0] + "' AND ";
            }
        }

        // dados da tabela
        Object[][] dadosTabela = null;

        // cabecalho da tabela
        Object[] cabecalho = new Object[5];
        cabecalho[0] = "Nr Ficha";
        cabecalho[1] = "Ferramenta";
        cabecalho[2] = "Data Retirada";
        cabecalho[3] = "Data Devolução";
        cabecalho[4] = "Motivo Devolução";

        String sqlC = "SELECT count(*) FROM ficha_producao WHERE " + criterio + "ativo = 'true'";
        String sql = "SELECT * FROM ficha_producao WHERE " + criterio + "ativo = 'true' ORDER BY idfichaproducao";

        System.out.println(sql);
        
        // cria matriz de acordo com nº de registros da tabela
        try {
            ResultSet resultadoQ = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(sqlC);

            resultadoQ.next();

            dadosTabela = new Object[resultadoQ.getInt(1)][5];

        } catch (Exception e) {
            System.out.println("Erro ao consultar fichas: " + e);
        }

        int lin = 0;

        // efetua consulta na tabela
        try {
            ResultSet resultadoQ = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(sql);

            while (resultadoQ.next()) {

                dadosTabela[lin][0] = resultadoQ.getInt("idfichaproducao");
                Ferramenta f = new FerramentaDao().consultarId(resultadoQ.getInt("idferramenta"));
                dadosTabela[lin][1] = "[" + f.getCodigo() + "] " + f.getDescricao();
                dadosTabela[lin][2] = Gema.inverteDataToDDMMYYYY_isNull(resultadoQ.getString("dataretirada"), "-");
                dadosTabela[lin][3] = Gema.inverteDataToDDMMYYYY_isNull(resultadoQ.getString("datadevolucao"), "-");
                dadosTabela[lin][4] = resultadoQ.getString("motivodevolucao");

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
                    column.setPreferredWidth(50);
                    break;
                case 1:
                    column.setPreferredWidth(400);
                    break;
                case 2:
                    column.setPreferredWidth(90);
                    break;
                case 3:
                    column.setPreferredWidth(90);
                    break;
                case 4:
                    column.setPreferredWidth(102);
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

    public void popularTabela(JTable tabela, ArrayList<ItemPDL> itens) {
        // dados da tabela
        Object[][] dadosTabela = null;

        // cabecalho da tabela
        Object[] cabecalho = new Object[7];
        cabecalho[0] = "#";
        cabecalho[1] = "Id";
        cabecalho[2] = "Data";
        cabecalho[3] = "Funcionario";
        cabecalho[4] = "Produto";
        cabecalho[5] = "Und";
        cabecalho[6] = "Qtde";

//        String sqlC = "SELECT count(*) FROM usuario WHERE NOME ILIKE '%" + criterio + "%'" + " AND deleted = 'false' " +  "AND idusuario != " + idUsuario;
//        String sql = "SELECT * FROM usuario WHERE NOME ILIKE '%" + criterio + "%'" + " AND deleted = 'false' " +  "AND idusuario != " + idUsuario + "ORDER BY nome";
        // cria matriz de acordo com nº de registros da tabela
//        try {
//            ResultSet resultadoQ = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(sqlC);
//
//            resultadoQ.next();
//
//            dadosTabela = new Object[resultadoQ.getInt(1)][4];
//
//        } catch (Exception e) {
//            System.out.println("Erro ao consultar Usuario: " + e);
//        }
//        int lin = 0;
        // efetua consulta na tabela
//        try {
//            ResultSet resultadoQ = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(sql);
//
//            while (resultadoQ.next()) {
//        DecimalFormat ft = new DecimalFormat("###,000");
        dadosTabela = new Object[itens.size()][7];
        for (int i = 0; i < itens.size(); i++) {

            dadosTabela[i][0] = i + 1;
            dadosTabela[i][1] = itens.get(i).getIdItem();
            dadosTabela[i][2] = itens.get(i).getData().obterData();
            dadosTabela[i][3] = itens.get(i).getFuncionario().getNome();
            dadosTabela[i][4] = itens.get(i).getProduto().getCodigo() + " - " + itens.get(i).getProduto().getDescricao();
            dadosTabela[i][5] = itens.get(i).getProduto().getUnidade();
            dadosTabela[i][6] = /*Double.valueOf(ft.format(*/ itens.get(i).getQuantidade()/*))*/;

            //System.out.println(dadosTabela[lin][1].toString() + dadosTabela[lin][0] + dadosTabela[lin][2] + dadosTabela[lin][3] + "");
            // caso a coluna precise exibir uma imagem
//                if (resultadoQ.getBoolean("Situacao")) {
//                    dadosTabela[lin][2] = new ImageIcon(getClass().getClassLoader().getResource("Interface/imagens/status_ativo.png"));
//                } else {
//                    dadosTabela[lin][2] = new ImageIcon(getClass().getClassLoader().getResource("Interface/imagens/status_inativo.png"));
//                }
//            lin++;
        }
//    }
//    catch (Exception e) {
//            System.out.println("problemas para popular tabela...");
//        System.out.println(e);
//    }

        // configuracoes adicionais no componente tabela
        tabela.setModel(
                new DefaultTableModel(dadosTabela, cabecalho) {
            @Override
            // quando retorno for FALSE, a tabela nao é editavel
            public boolean isCellEditable(int row, int column
            ) {
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
            public Class getColumnClass(int column
            ) {

                if (column == 2) {
//                    return ImageIcon.class;
                }
                return Object.class;
            }
        }
        );

        // permite seleção de apenas uma linha da tabela
        tabela.setSelectionMode(
                0);

        // redimensiona as colunas de uma tabela
        TableColumn column = null;
        for (int i = 0;
                i < tabela.getColumnCount();
                i++) {
            column = tabela.getColumnModel().getColumn(i);
            switch (i) {
                case 0:
                    column.setPreferredWidth(17);
                    break;
                case 1:
                    column.setPreferredWidth(17);
                    break;
                case 2:
                    column.setPreferredWidth(80);
                    break;
                case 3:
                    column.setPreferredWidth(180);
                    break;
                case 4:
                    column.setPreferredWidth(250);
                    break;
                case 5:
                    column.setPreferredWidth(25);
                    break;
                case 6:
                    column.setPreferredWidth(80);
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
