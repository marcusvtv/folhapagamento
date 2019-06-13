package edu.ifce.folhapagamento.controllers;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mysql.cj.result.Field;

import edu.ifce.folhapagamento.domain.Colaborador;
import edu.ifce.folhapagamento.repositories.ColaboradorRepository;





	@Controller    // This means that this class is a Controller
	@RequestMapping(path="/aplicativo") // This means URL's start with /demo (after Application path)
	public class ColaboradorController extends MainController {
		private final String ITEM_URI = "aplicativo/";
	   @Autowired 	
	   private ColaboradorRepository colaboradorRepository;
	   @GetMapping(path="/colaboradores/add") // Map ONLY GET Requests
	   
		public  @ResponseBody String addNewColaborador (
				@RequestParam String nome, 
				@RequestParam String salarioAtual,
				@RequestParam String cpf,
				@RequestParam String endereco,
				@RequestParam String telefone,
				@RequestParam String bairro,
				@RequestParam String cep) {
			// @ResponseBody means the returned String is the response, not a view name
			// @RequestParam means it is a parameter from the GET or POST request
		  try {
			Colaborador colaborador = new Colaborador();
			colaborador.setNome(nome);
			colaborador.setSalarioAtual(Float.parseFloat(salarioAtual));
			colaborador.setCpf(cpf);
			colaborador.setEndereco(endereco);
			colaborador.setTelefone(telefone);
			colaborador.setBairro(bairro);
			colaborador.setCep(cep);
			colaboradorRepository.save(colaborador);
		  }catch (Exception e) {
			// TODO: handle exception
			  return "Erro ao tentar adicionar novo colaborador.\nMotivo: " + e.toString();
		}
			return "Dados Salvos Com Sucesso!";
		}
	  
	   
		@GetMapping(path="/colaboradores/list/all")
		public @ResponseBody Iterable<Colaborador> getAllColaboradores() {
			// This returns a JSON or XML with the users
			return colaboradorRepository.findAll();
		}
		
		//Opção de mapear com /id
		/* 
		@GetMapping("/colaboradores/list/{id}")
		public  @ResponseBody Colaborador viewColaborador(@PathVariable("id") Integer id)
		*/
		
		@GetMapping("/colaboradores/list")
		public  @ResponseBody Colaborador viewColaborador(@RequestParam Integer id) {
			Colaborador colaborador = new Colaborador();
			try {
				colaborador = super.getColaboradorById(id);				
			}catch (Exception e) {
				
			}
			return colaborador;
		}
				
					
		@RequestMapping(path="/colaboradores/delete")
	    public @ResponseBody String removeColaborador(@RequestParam Integer id){
			try {
			colaboradorRepository.deleteById(id);
	        //return "redirect:/aplicativo/colaboradores/list/all";
			} catch (Exception e) {
				return "Erro ao tentar excluir o colaborador.\nMotivo: " + e.toString();
			}
	        return "Colaborador Excluído Com Sucesso!";
		
		}
		
	    @GetMapping("/colaboradores/edit")
	    public @ResponseBody String editColaborador(@RequestParam Integer id, 
	    @RequestParam(value="nome", required=false)  String nome,
	    @RequestParam(value="salarioAtual", required=false)  String salarioAtual,
	    @RequestParam(value="cpf", required=false)  String cpf,
	    @RequestParam(value="endereco", required=false)  String endereco,
	    @RequestParam(value="telefone", required=false) String telefone, 
	    @RequestParam(value="bairro", required=false)String bairro, 
	    @RequestParam(value="cep", required=false)String cep){
	    	 	    		 	   		 
	    		
		 try {
			 Colaborador colaborador = new Colaborador();
			 colaborador = super.getColaboradorById(id);
		     if (!(nome == null)&&!nome.isEmpty()) colaborador.setNome(nome);
			 if (!(salarioAtual == null)&&!salarioAtual.isEmpty()) colaborador.setSalarioAtual(Float.parseFloat(salarioAtual));
			 if (!(cpf == null)&& !cpf.isEmpty()) colaborador.setCpf(cpf);
			 if (!(endereco == null)&& !endereco.isEmpty()) colaborador.setEndereco(endereco);
			 if (!(telefone == null)&& !telefone.isEmpty()) colaborador.setTelefone(telefone);
			 if (!(bairro == null)&& !bairro.isEmpty()) colaborador.setBairro(bairro);
			 if (!(cep == null) && !cep.isEmpty()) colaborador.setCep(cep);
		     colaboradorRepository.save(colaborador);	    		 
		 }
	    catch (Exception ex) {
	    	return "Erro ao Atualizar o Registro.\nMotivo: " + ex.toString();
	    }
		 return "Dados atualizados com sucesso";
	    }
}

		
	
	


