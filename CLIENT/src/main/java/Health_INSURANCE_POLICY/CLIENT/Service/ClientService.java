package Health_INSURANCE_POLICY.CLIENT.Service;

import Health_INSURANCE_POLICY.CLIENT.Entity.Client;
import Health_INSURANCE_POLICY.CLIENT.Repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    // Get all clients
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    // Get a client by ID
    public Optional<Client> getClientById(Integer clientId) {
        return clientRepository.findById(clientId);
    }

    // Add a new client
    public Client addClient(Client client) {
        return clientRepository.save(client);
    }

    // Update an existing client
    public Client updateClient(Integer clientId, Client updatedClient) {
        return clientRepository.findById(clientId).map(client -> {
            client.setFirstName(updatedClient.getFirstName());
            client.setLastName(updatedClient.getLastName());
            client.setEmail(updatedClient.getEmail());
            client.setPhoneNumber(updatedClient.getPhoneNumber());
            client.setAddress(updatedClient.getAddress());
            return clientRepository.save(client);
        }).orElseThrow(() -> new RuntimeException("Client not found with ID: " + clientId));
    }

    // Delete a client by ID
    public void deleteClient(Integer clientId) {
        clientRepository.deleteById(clientId);
    }
}
