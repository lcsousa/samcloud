package br.com.samsung.security.user.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import br.com.samsung.security.user.controller.dto.UserDTO;
import br.com.samsung.security.user.model.User;
import br.com.samsung.security.user.repository.UserRepository;

@Service
public class UserService {
	/*
	 * @Autowired private RestTemplate client;
	 */
	private static final Logger LOG = LoggerFactory.getLogger(UserService.class);
	
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private DiscoveryClient eurekaClient;
	
	@HystrixCommand(fallbackMethod = "getByIdFallback",threadPoolKey ="getByIdThreadPool") 
	public User getById(Long id) {
		LOG.info(">>>>>>>>>>>>>>>>>Inicio getById");
		String a = null;
		a.toString();
		
		listClient();
		LOG.info(">>>>>>>>>>>>>>>>>Fim getById");
		return userRepository.findById(id).orElse(null);
	}
	
	public User getByIdFallback(Long id) {
		LOG.info(">>>>>>>>>>>>>>>>>Inicio getByIdFallback");
		listClient();
		LOG.info(">>>>>>>>>>>>>>>>>Fim getByIdFallback");
		return userRepository.findById(id).orElse(null);
	}
	
	@HystrixCommand(fallbackMethod = "saveFallback",threadPoolKey ="realizaCompraThreadPool" )
	public User save(@RequestBody UserDTO userDTO) {
		User user = new User();
		user.setId(userDTO.getId());
		user.setName(userDTO.getName());
		user.setUsername(userDTO.getUsername());
		user.setPassword(userDTO.getPassword());
		userRepository.save(user);
		
	    return user;
	}
	
	public User saveFallback(@RequestBody UserDTO compra) {
		
		
		
		return null;
	}
	
	public void listClient() {
		
		eurekaClient.getInstances("security").stream().
		forEach(obj -> {
			LOG.info("Host:"+obj.getHost()+" -Port:"+obj.getPort());
			
		});
		
	}

	

}
