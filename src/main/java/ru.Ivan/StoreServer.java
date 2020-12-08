package ru.Ivan;

import java.util.ArrayList;

public class StoreServer {
    private ArrayList<String> servers;

    StoreServer(ArrayList<String> servers) {
        this.servers = servers;
    }

    public ArrayList<String> getServers() {
        return this.servers;
    }

}
