package br.com.samsung.security.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.samsung.security.user.controller.dto.UserDTO;
import br.com.samsung.security.user.model.User;
import br.com.samsung.security.user.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService compraService;

	
	@RequestMapping("{id}")
	public User getById(@PathVariable("id") Long id) {
		return compraService.getById(id);
	}
	
	

	@RequestMapping(method = RequestMethod.POST)
	public User realizaCompra(@RequestBody UserDTO compra) {
		return compraService.save(compra);
	}
	
	
}
