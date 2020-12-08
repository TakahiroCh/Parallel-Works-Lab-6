package ru.Ivan;

import akka.actor.ActorRef;
import org.apache.zookeeper.ZooKeeper;

import java.nio.file.WatchEvent;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class ZooServer {
    final private static String SERVERS = "/servers";
    private ZooKeeper zoo;
    private ActorRef storage;

    public ZooServer(ZooKeeper zoo, ActorRef storage) {
        this.zoo = zoo;
        this.storage = storage;
    }

    private void watch(WatchEvent watchEvent) {
        System.out.println(watchEvent.toString());
        ArrayList<String> servers =
            this.zoo
                .getChildren(SERVERS,this)
                .stream(s -> SERVERS + "/" + s)
                .collect(Collectors.toList());
        this.storage.tell(new StorageActor());
    }

}
