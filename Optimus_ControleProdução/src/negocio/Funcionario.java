/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import apoio.Data;
import java.util.Date;

/**
 *
 * @author anderson.caye
 */
public class Funcionario {
    
    private int idFuncionario;
    private String nome;
    private String apelido;
    private String cpf;
    private String rg;
    private String endereco;
    private String cidade;
    private String estado;
    private Data dataIngresso;
    private Data dataAdmissao;
    private Data dataDemissao;
    private Double salario;
    private String funcao;
    private boolean ativo;
    private boolean deleted;

    public Funcionario(int idFuncionario, String nome, String apelido, String cpf, String rg, String endereco, String cidade, String estado, Data dataIngresso, Data dataAdmissao, Data dataDemissao, Double salario, String funcao, boolean ativo, boolean deleted) {
        this.idFuncionario = idFuncionario;
        this.nome = nome;
        this.apelido = apelido;
        this.cpf = cpf;
        this.rg = rg;
        this.endereco = endereco;
        this.cidade = cidade;
        this.estado = estado;
        this.dataIngresso = dataIngresso;
        this.dataAdmissao = dataAdmissao;
        this.dataDemissao = dataDemissao;
        this.salario = salario;
        this.funcao = funcao;
        this.ativo = ativo;
        this.deleted = deleted;
    }

    public Funcionario() {
        nome = null;
        idFuncionario = -1;
        this.apelido = "";
        this.cpf = "";
        this.rg = "";
        this.endereco = "";
        this.cidade = "";
        this.estado = "";
        this.dataIngresso = new Data(0);
        this.dataAdmissao = new Data(0);
        this.dataDemissao = new Data(0);
        this.salario = 0.0;
        this.funcao = "";
    }
    
    
    
    public int getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(int idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Data getDataIngresso() {
        return dataIngresso;
    }

    public void setDataIngresso(Data dataIngresso) {
        this.dataIngresso = dataIngresso;
    }

    public Data getDataAdmissao() {
        return dataAdmissao;
    }

    public void setDataAdmissao(Data dataAdmissao) {
        this.dataAdmissao = dataAdmissao;
    }

    public Data getDataDemissao() {
        return dataDemissao;
    }

    public void setDataDemissao(Data dataDemissao) {
        this.dataDemissao = dataDemissao;
    }

    public Double getSalario() {
        return salario;
    }

    public void setSalario(Double salario) {
        this.salario = salario;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
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
