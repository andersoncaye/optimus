/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apresentacao;

import daos.PDLDao;
import daos.UsuarioDao;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import negocio.PDL;
import negocio.Usuario;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;
import persistencia.ConexaoBD;


/**
 *
 * @author XorNOTE
 */
public class JfPrincipal extends javax.swing.JFrame {

    /**
     * Creates new form JfPrincipal
     */
    
    private String pass;
    private String nick;
    private int idUsuarioLogin;
    private Usuario user;
    
    public JfPrincipal(int idUsuario) {
        this.setExtendedState(MAXIMIZED_BOTH);
        initComponents();
        idUsuarioLogin = idUsuario;
        UsuarioDao uDao = new UsuarioDao();
        user = uDao.consultarId(idUsuarioLogin);
        boolean login = false;

//        while(!login){
//
//        }
        
        
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        desktopPrincipal = new javax.swing.JDesktopPane();
        jMenuBar_Principal = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem13 = new javax.swing.JMenuItem();
        jSeparator5 = new javax.swing.JPopupMenu.Separator();
        jMenuItem12 = new javax.swing.JMenuItem();
        JmCadastro = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        btnFuncionario = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        btnProduto = new javax.swing.JMenuItem();
        btnFerramenta = new javax.swing.JMenuItem();
        jSeparator4 = new javax.swing.JPopupMenu.Separator();
        JmLancamentos = new javax.swing.JMenu();
        lancarPDL = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        menuConsultarLancamento = new javax.swing.JMenuItem();
        JmRelatorios = new javax.swing.JMenu();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        jMenu2 = new javax.swing.JMenu();
        btnlancamentoCadastro = new javax.swing.JMenuItem();
        menuRalatorioLancamentoListarLancamento = new javax.swing.JMenuItem();
        menuRelatorioLancamentoEntreDatas = new javax.swing.JMenuItem();
        menuRelatorioLancamentoPorFerramenta = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        btnListarProduto = new javax.swing.JMenuItem();
        jMenu6 = new javax.swing.JMenu();
        btnListarFornecedor = new javax.swing.JMenuItem();
        jMenu7 = new javax.swing.JMenu();
        btnListarFerramenta = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        btnListarFuncionario = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        menuRelatorioUsuarioListarUsuario = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Optimus - Controle de Produção");

        javax.swing.GroupLayout desktopPrincipalLayout = new javax.swing.GroupLayout(desktopPrincipal);
        desktopPrincipal.setLayout(desktopPrincipalLayout);
        desktopPrincipalLayout.setHorizontalGroup(
            desktopPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        desktopPrincipalLayout.setVerticalGroup(
            desktopPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 281, Short.MAX_VALUE)
        );

        jMenu1.setText("Arquivo");

        jMenuItem13.setText("Dados do Login ");
        jMenuItem13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem13ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem13);
        jMenu1.add(jSeparator5);

