package com.sd.projeto1.main;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import com.sd.projeto1.model.Mapa;
import com.sd.projeto1.util.FileUtils;

public class Server {

	public static void main(String[] args) throws Exception {
		List<Mapa> logs = new ArrayList<Mapa>();

		FileUtils.read();// leitura do arquivo de log para inicialização do banco

		for (Mapa m : logs) {
			BigInteger chave = new BigInteger(String.valueOf(m.getChave()));

			ServerThreadDisco.mapa.put(chave, m.getTexto());
		}

		System.out.println("Log do Disco Recuperado");
		System.out.println("Tamanho da Fila: " + ServerThreadDisco.mapa.size() + "\n");

		System.out.println("Servidor Iniciado...");
		new Thread(new ServerThreadReceive()).start();
	}

}
