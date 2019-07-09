package com.sd.projeto1.test;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

import com.sd.projeto1.model.Mapa;

public class MapaTest {

	Mapa mapa = new Mapa();
	@Test
	public void getChaveTest() {
		mapa.setChave(1);
		assertEquals(1, mapa.getChave());
	}

	
	@Test
	public void getTexto() {
		mapa.setTexto("Execução da classe de teste.");
		assertEquals("Execução da classe de teste.", mapa.getTexto());
	}
	
	@Test
	public void getTipoOperacaoId() {
		mapa.setTipoOperacaoId(3);
		assertEquals(3, mapa.getTipoOperacaoId());
	}
	
	@Test
	public void getData() {
		Date d = new Date();
		mapa.setData(d);
		assertEquals(d, mapa.getData());
	}
	
	
}
