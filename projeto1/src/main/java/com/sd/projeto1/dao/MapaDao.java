package com.sd.projeto1.dao;

import com.sd.projeto1.model.Mapa;
import com.sd.projeto1.util.SQLiteConnection;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class MapaDao implements Serializable {

    private static final long serialVersionUID = 1L;

    public Mapa buscarPorId(Integer id) throws Exception {

            Mapa mapa = new Mapa();

            return mapa;

    }

    public Mapa salvar(Mapa mapa) throws Exception {
    	Mapa m = buscarPorId(mapa.getChave());

        return m;
    }

    public Mapa editar(Mapa mapa) throws Exception {
    	Mapa m = buscarPorId(mapa.getChave());

        return m;
    }

    public Mapa excluir(int id) throws Exception {
    	Mapa m = buscarPorId(id);

        return m;

    }

    public List<Mapa> buscarTodos() throws Exception {
    	
        return new ArrayList<Mapa>();

    }

}
