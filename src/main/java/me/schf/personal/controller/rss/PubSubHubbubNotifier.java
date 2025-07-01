package me.schf.personal.controller.rss;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class PubSubHubbubNotifier {

    private static final Logger logger = LoggerFactory.getLogger(PubSubHubbubNotifier.class);
    private static final String HUB_URL = "https://pubsubhubbub.appspot.com/";
    private static final String TOPIC_URL = "https://schf.me/posts/rss";

    private final WebClient webClient = WebClient.create();

    public void notifyHub() {
        webClient.post()
            .uri(HUB_URL)
            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
            .bodyValue("hub.mode=publish&hub.url=" + TOPIC_URL)
            .retrieve()
            .toBodilessEntity()
            .doOnSuccess(response -> logger.info("Successfully notified PubSubHubbub hub."))
            .doOnError(error -> logger.error("Failed to notify PubSubHubbub hub.", error))
            .subscribe();
    }
}
