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
public class Ferramenta {
    private int idferramenta;
    private String codigo;
    private String descricao;
    private Fornecedor fornecedor;
    private boolean ativo;
    private boolean deleted;
//    private Data deleted_time;
//    private Usuario deleted_user;

    public Ferramenta(int idferramenta, String codigo, String descricao, Fornecedor fornecedor, boolean ativo, boolean deleted, Data deleted_time, Usuario deleted_user) {
        this.idferramenta = idferramenta;
        this.codigo = codigo;
        this.descricao = descricao;
        this.fornecedor = fornecedor;
        this.ativo = ativo;
        this.deleted = deleted;
//        this.deleted_time = deleted_time;
//        this.deleted_user = deleted_user;
    }

    public Ferramenta() {
        idferramenta = -1;
        descricao = null;
    }

    
    
    public int getIdferramenta() {
        return idferramenta;
    }

    public void setIdferramenta(int idferramenta) {
        this.idferramenta = idferramenta;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

//    public Data getDeleted_time() {
//        return deleted_time;
//    }
//
//    public void setDeleted_time(Data deleted_time) {
//        this.deleted_time = deleted_time;
//    }
//
//    public Usuario getDeleted_user() {
//        return deleted_user;
//    }
//
//    public void setDeleted_user(Usuario deleted_user) {
//        this.deleted_user = deleted_user;
//    }
    
    
}
