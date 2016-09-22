package Server;

import Interface.Client_Agreement;

/**
 * Created by Scalman on 22/09/16.
 */
public class ClientInstance {

    public Client_Agreement client;
    public String user_name;

    public ClientInstance(Client_Agreement client, String user_name) {
        this.client = client;
        this.user_name = user_name;
    }
}
