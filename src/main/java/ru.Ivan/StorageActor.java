package ru.Ivan;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;

import java.util.ArrayList;
import java.util.Random;

public class StorageActor extends AbstractActor {
    private ArrayList<String> servers;
    private Random random = new Random();


    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(StoreServer.class, mes -> this.servers = mes.getServers())
                .match(NextServer.class, mes -> {
                    getSender().tell(this.getRandomServer(), ActorRef.noSender());
                })
                .match(DeleteServer.class, mes -> this.servers.remove(mes.getServer()))
                .build();
    }

    private String getRandomServer() {
        return this.servers.get(random.nextInt(servers.size()));
    }
}
