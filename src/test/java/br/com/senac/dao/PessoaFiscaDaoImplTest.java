/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senac.dao;

import br.com.senac.entidade.Endereco;
import br.com.senac.entidade.PessoaFisica;
import static br.com.senac.util.GeradorUtil.*;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author vitor.kuhnen
 */
public class PessoaFiscaDaoImplTest {

    private PessoaFisica pessoaFisica;
    private PessoaFisicaDao pessoaFisicaDao;
    private Session session; 

    public PessoaFiscaDaoImplTest() {
        pessoaFisicaDao = new PessoaFiscaDaoImpl();
    }

    @Test
    public void testSalvar() {
        System.out.println("salvar");
        ProfissaoDaoImplTest pdit = new ProfissaoDaoImplTest();
        
        pessoaFisica = new PessoaFisica("Vítor", "astalavista@gmail", "12347398710", "1456967");
        Endereco endereco = gerarEndereco();
        pessoaFisica.setEndereco(endereco);
        endereco.setCliente(pessoaFisica);
        pessoaFisica.setProfissao(pdit.buscarProfissaoBd());
        session = HibernateUtil.abrirConexao();
        pessoaFisicaDao.salvarOuAlterar(pessoaFisica, session);
        session.close();
        assertNotNull(pessoaFisica.getId());
    }
    

    

//    @Test
    public void testalterar() {
        System.out.println("alterar");
        ProfissaoDaoImplTest pdit = new ProfissaoDaoImplTest();
        buscarPessoaFisicaBd();
        pessoaFisica.setNome("Pedrão");
        pessoaFisica.getEndereco().setLogradouro("Rua papel tesoura");
        pessoaFisica.setProfissao(pdit.buscarProfissaoBd());
        session = HibernateUtil.abrirConexao();
        pessoaFisicaDao.salvarOuAlterar(pessoaFisica, session);
        session.close();

        session = HibernateUtil.abrirConexao();
        PessoaFisica pesFisicaAlt = pessoaFisicaDao.pesquisarPorId(pessoaFisica.getId(), session);
        session.close();

        assertEquals(pessoaFisica.getNome(), pesFisicaAlt.getNome());
    }

//    @Test
    public void testExcluir() {
        System.out.println("Excluir");
        buscarPessoaFisicaBd();
        session = HibernateUtil.abrirConexao();
        pessoaFisicaDao.excluir(pessoaFisica, session);

        PessoaFisica pesFisicaExc = pessoaFisicaDao.pesquisarPorId(pessoaFisica.getId(), session);
        session.close();
        
        assertNotNull(pesFisicaExc);

    }
    
//     @Test
    public void testePesquisarPorNome(){
        System.out.println("pesquisarPorNome");
        buscarPessoaFisicaBd();
        session = HibernateUtil.abrirConexao();
        List<PessoaFisica> pessoasF = pessoaFisicaDao.askPerName(pessoaFisica.getNome(), session);
        session.close();
        assertFalse(pessoasF.isEmpty());
        System.out.println(pessoaFisica.getId() + pessoaFisica.getNome());
    }
    

//    @Test
    public void testPesquisarPorId() {
        System.out.println("pesquisarPorId");
        buscarPessoaFisicaBd();
        session = HibernateUtil.abrirConexao();
        PessoaFisica pesFisica = pessoaFisicaDao.pesquisarPorId(pessoaFisica.getId(), session);
        session.close();
        assertNotNull(pesFisica);
    }

    public PessoaFisica buscarPessoaFisicaBd() {
        String hql = "from PessoaFisica pf";
        session = HibernateUtil.abrirConexao();
        Query<PessoaFisica> consulta = session.createQuery(hql);
        List<PessoaFisica> pessoaFisicas = consulta.getResultList();
        session.close();
        if (pessoaFisicas.isEmpty()) {
            testSalvar();
        } else {
            pessoaFisica = pessoaFisicas.get(0);
        }
        return pessoaFisica;
    }

}
