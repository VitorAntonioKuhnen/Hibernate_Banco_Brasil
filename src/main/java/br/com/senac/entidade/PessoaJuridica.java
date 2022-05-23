/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senac.entidade;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 *
 * @author vitor.kuhnen
 */

@Entity
@Table(name = "pessoa_juridica")
@PrimaryKeyJoinColumn(name = "id_cliente")
public class PessoaJuridica extends Cliente{
    
    @Column(nullable = false, unique = true)
    private String cnpj;
    
    @Column(nullable = false)
    private String escricaoEstadual;

    public PessoaJuridica() {
    }

    public PessoaJuridica(String nome, String email, String cnpj, String escricaoEstadual) {
        super(nome, email);
        this.cnpj = cnpj;
        this.escricaoEstadual = escricaoEstadual;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getEscricaoEstadual() {
        return escricaoEstadual;
    }

    public void setEscricaoEstadual(String escricaoEstadual) {
        this.escricaoEstadual = escricaoEstadual;
    }

    
    
    
    
    
}
