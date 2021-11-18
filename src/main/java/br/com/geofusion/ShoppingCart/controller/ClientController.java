package br.com.geofusion.ShoppingCart.controller;

import java.util.List;

import br.com.geofusion.ShoppingCart.repository.ClientRepository;
import br.com.geofusion.ShoppingCart.exception.ClientNotFoundException;
import br.com.geofusion.ShoppingCart.model.Client;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
class ClientController {
        private final ClientRepository repository;

        ClientController(ClientRepository repository) {
                this.repository = repository;
        }

        // Aggregate root
        // tag::get-aggregate-root[]
        @GetMapping("/clients")
        List<Client> all() {
                return repository.findAll();
        }
        // end::get-aggregate-root[]

        @PostMapping("/clients")
        Client getNewClient(@RequestBody Client newClient) {
                return repository.save(newClient);
        }

        // Single item
        @GetMapping("/clients/{code}")
        Client one(@PathVariable Long code) {
                return repository.findById(code)
                        .orElseThrow(() -> new ClientNotFoundException(code));
        }

        @PutMapping("/clients/{code}")
        Client replaceClient(@RequestBody Client newClient, @PathVariable Long code) {
                return repository.findById(code)
                        .map(client -> {
                                client.setDescription(newClient.getDescription());
                                return repository.save(client);
                        })
                        .orElseGet(() -> {
                                newClient.setCode(code);
                                return repository.save(newClient);
                        });
        }

        @DeleteMapping("/clients/{code}")
        void deleteClient(@PathVariable Long code) {
                repository.deleteById(code);
        }
}