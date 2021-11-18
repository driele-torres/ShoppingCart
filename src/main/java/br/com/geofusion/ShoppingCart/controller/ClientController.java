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

        @GetMapping("/{code}")
        Client one(@PathVariable Long code) {
                return repository.findById(code)
                        .orElseThrow(() -> new ClientNotFoundException(code));
        }

        @PutMapping("/{code}")
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

        @DeleteMapping("/{code}")
        void deleteClient(@PathVariable Long code) {
                repository.deleteById(code);
        }
}