package com.bookfinderai.service;

import com.bookfinderai.model.Book;
import com.bookfinderai.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository repository;

    public Book save(Book book) {
        return repository.save(book);
    }

    public List<Book> getAll() {
        return repository.findAll();
    }

    public Book findById(Long id) {
        Optional<Book> book = repository.findById(id);
        return book.orElse(null);
    }

    public Book update(Long id, Book newBook){
        Optional<Book> book = repository.findById(id);
        if(book.isPresent()){
            newBook.setId(book.get().getId());
            return repository.save(newBook);
        }
        return null;
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

}
