/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import apoio.Data;
import java.util.ArrayList;

/**
 *
 * @author anderson.caye
 */
public class PDL {
    
    private int idFicha;
    private Data dataRetirada;
    private Data dataDevolucao;
    private String motivoDevolucao;
    private Funcionario funcionario;
    private Ferramenta ferramenta;
    private ArrayList<ItemPDL> itens = new ArrayList();
    private ArrayList<Integer> removerItens = new ArrayList();

    public PDL() {
        idFicha = -1;
        motivoDevolucao = null;
        dataDevolucao = new Data(0);
        dataRetirada = new Data(0);
        funcionario = new Funcionario();
        ferramenta = new Ferramenta();
    }

    public PDL(PDL pdl) {
        this.idFicha = pdl.getIdFicha();
        this.dataRetirada = new Data(pdl.getDataRetirada());
        this.dataDevolucao = new Data (pdl.getDataDevolucao());
        this.motivoDevolucao = pdl.getMotivoDevolucao();
        this.funcionario = pdl.getFuncionario();
        this.ferramenta = pdl.getFerramenta();
        this.itens = pdl.getItens();
    }
    
    public PDL(int idFicha) {
        
        this.setIdFicha(idFicha);
        
    }
    
    
    /**
     * @return the idFicha
     */
    public int getIdFicha() {
        return idFicha;
    }

    /**
     * @param idFicha the idFicha to set
     */
    public void setIdFicha(int idFicha) {
        this.idFicha = idFicha;
    }

    /**
     * @return the dataRetirada
     */
    public String getDataRetirada() {
        return dataRetirada.obterData();
    }
    
    public boolean getDataRetiradaIsEmpty() {
        return dataRetirada.isEmpty();
    }

    /**
     * @param dataRetirada the dataRetirada to set
     */
    public void setDataRetirada(Data dataRetirada) {
        this.dataRetirada = dataRetirada;
    }

    /**
     * @return the dataDevolucao
     */
    public String getDataDevolucao() {
        return dataDevolucao.obterData();
    }
    
    public boolean getDataDevolucaoIsEmpty() {
        return dataDevolucao.isEmpty();
    }

    /**
     * @param dataDevolucao the dataDevolucao to set
     */
    public void setDataDevolucao(Data dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    /**
     * @return the motivoDevolucao
     */
    public String getMotivoDevolucao() {
        return motivoDevolucao;
    }

    /**
     * @param motivoDevolucao the motivoDevolucao to set
     */
    public void setMotivoDevolucao(String motivoDevolucao) {
        this.motivoDevolucao = motivoDevolucao;
    }

    /**
     * @return the funcionario
     */
    public Funcionario getFuncionario() {
        return funcionario;
    }

    /**
     * @param funcionario the funcionario to set
     */
    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    /**
     * @return the ferramenta
     */
    public Ferramenta getFerramenta() {
        return ferramenta;
    }

    /**
     * @param ferramenta the ferramenta to set
     */
    public void setFerramenta(Ferramenta ferramenta) {
        this.ferramenta = ferramenta;
    }

    /**
     * @return the itens
     */
    public ArrayList<ItemPDL> getItens() {
        return itens;
    }
    
    /**
     * @return the itens
     */
    public ItemPDL getItem(int index) {
        ItemPDL i = null;
        if(index < itens.size()){
            i = itens.get(index);
        }
        return i;
    }

    /**
     * @param itens the itens to set
     */
    public void setItens(ArrayList<ItemPDL> itens) {
        this.itens = itens;
    }
    
    public void setItens(ItemPDL item) {
        itens.add(item);
    }
    
    public void setItenIndex(int index, ItemPDL item) {
        itens.remove(index);
        itens.add(index, item);
    }
    
    public void setItenIndexRemove(int index) {
        itens.remove(index);
    }
    
    public ArrayList<Integer> getRemoverItens() {
        return removerItens;
    }

    public void setRemoverItens(ArrayList<Integer> removerItens) {
        this.removerItens = removerItens;
    }
}
