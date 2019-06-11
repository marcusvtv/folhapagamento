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
@RequestMapping(path="/folhapagamento")
public class FolhaPagamentoController {
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
	
	@RequestMapping(path="/folhapagamento/delete/{id}")
    public String removeFolhaPagamento(@PathVariable("id") Integer id){
		
		folhaPagamentoRepository.deleteById(id);
        return "redirect:/folhapagamento/folhapagamento/list/all";
        //return "Dados Excluidos Com Sucesso!";
	
	}
	
	@RequestMapping(path="/folhapagamento/list/{id}")
    public @ResponseBody FolhaPagamento viewFolhaPagamento(@PathVariable("id") Integer id){
		
		FolhaPagamento folha = new FolhaPagamento();
		try {
			folha = folhaPagamentoRepository.findById(id).get();				
		}catch (Exception e) {
			
		}
		return folha;
	}//término de viewFolhaPagamento
	
}//término da classe
