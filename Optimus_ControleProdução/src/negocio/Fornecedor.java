/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

/**
 *
 * @author XorNOTE
 */
public class Fornecedor {
    private int idFornecedor;
    private String razaoSocial;
    private String nomeFantasia;
    private String cnpj;
    private String ie;
    private String endereco;
    private String cidade;
    private String estado;
    private boolean ativo;
    private boolean deleted;

    public Fornecedor() {
        idFornecedor = -1;
        razaoSocial = "";
        nomeFantasia = "";
        cnpj = "";
        ie = "";
        endereco = "";
        cidade = "";
        estado = "";
    }

    public int getIdFornecedor() {
        return idFornecedor;
    }

    public void setIdFornecedor(int idFornecedor) {
        this.idFornecedor = idFornecedor;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        if(!nomeFantasia.trim().isEmpty()){
            this.nomeFantasia = nomeFantasia;
        }
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        if(!cnpj.equals("  .   .   /    -  ")){
            this.cnpj = cnpj;
        }
    }

    public String getIe() {
        return ie;
    }

    public void setIe(String ie) {
        
        if(!ie.trim().isEmpty()){
            this.ie = ie;
        }
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        if(!endereco.trim().isEmpty()){
            this.endereco = endereco;
        }
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        if(!cidade.trim().isEmpty()){
            this.cidade = cidade;
        }
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        if(!estado.trim().isEmpty()){
            this.estado = estado;
        }
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
    
}