        jMenuItem12.setText("Sair");
        jMenuItem12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem12ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem12);

        jMenuBar_Principal.add(jMenu1);

        JmCadastro.setText("Cadastros");

        jMenuItem1.setText("Usuário");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        JmCadastro.add(jMenuItem1);

        btnFuncionario.setText("Funcionário");
        btnFuncionario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFuncionarioActionPerformed(evt);
            }
        });
        JmCadastro.add(btnFuncionario);

        jMenuItem3.setText("Fornecedor");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        JmCadastro.add(jMenuItem3);
        JmCadastro.add(jSeparator1);

        btnProduto.setText("Produto");
        btnProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProdutoActionPerformed(evt);
            }
        });
        JmCadastro.add(btnProduto);

        btnFerramenta.setText("Ferramenta");
        btnFerramenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFerramentaActionPerformed(evt);
            }
        });
        JmCadastro.add(btnFerramenta);
        JmCadastro.add(jSeparator4);

        jMenuBar_Principal.add(JmCadastro);

        JmLancamentos.setText("Lançamentos");

        lancarPDL.setText("Lançar Ficha de Produção");
        lancarPDL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lancarPDLActionPerformed(evt);
            }
        });
        JmLancamentos.add(lancarPDL);
        JmLancamentos.add(jSeparator3);

        menuConsultarLancamento.setText("Consultar Lançamentos");
        menuConsultarLancamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuConsultarLancamentoActionPerformed(evt);
            }
        });
        JmLancamentos.add(menuConsultarLancamento);

        jMenuBar_Principal.add(JmLancamentos);

        JmRelatorios.setText("Relatórios");
        JmRelatorios.add(jSeparator2);

        jMenu2.setText("Lançamentos");

        btnlancamentoCadastro.setText("Lançamento Cadastro");
        btnlancamentoCadastro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnlancamentoCadastroActionPerformed(evt);
            }
        });
        jMenu2.add(btnlancamentoCadastro);

        menuRalatorioLancamentoListarLancamento.setText("Listar Lançamentos");
        menuRalatorioLancamentoListarLancamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuRalatorioLancamentoListarLancamentoActionPerformed(evt);
            }
        });
        jMenu2.add(menuRalatorioLancamentoListarLancamento);

        menuRelatorioLancamentoEntreDatas.setText("Listar Lançamentos - Entre Datas");
        menuRelatorioLancamentoEntreDatas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuRelatorioLancamentoEntreDatasActionPerformed(evt);
            }
        });
        jMenu2.add(menuRelatorioLancamentoEntreDatas);

        menuRelatorioLancamentoPorFerramenta.setText("Listar Lançamentos - Por Ferramenta");
        menuRelatorioLancamentoPorFerramenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuRelatorioLancamentoPorFerramentaActionPerformed(evt);
            }
        });
        jMenu2.add(menuRelatorioLancamentoPorFerramenta);

        JmRelatorios.add(jMenu2);

        jMenu5.setText("Produto");

        btnListarProduto.setText("Listar Produto");
        btnListarProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnListarProdutoActionPerformed(evt);
            }
        });
        jMenu5.add(btnListarProduto);

        JmRelatorios.add(jMenu5);

        jMenu6.setText("Fornecedor");

        btnListarFornecedor.setText("Listar Fornecedor");
        btnListarFornecedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnListarFornecedorActionPerformed(evt);
            }
        });
        jMenu6.add(btnListarFornecedor);

        JmRelatorios.add(jMenu6);

        jMenu7.setText("Ferramenta");

        btnListarFerramenta.setText("Listar Ferramenta");
        btnListarFerramenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnListarFerramentaActionPerformed(evt);
            }
        });
        jMenu7.add(btnListarFerramenta);

        JmRelatorios.add(jMenu7);

        jMenu4.setText("Funcionário");

        btnListarFuncionario.setText("Listar Funcionário");
        btnListarFuncionario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnListarFuncionarioActionPerformed(evt);
            }
        });
        jMenu4.add(btnListarFuncionario);

        JmRelatorios.add(jMenu4);

        jMenu3.setText("Usuarios");

        menuRelatorioUsuarioListarUsuario.setText("Listar Usuarios");
        menuRelatorioUsuarioListarUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuRelatorioUsuarioListarUsuarioActionPerformed(evt);
            }
        });
        jMenu3.add(menuRelatorioUsuarioListarUsuario);

        JmRelatorios.add(jMenu3);

        jMenuBar_Principal.add(JmRelatorios);

        setJMenuBar(jMenuBar_Principal);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desktopPrincipal)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desktopPrincipal)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        IjfUsuario ijfUsuario;
        ijfUsuario = new IjfUsuario(idUsuarioLogin);
        desktopPrincipal.add(ijfUsuario);
        ijfUsuario.setLocation(this.getWidth() / 2 - ijfUsuario.getWidth() / 2, this.getHeight() / 2 - ijfUsuario.getHeight() / 2);
        ijfUsuario.setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem12ActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jMenuItem12ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        IjfFornecedor ijfFoenecedor = new IjfFornecedor(false);
        desktopPrincipal.add(ijfFoenecedor);
        ijfFoenecedor.setLocation(this.getWidth() / 2 - ijfFoenecedor.getWidth() / 2, this.getHeight() / 2 - ijfFoenecedor.getHeight() / 2);
        ijfFoenecedor.setVisible(true);
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem13ActionPerformed
        IjfUsuarioLogin userLogin = new IjfUsuarioLogin(user);
        desktopPrincipal.add(userLogin);
        userLogin.setLocation(this.getWidth() / 2 - userLogin.getWidth() / 2, this.getHeight() / 2 - userLogin.getHeight() / 2);
        userLogin.setVisible(true);
    }//GEN-LAST:event_jMenuItem13ActionPerformed

    private void lancarPDLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lancarPDLActionPerformed
        PDL p = new PDL();
        IjfPDL pdl = new IjfPDL(false, new PDL()/*new PDLDao().consultarId(7)*/);
        desktopPrincipal.add(pdl);
        pdl.setLocation(this.getWidth() / 2 - pdl.getWidth() / 2, /*this.getHeight() / 2 - pdl.getHeight() / 2*/0);
        pdl.setVisible(true);
    }//GEN-LAST:event_lancarPDLActionPerformed

    private void menuConsultarLancamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuConsultarLancamentoActionPerformed
        IjfPesquisaLancamento sPdl = new IjfPesquisaLancamento();
        desktopPrincipal.add(sPdl);
        sPdl.setLocation(this.getWidth() / 2 - sPdl.getWidth() / 2, this.getHeight() / 2 - sPdl.getHeight() / 2);
        sPdl.setVisible(true);
    }//GEN-LAST:event_menuConsultarLancamentoActionPerformed

    private void menuRelatorioUsuarioListarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuRelatorioUsuarioListarUsuarioActionPerformed
        gerarLista("/relatorios/list_usuarios.jrxml");
    }//GEN-LAST:event_menuRelatorioUsuarioListarUsuarioActionPerformed

    private void menuRalatorioLancamentoListarLancamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuRalatorioLancamentoListarLancamentoActionPerformed
        gerarLista("/relatorios/list_lancamentos.jrxml");
    }//GEN-LAST:event_menuRalatorioLancamentoListarLancamentoActionPerformed

    private void menuRelatorioLancamentoPorFerramentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuRelatorioLancamentoPorFerramentaActionPerformed
        IjdListarLancamentos r = new IjdListarLancamentos(this, true, 1);
        r.setLocationRelativeTo(this);
        r.setVisible(true);
    }//GEN-LAST:event_menuRelatorioLancamentoPorFerramentaActionPerformed

    private void menuRelatorioLancamentoEntreDatasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuRelatorioLancamentoEntreDatasActionPerformed
        IjdListarLancamentos r = new IjdListarLancamentos(this, true, 0);
        r.setLocationRelativeTo(this);
        r.setVisible(true);
    }//GEN-LAST:event_menuRelatorioLancamentoEntreDatasActionPerformed

    private void btnFerramentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFerramentaActionPerformed
        IjfFerramenta f = new IjfFerramenta();
        desktopPrincipal.add(f);
        f.setLocation(this.getWidth() / 2 - f.getWidth() / 2, /*this.getHeight() / 2 - pdl.getHeight() / 2*/0);
        f.setVisible(true);
    }//GEN-LAST:event_btnFerramentaActionPerformed

    private void btnProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProdutoActionPerformed
        IjfProduto f = new IjfProduto();
        desktopPrincipal.add(f);
        f.setLocation(this.getWidth() / 2 - f.getWidth() / 2, /*this.getHeight() / 2 - pdl.getHeight() / 2*/0);
        f.setVisible(true);
    }//GEN-LAST:event_btnProdutoActionPerformed

    private void btnFuncionarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFuncionarioActionPerformed
        IjfFuncionario f = new IjfFuncionario();
        desktopPrincipal.add(f);
        f.setLocation(this.getWidth() / 2 - f.getWidth() / 2, /*this.getHeight() / 2 - pdl.getHeight() / 2*/0);
        f.setVisible(true);
    }//GEN-LAST:event_btnFuncionarioActionPerformed

    private void btnListarFuncionarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnListarFuncionarioActionPerformed
        gerarLista("/relatorios/list_funcionario.jrxml");
    }//GEN-LAST:event_btnListarFuncionarioActionPerformed

    private void btnListarProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnListarProdutoActionPerformed
        gerarLista("/relatorios/list_produto.jrxml");
    }//GEN-LAST:event_btnListarProdutoActionPerformed

    private void btnListarFornecedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnListarFornecedorActionPerformed
        gerarLista("/relatorios/list_fornecedor.jrxml");
    }//GEN-LAST:event_btnListarFornecedorActionPerformed

    private void btnListarFerramentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnListarFerramentaActionPerformed
        gerarLista("/relatorios/list_ferramenta.jrxml");
    }//GEN-LAST:event_btnListarFerramentaActionPerformed

    private void btnlancamentoCadastroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnlancamentoCadastroActionPerformed
        IjfPesquisaLancamento sPdl = new IjfPesquisaLancamento();
        desktopPrincipal.add(sPdl);
        sPdl.setLocation(this.getWidth() / 2 - sPdl.getWidth() / 2, this.getHeight() / 2 - sPdl.getHeight() / 2);
        sPdl.setVisible(true);
    }//GEN-LAST:event_btnlancamentoCadastroActionPerformed

