/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import apoio.Data;

/**
 *
 * @author anderson.caye
 */
public class ItemPDL {

    private int idItem;
    private Data data;
    private int idFicha;
    private Funcionario funcionario;
    private ProdutoMaterial produto;
    private Double quantidade;

    public ItemPDL() {
        idItem = -1;
    }

    public int getIdItem() {
        return idItem;
    }

    public void setIdItem(int idItem) {
        this.idItem = idItem;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public int getIdFicha() {
        return idFicha;
    }

    public void setIdFicha(int idFicha) {
        this.idFicha = idFicha;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public ProdutoMaterial getProduto() {
        return produto;
    }

    public void setProduto(ProdutoMaterial produto) {
        this.produto = produto;
    }

    public Double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Double quantidade) {
        this.quantidade = quantidade;
    }

}
