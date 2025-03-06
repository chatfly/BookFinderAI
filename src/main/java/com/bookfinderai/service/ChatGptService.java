package com.bookfinderai.service;

import com.bookfinderai.model.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChatGptService {

    private final WebClient webClient;

    @Value("${chatgpt.api.key}")
    private String apiKey;

    public Mono<String> suggestBook(List<Book> bookList){

        String books = bookList.stream()
                .map(book -> String.format("%s (%s) - Titulo do livro: %s, Nota: %.1f",
                        book.getTitulo(), book.getNota(), book.getTitulo(), book.getNota()))
                .collect(Collectors.joining("\n"));

        String prompt = "Baseando nos livros e nas notas maiores, sugira mais 2 opções de leitura que são similares e recomendadas em relação aos seguintes livros: " + books;

        Map<String, Object> requestBody = Map.of(
                "model", "gpt-4o-mini",
                "messages", List.of(
                        Map.of("role", "system", "content", "Você é um assistente que sugere livros em base de outros."),
                        Map.of("role", "user", "content", prompt)
                )
        );

        return webClient.post()
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + apiKey)
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(Map.class)
                .map(response -> {
                    var choices = (List<Map<String, Object>>) response.get("choices");
                    if(choices != null && !choices.isEmpty()){
                        Map<String, Object> message = (Map<String, Object>) choices.get(0).get("message");
                        return message.get("content").toString();
                    }
                    return "Nenhum livro foi recomendado";
                });

    }

}
