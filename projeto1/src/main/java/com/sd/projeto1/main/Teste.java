package com.sd.projeto1.main;

import java.io.IOException;

import com.sd.projeto1.test.ClientServerTest;

public class Teste {

	public static void main(String[] args) throws Exception {

		Server server = new Server();

		server.main(null);

		ClientServerTest c = new ClientServerTest();

		//c.sendTest(); Teste inserir OK
		//System.out.println("Inseridos.");

		c.deleteTest();
		System.out.println("Excluido.");

	}

}
