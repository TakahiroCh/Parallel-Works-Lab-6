package ru.Ivan;

import akka.actor.ActorRef;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.nio.file.WatchEvent;
import java.util.List;

public class ZooServer implements Watcher {
    final private static String SERVERS = "/servers";
    private ZooKeeper zoo;
    private ActorRef storage;

    public ZooServer(ZooKeeper zoo, ActorRef storage) {
        this.zoo = zoo;
        this.storage = storage;
    }


    private void sendServers() throws KeeperException, InterruptedException {
        List<String> servers = zoo.getChildren(SERVERS, this);
        storage.tell(new StorageActor(servers), ActorRef.noSender());
    }


    @Override
    public void process(WatchedEvent watchedEvent) {
        System.out.println(watchedEvent.toString());
        try {
            sendServers();
        } catch (KeeperException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
