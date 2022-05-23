/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senac.dao;

import br.com.senac.entidade.Endereco;
import br.com.senac.entidade.PessoaJuridica;
import static br.com.senac.util.GeradorUtil.gerarEndereco;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author vitor.kuhnen
 */
public class PessoaJuridicaDaoImplTest {
    
    private PessoaJuridica pessoaJuridica;
    private PessoaJuridicaDao pessoaJuridicaDao;
    private Session session;
    
    public PessoaJuridicaDaoImplTest() {
        pessoaJuridicaDao = new PessoaJuridicaDaoImpl();
    }

//    @Test
    public void testSalvar(){
        System.out.println("Save");
        pessoaJuridica = new PessoaJuridica("Vítor", "vitefr@gmail.com", "193198319135", "Isso ai");
        Endereco endereco = gerarEndereco();
        pessoaJuridica.setEndereco(endereco);
        endereco.setCliente(pessoaJuridica);
        session = HibernateUtil.abrirConexao();
        pessoaJuridicaDao.salvarOuAlterar(pessoaJuridica, session);
        session.close();
        assertNotNull(pessoaJuridica.getId());
    }
    
//    @Test
    public void testalterar() {
        System.out.println("alterar");
        buscarPessoaJuridicaBd();
        pessoaJuridica.setNome("Pedrão");
        pessoaJuridica.getEndereco().setLogradouro("Rua papel tesoura");
        session = HibernateUtil.abrirConexao();
        pessoaJuridicaDao.salvarOuAlterar(pessoaJuridica, session);
        session.close();

        session = HibernateUtil.abrirConexao();
        PessoaJuridica pesFisicaAlt = pessoaJuridicaDao.pesquisarPorId(pessoaJuridica.getId(), session);
        session.close();

        assertEquals(pessoaJuridica.getNome(), pesFisicaAlt.getNome());
    }
    
//    @Test
    public void testExcluir(){
        System.out.println("Excluir");
        buscarPessoaJuridicaBd();
        session = HibernateUtil.abrirConexao();
        pessoaJuridicaDao.excluir(pessoaJuridica, session);

        PessoaJuridica pesJuridicaExc = pessoaJuridicaDao.pesquisarPorId(pessoaJuridica.getId(), session);
        session.close();
        
        assertNotNull(pesJuridicaExc);
    }
    
     @Test
    public void testePesquisarPorNome(){
        System.out.println("pesquisarPorNome");
        buscarPessoaJuridicaBd();
        session = HibernateUtil.abrirConexao();
        List<PessoaJuridica> pessoasJ = pessoaJuridicaDao.askPerName(pessoaJuridica.getNome(), session);
        session.close();
        assertFalse(pessoasJ.isEmpty());
        System.out.println(pessoaJuridica.getId() + pessoaJuridica.getNome());
    }
    
//    @Test
    public void testPesquisarPorId() {
        System.out.println("pesquisarPorId");
        buscarPessoaJuridicaBd();
        session = HibernateUtil.abrirConexao();
        PessoaJuridica pesJuridica = pessoaJuridicaDao.pesquisarPorId(pessoaJuridica.getId(), session);
        session.close();
        assertNotNull(pesJuridica);
        
    }
    
    public PessoaJuridica buscarPessoaJuridicaBd() {
        String hql = "from PessoaJuridica pj";
        session = HibernateUtil.abrirConexao();
        Query<PessoaJuridica> consulta = session.createQuery(hql);
        List<PessoaJuridica> pessoaJuridicas = consulta.getResultList();
        session.close();
        if (pessoaJuridicas.isEmpty()) {
            testSalvar();
        } else {
            pessoaJuridica = pessoaJuridicas.get(0);
        }
        return pessoaJuridica;
    }
    
}
