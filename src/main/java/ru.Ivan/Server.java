package ru.Ivan;

import akka.NotUsed;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.http.javadsl.Http;
import akka.http.javadsl.model.HttpRequest;
import akka.http.javadsl.model.HttpResponse;
import akka.http.javadsl.server.Route;
import akka.stream.ActorMaterializer;
import akka.stream.javadsl.Flow;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;

import static akka.http.javadsl.server.Directives.*;

public class Server {
    final private static String ZOO_HOST = "127.0.0.1:2181";
    final private static int TIME_OUT = 2500;
    final private static String LOCAL_HOST = "localhost";
    final private static String PORT = "8080";
    final private static String URL = "url";
    final private static String COUNT = "COUNT"

    public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
        ActorSystem system = ActorSystem.create("routes");
        ActorRef storage = system.actorOf(Props.create(StorageActor.class));
        final ActorMaterializer materializer = ActorMaterializer.create(system);
        Watcher empty = new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
            }
        };
        ZooKeeper zoo = new ZooKeeper(ZOO_HOST, TIME_OUT, empty);
        final Http http = Http.get(system);
        ZooServer server = new ZooServer(zoo, storage);
        server.createServer(LOCAL_HOST, PORT);

        final Flow<HttpRequest, HttpResponse, NotUsed> flow = createRoute()

    }

    private Route createRoute() {
        return route(pathSingleSlash(() -> {
            parameter(URL, url -> {
                parameter(COUNT, count -> {
                    check(new Request(url, count));
                })
            })
        }))
    }
}
