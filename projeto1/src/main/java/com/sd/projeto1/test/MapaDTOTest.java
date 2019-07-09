package com.sd.projeto1.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.sd.projeto1.model.Mapa;
import com.sd.projeto1.model.MapaDTO;

public class MapaDTOTest {

	MapaDTO mapaDTO = new MapaDTO();
    private List<Mapa> mapas = new ArrayList<>();
    private Mapa mapa = new Mapa();
    private String mensagem;
    
	@Test
	public void constructorTest() {
		mapa.setChave(1);
		mapa.setTexto("tttt");
		mapa.setTipoOperacaoId(4);
		
		mapas.add(mapa);
		
		mapaDTO.setMapa(mapa);
		mapaDTO.setMapas(mapas);
		mapaDTO.setMensagem("Teste");
		
		assertEquals("Teste", mapaDTO.getMensagem());
		assertNotNull(mapaDTO.getMapa());
		assertNotNull(mapaDTO.getMapas());
		assertNotNull(mapas);
		assertNotNull(mapa);
	}
	
	

}
