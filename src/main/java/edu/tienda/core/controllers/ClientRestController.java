package edu.tienda.core.controllers;

import edu.tienda.core.domain.Client;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class ClientRestController {

    /* Creamos una lista de prueba que emplea el rol de persistencia
        o almacén de clientes en nuestra API
    */
    private List<Client> clients = new ArrayList<>(Arrays.asList(
            new Client("arm", "1234", "Armstrong"),
            new Client("ald", "1234", "Aldrin"),
            new Client("col", "1234", "Collins")
        )
    );

    @GetMapping("/clients")
    public List<Client> getClients() {
        return clients;
    }

    @GetMapping("/clients/{userName}")
    public Client getClient(@PathVariable String userName) {
        return clients.stream().
                filter(client -> client.getUsername().equalsIgnoreCase(userName)).
                findFirst().orElseThrow();
    }

    @PostMapping("/clients")
    public Client addClient(@RequestBody Client client) {
        clients.add(client);
        return client;
    }

    @PutMapping("/clients")
    public Client updateClient(@RequestBody Client client) {

        Client foundClient = clients.stream().
                filter(cli -> cli.getUsername().equalsIgnoreCase(client.getUsername())).
                findFirst().orElseThrow();

        foundClient.setPassword(client.getPassword());
        foundClient.setName(client.getName());

        return foundClient;
    }

    @DeleteMapping("/clients/{userName}")
    public void deleteClient(@PathVariable String userName) {
        Client foundClient = clients.stream().
                filter(cli-> cli.getUsername().equalsIgnoreCase(userName)).
                findFirst().orElseThrow();
        clients.remove(foundClient);
    }

}
