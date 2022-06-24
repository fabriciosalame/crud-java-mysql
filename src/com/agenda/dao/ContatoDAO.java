package com.agenda.dao;

import com.agenda.factory.ConnectionFactory;
import com.agenda.model.Contato;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContatoDAO {

    public void save(Contato contato) {

        String sql = "INSERT INTO contatos(nome, idade, datacadastro) VALUES (?, ?, ?)";

        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            conn = ConnectionFactory.createConnectionToMySQL();

            pstm = conn.prepareStatement(sql);
            pstm.setString(1, contato.getNome());
            pstm.setInt(2, contato.getIdade());
            pstm.setDate(3, new Date(contato.getDataCadastro().getTime()));

            pstm.execute();

            System.out.println("Contato salvo com sucesso.");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstm!=null) {
                    pstm.close();
                }
                if (conn!=null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public List<Contato> getContatos() {

        String sql = "SELECT * FROM contatos";

        List<Contato> contatos = new ArrayList<>();

        Connection conn = null;
        PreparedStatement pstm = null;

        ResultSet rset = null;

        try {
            conn = ConnectionFactory.createConnectionToMySQL();
            pstm = conn.prepareStatement(sql);
            rset = pstm.executeQuery();

            while (rset.next()) {
                Contato contato = new Contato();
                contato.setId(rset.getInt("id"));
                contato.setNome(rset.getString("nome"));
                contato.setIdade(rset.getInt("idade"));
                contato.setDataCadastro(rset.getDate("datacadastro"));

                contatos.add(contato);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(rset!=null) {
                    rset.close();
                }
                if (pstm!=null) {
                    pstm.close();
                }
                if (conn!=null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return contatos;
    }
}
