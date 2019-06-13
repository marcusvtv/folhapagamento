package edu.ifce.folhapagamento.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.ifce.folhapagamento.domain.Colaborador;
import edu.ifce.folhapagamento.domain.FolhaPagamento;
import edu.ifce.folhapagamento.repositories.ColaboradorRepository;
import edu.ifce.folhapagamento.repositories.FolhaPagamentoRepository;

@Controller
@RequestMapping(path="/aplicativo")
public class FolhaPagamentoController extends MainController {
	@Autowired
	private FolhaPagamentoRepository folhaPagamentoRepository;
	@GetMapping(path="/folhapagamento/add")
	 
		public  @ResponseBody String addNewFolhaPagamento (
				@RequestParam String mes, 
				@RequestParam String ano) {
				
		  	FolhaPagamento folhaPagamento = new FolhaPagamento();
			folhaPagamento.setMes(Integer.parseInt(mes));
			folhaPagamento.setAno(Integer.parseInt(ano));
			folhaPagamentoRepository.save(folhaPagamento);
			return "Dados Salvos Com Sucesso!";
		}
	
	@GetMapping(path="/folhapagamento/list/all")
	public @ResponseBody Iterable<FolhaPagamento> getAllFolhaPagamento() {
		// This returns a JSON or XML with the users
		return folhaPagamentoRepository.findAll();
	}
	
	@RequestMapping(path="/folhapagamento/delete")
    public @ResponseBody String removeFolhaPagamento(@RequestParam Integer id){
		try {
			folhaPagamentoRepository.deleteById(id);	
		}
		catch (Exception e) {
			// TODO: handle exception
			return "Não foi possível excluir a folha de Id: "+id+"\nMotivo: " + e.toString();
		}
       // return "redirect:/aplicativo/folhapagamento/list/all";
        return "Folha Excluída Com Sucesso!";
	
	}
	
	@RequestMapping(path="/folhapagamento/list")
    public @ResponseBody FolhaPagamento viewFolhaPagamento(@RequestParam Integer id){
		
		FolhaPagamento folha = new FolhaPagamento();
		try {
			folha = folhaPagamentoRepository.findById(id).get();				
		}catch (Exception e) {
			
		}
		return folha;
	}//término de viewFolhaPagamento
	
	@GetMapping(path="/folhapagamento/edit/incluicolaborador")
	 
	public  @ResponseBody String incluiColaboradorEmFolhaPagamento (
			@RequestParam Integer idFolha,
			@RequestParam Integer colaboradorId
			) {
			
	  	FolhaPagamento folhaPagamento = new FolhaPagamento();
	  	Colaborador colaborador = new Colaborador();
	  	colaborador = super.getColaboradorById(colaboradorId);
	  	try {
			folhaPagamento = folhaPagamentoRepository.findById(idFolha).get();
			folhaPagamento.getColaboradores().add(colaborador);
			folhaPagamentoRepository.save(folhaPagamento);
		}catch (Exception e) {
			return "Erro ao tentar incluir colaborador em folha.\nMotivo: "
					+ e.toString();
		}
		return "Dados Salvos Com Sucesso!";
	}
	
}//término da classe
