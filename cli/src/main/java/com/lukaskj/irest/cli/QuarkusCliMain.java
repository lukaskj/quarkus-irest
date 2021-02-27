package com.lukaskj.irest.cli;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.stream.Stream;
import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

@QuarkusMain
public class QuarkusCliMain implements QuarkusApplication {

    @Inject
    @Channel("params")
    Emitter<String> emitter;

    @Override
    public int run(String... args) throws Exception {
        Stream.of(args).forEach(s -> emitter.send(s));
        System.out.println("Quarkus CLI main " + Arrays.toString(args));
        Quarkus.waitForExit();
        return 0;
    }
}

// @QuarkusMain
// public class QuarkusCliMain {
// public static void main(String[] args) {
// Quarkus.run(Main2.class, args);
// }

// }


// class Main2 implements QuarkusApplication {
// @Override
// public int run(String... args) throws Exception {
// System.out.println("Quarkus CLI main " + Arrays.toString(args));
// Quarkus.waitForExit();
// return 0;
// }
// }
