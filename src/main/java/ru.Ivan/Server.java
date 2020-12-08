package ru.Ivan;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.stream.ActorMaterializer;
import org.apache.zookeeper.ZooKeeper;

public class Server {
    final private static String ZOO_HOST = "127.0.0.1:2181";
    final private static int TIME_OUT = 2500;

    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create("routes");
        ActorRef storage = system.actorOf(Props.create(StorageActor.class));
        final ActorMaterializer materializer = ActorMaterializer.create(system);
        ZooKeeper zoo = new ZooKeeper(ZOO_HOST, TIME_OUT, )

    }
}
