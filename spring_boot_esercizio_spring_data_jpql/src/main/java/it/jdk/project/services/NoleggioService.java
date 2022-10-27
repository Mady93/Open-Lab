package it.jdk.project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.jdk.project.repositories.AutoRepository;
import it.jdk.project.repositories.ClientRepository;
import it.jdk.project.repositories.NoleggioRepository;


@Service
@Transactional(readOnly = true)
public class NoleggioService {
	private final AutoRepository autoRepository;
	private final ClientRepository clientRepository;
	private final NoleggioRepository repository;
	
	@Autowired
	public NoleggioService( AutoRepository autoRepository, ClientRepository clientRepository, NoleggioRepository repository) {
		this.autoRepository = autoRepository;
		this.clientRepository = clientRepository;
		this.repository = repository;
	}

}
