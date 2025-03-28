package edu.epam.fop.mocks;

import edu.epam.fop.mocks.client.ClientRepository;
import edu.epam.fop.mocks.client.ClientResponse;
import org.mockito.Mockito;
import org.mockito.stubbing.Answer;

public final class ClientService {

    private final ClientRepository client;

    public ClientService(ClientRepository client) {
        this.client = client;


        Mockito.when(client.findById(Mockito.anyLong())).thenAnswer((Answer<ClientResponse>) invocation -> {
            long id = invocation.getArgument(0);
            long definedId = client.definedId();

            if (id == definedId) {
                if (definedId % 2 == 1) return new ClientResponse(definedId, "Lou", "Tenat");
                else {
                    return new ClientResponse(definedId, "Louisa", "Rodriguez");
                }
            }
            return null;
        });
    }

    public ClientResponse search(long id) {
        return client.findById(id);
    }
}
