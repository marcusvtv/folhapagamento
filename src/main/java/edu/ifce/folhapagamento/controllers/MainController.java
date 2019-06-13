package edu.ifce.folhapagamento.controllers;



import org.springframework.beans.factory.annotation.Autowired;


import edu.ifce.folhapagamento.domain.Colaborador;
import edu.ifce.folhapagamento.domain.OcorrenciaFolha;
import edu.ifce.folhapagamento.repositories.ColaboradorRepository;


public class MainController {
	@Autowired
	   private ColaboradorRepository colaboradorRepository;
	   
	public  Colaborador getColaboradorById(Integer id) {
		Colaborador colaborador = new Colaborador();
		try {
			colaborador = colaboradorRepository.findById(id).get();				
		}catch (Exception e) {
			
		}
		return colaborador;
	}
	
	public void setNewOcorrenciaInColaborador(OcorrenciaFolha ocorrencia, Colaborador colaborador) {
		colaborador.getOcorrencias().add(ocorrencia);
		colaboradorRepository.save(colaborador);
		
	}

}
