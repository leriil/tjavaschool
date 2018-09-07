package com.tsystems.tshop.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tsystems.tshop.domain.Client;
import com.tsystems.tshop.services.ClientService;

@Controller
@RequestMapping("/api/clients")
public class ClientController {

	private final ClientService clientService;

	public ClientController(final ClientService clientService) {
		this.clientService = clientService;
	}

	@RequestMapping(method = RequestMethod.GET, path = "/{id}")
	@ResponseBody
	public Client getClient(@PathVariable Long id) {
		return clientService.getClientById(id);
	}
	
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<Client> getClients() {
		return clientService.getClients();
	}

}
