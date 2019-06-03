package edu.ifce.folhapagamento.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.ifce.folhapagamento.domain.Colaborador;
import edu.ifce.folhapagamento.repositories.ColaboradorRepository;
import exemplo.User;




	@Controller    // This means that this class is a Controller
	@RequestMapping(path="/colaboradores") // This means URL's start with /demo (after Application path)
	public class ColaboradorController {
		
	   @Autowired // This means to get the bean called userRepository
		           // Which is auto-generated by Spring, we will use it to handle the data
	
		private ColaboradorRepository colaboradorRepository;
		
	   /*
		@GetMapping(path="/add") // Map ONLY GET Requests
		public @ResponseBody String addNewUser (@RequestParam String name
				, @RequestParam String email) {
			// @ResponseBody means the returned String is the response, not a view name
			// @RequestParam means it is a parameter from the GET or POST request
			
			User n = new User();
			n.setName(name);
			n.setEmail(email);
			userRepository.save(n);
			return "Saved";
		}
		*/
	   
		@GetMapping(path="/all")
		public @ResponseBody Iterable<Colaborador> getAllColaboradores() {
			// This returns a JSON or XML with the users
			return colaboradorRepository.findAll();
		}

	}

