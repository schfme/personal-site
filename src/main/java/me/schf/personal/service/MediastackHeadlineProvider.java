package me.schf.personal.service;
import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import me.schf.personal.config.AccessKeyProperties;

@Component
public class MediastackHeadlineProvider implements HeadlineProvider {

    private final WebClient webClient;
    private final String accessKey;

    public MediastackHeadlineProvider(WebClient.Builder webClientBuilder, AccessKeyProperties accessKeyProperties) {
        this.webClient = webClientBuilder.baseUrl("https://api.mediastack.com/v1/news").build();
        this.accessKey = accessKeyProperties.getMediastack();
    }

    @Override
    public List<String> getHeadlinesOn(LocalDate localDate) {
        String date = localDate.toString();

        NewsResponse response = webClient.get()
            .uri(uriBuilder -> uriBuilder
                .queryParam("access_key", accessKey)
                .queryParam("date", date)
                .queryParam("limit", 100)
                .build())
            .retrieve()
            .bodyToMono(NewsResponse.class)
            .block();

        return response != null ? response.data().stream().map(NewsArticle::title).toList() : List.of();
    }

    public record NewsArticle(String author, String title, String description, String url, String source, String image,
                              String category, String language, String country, String published_at) {}

    public record Pagination(int limit, int offset, int count, int total) {}

    public record NewsResponse(Pagination pagination, List<NewsArticle> data) {}
}
