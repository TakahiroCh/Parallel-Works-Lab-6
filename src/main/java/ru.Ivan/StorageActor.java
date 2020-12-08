package ru.Ivan;

import akka.actor.AbstractActor;

import java.util.ArrayList;
import java.util.Random;

public class StorageActor extends AbstractActor {
    private ArrayList<String> servers;
    private Random random = new Random();


    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(StoreServer.class, s -> servers = s.getServers())
                .match()
    }
}
