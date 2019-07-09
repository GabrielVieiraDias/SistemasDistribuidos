package com.sd.projeto1.test;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;

import org.apache.commons.lang3.SerializationUtils;

import com.sd.projeto1.main.*;
import com.sd.projeto1.model.Mapa;

public class ClientServerTest {

	Client c = new Client();
	int chave = 0;

	public void sendTest() throws IOException, NoSuchFieldException, SecurityException {

		c.enderecoIP = InetAddress.getByName(c.pm.getAddress());
		c.socketCliente = new DatagramSocket();
		int i = 0;
		Mapa mapa = new Mapa();
		mapa.setChave(0);
		mapa.setTipoOperacaoId(1);
		mapa.setTexto("Teste 0");
		mapa.setData(new Date());

		byte[] object = SerializationUtils.serialize(mapa);
		c.send(object);
		
		
		try {
			File file = new File("app.log");
			while( file.exists() == false) {
				Thread.currentThread().sleep(1000);
				file = new File("app.log");
			}
		}catch(Exception e) {}
		
		BufferedReader br = Files.newBufferedReader(Paths.get("app.log"));
		String line = br.readLine();
		
		assertEquals("1#0#Teste 0", line);
	}

	public void updateTest() throws IOException {
		c.enderecoIP = InetAddress.getByName(c.pm.getAddress());
		c.socketCliente = new DatagramSocket();
		Mapa mapa = new Mapa();
		mapa.setChave(2);
		mapa.setTipoOperacaoId(2);
		mapa.setTexto("Teste Update");

		byte[] objectUpdate = SerializationUtils.serialize(mapa);
		c.send(objectUpdate);
	}

	public void deleteTest() throws IOException, NoSuchFieldException, SecurityException {
		
		sendTest();
		
		c.enderecoIP = InetAddress.getByName(c.pm.getAddress());
		c.socketCliente = new DatagramSocket();
		Mapa mapa = new Mapa();
		mapa.setChave(0);
		mapa.setTipoOperacaoId(3);
		System.out.println("Excluído." + mapa.getTexto());

		byte[] objectDelete = SerializationUtils.serialize(mapa);
		c.send(objectDelete);
	}

}
