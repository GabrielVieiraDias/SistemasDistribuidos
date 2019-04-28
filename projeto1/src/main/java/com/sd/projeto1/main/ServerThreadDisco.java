/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sd.projeto1.main;

import com.sd.projeto1.dao.MapaDao;
import com.sd.projeto1.model.Mapa;
import com.sd.projeto1.model.MapaDTO;
import com.sd.projeto1.util.FileUtils;
import com.sd.projeto1.util.PropertyManagement;
import com.sd.projeto1.util.Utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.lang3.SerializationUtils;

/**
 *
 * 
 */
public class ServerThreadDisco implements Runnable {

	public static Map<BigInteger, String> mapa = new HashMap();
	private DatagramSocket socketServidor;
	private static PropertyManagement pm;
	private static byte[] in;
	private MapaDao mapaDAO = new MapaDao();
	private ExecutorService executor;

	/// Recebendo o pacote da Thread Anterior;
	ServerThreadDisco(DatagramSocket socketServidor) {
		this.socketServidor = socketServidor;
	}

	@Override
	public void run() {
		try {
			executor = Executors.newCachedThreadPool();
			pm = new PropertyManagement();
			// socketServidor = new DatagramSocket(pm.getPort());

			while (true) {
				in = new byte[1400];
				DatagramPacket receivedPacket = MultiQueue.getDiscoFila();
				if (receivedPacket != null) {

					Mapa maparetorno = new Mapa();
					maparetorno = (Mapa) SerializationUtils.deserialize(receivedPacket.getData());

					MapaDTO mapaDisco = new MapaDTO();
					mapaDisco = tipoOperacao(maparetorno);

					ServerThreadSend serverSend = new ServerThreadSend(mapaDisco, socketServidor);

					if (serverSend != null) {
						executor.execute(serverSend);
					}
				}

			}
		} catch (IOException ex) {
			Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
		} catch (Exception e) {
			Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, e);
		}
	}

	public static void salvar(Mapa mapValue) {
		BigInteger key = new BigInteger(String.valueOf(mapValue.getChave()));

		if (mapa.containsKey(mapValue.getChave())) {
			System.out.println("Mensagem com essa chave já adicionada");
		}
		FileUtils.writeFile(String.valueOf(Utils.CREATE), key, mapValue.getTexto());
		mapa.put(key, mapValue.getTexto());
	}

	

	public static void editar(Mapa mapValue) {
		BigInteger key = new BigInteger(String.valueOf(mapValue.getChave()));

		if (!mapa.containsKey(mapValue.getChave())) {
			System.out.println("Chave não encontrada");
		}
		FileUtils.writeFile(String.valueOf(Utils.UPDATE), key, mapValue.getTexto());
		mapa.put(key, mapValue.getTexto());
	}

	public static void excluir(Mapa mapValue) {
		BigInteger key = new BigInteger(String.valueOf(mapValue.getChave()));
		FileUtils.writeFile(String.valueOf(Utils.DELETE), key, mapValue.getTexto());
		mapa.remove(key);
	}

	public static String buscar(Mapa mapa1) {
		BigInteger chave = new BigInteger(String.valueOf(mapa1.getChave()));
		return mapa.get(chave);
	}

	public static void imprimeCRUD(Mapa mapa1) {
		System.out.println("\n===============================");
		System.out.println("Chave: " + mapa1.getChave());
		System.out.println("Texto: " + mapa1.getTexto());
		System.out.println("Tipo de Operaçao: " + Utils.retornaTipoOperacao(mapa1.getTipoOperacaoId()));
		System.out.println("Data: " + mapa1.getData());
		System.out.println("Tamanho da fila: " + mapa.size());
		System.out.println("===============================");
	}

	public MapaDTO tipoOperacao(Mapa mapaEntity) throws Exception {

		MapaDTO mapaDTO = new MapaDTO();

		switch (mapaEntity.getTipoOperacaoId()) {
		case 1:
			

			if (mapaEntity != null) {
				mapaDTO.setMapa(mapaEntity);
				salvar(mapaEntity);
				imprimeCRUD(mapaEntity);
				mapaDTO.setMensagem("Inserido com Sucesso!");

			} else {
				mapaDTO.setMensagem("Erro ao inserir!");
			}
			break;
		case 2:

			if (mapaEntity != null) {
				mapaDTO.setMapa(mapaEntity);
				editar(mapaEntity);
				imprimeCRUD(mapaEntity);
				mapaDTO.setMensagem("Atualizado com Sucesso!");

			} else {
				mapaDTO.setMensagem("Erro ao atualizar!");
			}
			break;
		case 3:
			

			if (mapaEntity != null) {
				mapaEntity.setTipoOperacaoId(3);
				mapaDTO.setMapa(mapaEntity);
				excluir(mapaEntity);
				imprimeCRUD(mapaEntity);
				mapaDTO.setMensagem("Excluido com Sucesso!");

			} else {
				mapaDTO.setMensagem("Excluido ao atualizar!");
			}
			break;
		case 4:
			

			if (mapaEntity.getChave() != 0) {
				mapaEntity.setTipoOperacaoId(4);
				mapaDTO.setMapa(mapaEntity);
				// buscar(ma.getChave());
				imprimeCRUD(mapaEntity);
				mapaDTO.setMensagem("Recuperado com Sucesso!");

			} else {
				mapaDTO.setMensagem("Erro ao recuperar!");
			}
			break;
		default:
			mapaDTO.setMapa(null);
			mapaDTO.setMensagem("Opção inválida");

		}

		return mapaDTO;
	}

}
