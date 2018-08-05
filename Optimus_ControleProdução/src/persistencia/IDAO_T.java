/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.util.ArrayList;
import javax.swing.JTable;

/**
 *
 * @author pretto
 */

// Utiliza Generics como tipo de dado

public interface IDAO_T <T> {

    public String salvar(T o);

    public String atualizar(T o);

    public String excluir(int id);

    public ArrayList<T> consultarTodos();

    public ArrayList<T> consultar(String criterio);

    public T consultarId(int id);
    
    public void popularTabela(JTable tabela, String criterio, int id);
    
    public void popularTabelaPesquisarSimples(JTable tabela, String criterio);
    
}
