package ru.Ivan;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

public class Server {
    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create("routes");
        ActorRef storage = system.actorOf(Props.create(StorageActor.class));
    }
}
