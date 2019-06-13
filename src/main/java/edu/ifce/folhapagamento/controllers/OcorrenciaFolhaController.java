package edu.ifce.folhapagamento.controllers;

import edu.ifce.folhapagamento.controllers.ColaboradorController;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.ifce.folhapagamento.domain.Colaborador;
import edu.ifce.folhapagamento.domain.EnumTipoOcorrencia;
import edu.ifce.folhapagamento.domain.OcorrenciaFolha;
import edu.ifce.folhapagamento.repositories.ColaboradorRepository;
import edu.ifce.folhapagamento.repositories.OcorrenciaFolhaRepository;
import net.bytebuddy.implementation.bind.annotation.Super;

@Controller
@RequestMapping(path="/aplicativo")
public class OcorrenciaFolhaController extends MainController {
	@Autowired
	private OcorrenciaFolhaRepository ocorrenciaFolhaRepository;
	@GetMapping(path="/ocorrencias/add")
	 public @ResponseBody String addNewOcorrencia (
			 @RequestParam String descricao,
			 @RequestParam String valor,
			 @RequestParam Integer tipoOcorrencia,
			 @RequestParam Integer colaboradorId
			 ) {
		try {
			 Colaborador colaborador = new Colaborador();
			 colaborador = super.getColaboradorById(colaboradorId);
			 OcorrenciaFolha ocorrenciaFolha = new OcorrenciaFolha();
			 ocorrenciaFolha.setDescricao(descricao);
			 ocorrenciaFolha.setTipoOcorrencia(EnumTipoOcorrencia.valueOf(tipoOcorrencia));
			 ocorrenciaFolha.setValor(Float.parseFloat(valor));		 
			 ocorrenciaFolha.setColaborador(colaborador);
			 //super.setNewOcorrenciaInColaborador(ocorrenciaFolha, colaborador);
			 ocorrenciaFolhaRepository.save(ocorrenciaFolha);
		} catch (Exception e) {
			// TODO: handle exception
			return "Erro ao tentar incluir ocorrência.\nMotivo: "+ e.toString();
		}
	
		 
		 return "Ocorrência salva com sucesso";
	}
		
	 
	 


	@GetMapping(path="/ocorrencias/list/all")
		public @ResponseBody Iterable<OcorrenciaFolha> getAllOcorrencias() {
			// This returns a JSON or XML with the users
			return ocorrenciaFolhaRepository.findAll();
			
		}
	 
	 @GetMapping("/ocorrencias/list")
		public  @ResponseBody OcorrenciaFolha getOcorrencia(@RequestParam Integer idOcorrencia) {
			OcorrenciaFolha ocorrenciaFolha = new OcorrenciaFolha();
			try {
				ocorrenciaFolha = ocorrenciaFolhaRepository.findById(idOcorrencia).get();
			}catch (Exception e) {
				
			}
			return ocorrenciaFolha;
		}
	 
	 @RequestMapping(path="/ocorrencias/delete")
	    public @ResponseBody String removeOcorrencia(@RequestParam Integer id){
			
			ocorrenciaFolhaRepository.deleteById(id);
	        //return "redirect:/aplicativo/ocorrencias/list/all";
	        return "Ocorrência " +id+ " excluída com Sucesso!";
		
		}
	 
	 @GetMapping("/ocorrencias/edit")
	    @ResponseBody
	    public String editOcorrencia(@RequestParam Integer id, 
	    @RequestParam(value="descricao", required=false)  String descricao,
	    @RequestParam(value="valor", required=false)  String valor,
	    @RequestParam(value="tipoOcorrencia", required=false)  String tipoOcorrencia,
	    @RequestParam(value="colaboradorId", required=false)  Integer colaboradorId){
	    	 	    		 	   		 
	    		
		 try {	
			 
			 OcorrenciaFolha ocorrenciaFolha = ocorrenciaFolhaRepository.findById(id).get();
		     if (!(descricao == null)&&!descricao.isEmpty()) ocorrenciaFolha.setDescricao(descricao);
			 if (!(valor == null)&&!valor.isEmpty()) ocorrenciaFolha.setValor(Float.parseFloat(valor));
			 if (!(tipoOcorrencia == null)&& !tipoOcorrencia.isEmpty()) ocorrenciaFolha.setTipoOcorrencia(EnumTipoOcorrencia.valueOf(Integer.parseInt(tipoOcorrencia)));
			 if (!(colaboradorId == null)) {
				 Colaborador colaborador = super.getColaboradorById(colaboradorId);
				 ocorrenciaFolha.setColaborador(colaborador);
			 }
			 ocorrenciaFolhaRepository.save(ocorrenciaFolha);	    		 
	    }
	    catch (Exception ex) {
	    	return "Erro ao Atualizar o Registro: " + ex.toString();
	    }
		 return "Dados atualizados com sucesso";
	    }


}//final classe
