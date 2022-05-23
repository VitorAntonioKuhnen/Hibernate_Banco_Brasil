/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senac.dao;

import br.com.senac.entidade.PessoaJuridica;
import java.io.Serializable;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

/**
 *
 * @author vitor.kuhnen
 */
public class PessoaJuridicaDaoImpl extends BaseDaoImpl<PessoaJuridica, Long> implements PessoaJuridicaDao, Serializable{

    @Override
    public PessoaJuridica pesquisarPorId(Long id, Session session) throws HibernateException {
        return session.find(PessoaJuridica.class, id);
    }

    @Override
    public List<PessoaJuridica> askPerName(String nome, Session session) throws HibernateException {
        Query<PessoaJuridica> consulta = session.createQuery("from PessoaJuridica pj where pj.nome like :nome");
        consulta.setParameter("nome", "%" + nome + "%");
        return consulta.getResultList();
    }
    
}