//    /**
//     * @param args the command line arguments
//     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(JfPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(JfPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(JfPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(JfPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new JfPrincipal().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu JmCadastro;
    private javax.swing.JMenu JmLancamentos;
    private javax.swing.JMenu JmRelatorios;
    private javax.swing.JMenuItem btnFerramenta;
    private javax.swing.JMenuItem btnFuncionario;
    private javax.swing.JMenuItem btnListarFerramenta;
    private javax.swing.JMenuItem btnListarFornecedor;
    private javax.swing.JMenuItem btnListarFuncionario;
    private javax.swing.JMenuItem btnListarProduto;
    private javax.swing.JMenuItem btnProduto;
    private javax.swing.JMenuItem btnlancamentoCadastro;
    public static javax.swing.JDesktopPane desktopPrincipal;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenu jMenu7;
    private javax.swing.JMenuBar jMenuBar_Principal;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem12;
    private javax.swing.JMenuItem jMenuItem13;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private javax.swing.JPopupMenu.Separator jSeparator4;
    private javax.swing.JPopupMenu.Separator jSeparator5;
    private javax.swing.JMenuItem lancarPDL;
    private javax.swing.JMenuItem menuConsultarLancamento;
    private javax.swing.JMenuItem menuRalatorioLancamentoListarLancamento;
    private javax.swing.JMenuItem menuRelatorioLancamentoEntreDatas;
    private javax.swing.JMenuItem menuRelatorioLancamentoPorFerramenta;
    private javax.swing.JMenuItem menuRelatorioUsuarioListarUsuario;
    // End of variables declaration//GEN-END:variables

    private void gerarLista(String caminho){
        try {
            // Compila o relatorio
            JasperReport relatorio = JasperCompileManager.compileReport(getClass().getResourceAsStream(caminho));

            // Mapeia campos de parametros para o relatorio, mesmo que nao existam
            Map parametros = new HashMap();

            // Executa relatoio
            JasperPrint impressao = JasperFillManager.fillReport(relatorio, parametros, ConexaoBD.getInstance().getConnection());

            // Exibe resultado em video
            JasperViewer.viewReport(impressao, false);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao gerar relatório: " + e);
        }
    }
    
}
