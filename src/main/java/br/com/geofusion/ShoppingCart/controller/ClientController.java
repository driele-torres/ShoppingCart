package br.com.geofusion.ShoppingCart.controller;

import java.util.List;

import br.com.geofusion.ShoppingCart.repository.ClientRepository;
import br.com.geofusion.ShoppingCart.exception.ClientNotFoundException;
import br.com.geofusion.ShoppingCart.model.Client;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/clients")
class ClientController {
        private final ClientRepository repository;

        ClientController(ClientRepository repository) {
                this.repository = repository;
        }

        @GetMapping()
        List<Client> all() {
                return repository.findAll();
        }

        @PostMapping()
        Client getNewClient(@RequestBody Client newClient) {
                return repository.save(newClient);
        }

        @GetMapping("/{clientId}")
        Client one(@PathVariable String clientId) {
                return repository.findById(clientId)
                        .orElseThrow(() -> new ClientNotFoundException(clientId));
        }

        @PutMapping("/{clientId}")
        Client replaceClient(@RequestBody Client newClient, @PathVariable String clientId) {
                return repository.findById(clientId)
                        .map(client -> {
                                client.setDescription(newClient.getDescription());
                                return repository.save(client);
                        })
                        .orElseGet(() -> {
                                newClient.setId(clientId);
                                return repository.save(newClient);
                        });
        }

        @DeleteMapping("/{clientId}")
        void deleteClient(@PathVariable String clientId) {
                repository.deleteById(clientId);
        }
}