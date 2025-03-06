package com.bookfinderai.controller;

import com.bookfinderai.model.Book;
import com.bookfinderai.service.BookService;
import com.bookfinderai.service.ChatGptService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class SuggestedBooksController {

    private final BookService service;
    private final ChatGptService chatGptService;

    @GetMapping("/generate")
    public Mono<ResponseEntity<String>> suggestBooks(){
        List<Book> bookList = service.getAll();
        return chatGptService.suggestBook(bookList)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.noContent().build());
    }

}
