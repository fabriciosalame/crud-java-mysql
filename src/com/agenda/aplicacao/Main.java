package com.agenda.aplicacao;

import com.agenda.dao.ContatoDAO;
import com.agenda.model.Contato;

import java.util.Date;

public class Main {

    public static void main(String[] args) {

        ContatoDAO contatoDAO = new ContatoDAO();

        Contato contato = new Contato();
        contato.setNome("Vânia Rosa");
        contato.setIdade(45);
        contato.setDataCadastro(new Date());

        contatoDAO.save(contato);

        for (Contato c : contatoDAO.getContatos()) {
            System.out.println("Contato: "+c.getNome());
        }
    }

}
