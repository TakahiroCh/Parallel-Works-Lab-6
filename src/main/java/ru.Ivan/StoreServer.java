package ru.Ivan;

import java.util.ArrayList;
import java.util.List;

public class StoreServer {
    private List<String> servers;

    StoreServer(List<String> servers) {
        this.servers = servers;
    }

    public List<String> getServers() {
        return this.servers;
    }

}
