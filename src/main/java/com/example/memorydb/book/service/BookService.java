package com.example.memorydb.book.service;


import com.example.memorydb.book.db.entity.BookEntity;
import com.example.memorydb.book.db.repository.BookRepository;
import com.example.memorydb.user.model.UserEntity;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    private final BookRepository bookRepository;

    //@RequiredArgsConstructor
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    //create, update
    public BookEntity create(BookEntity book) {
        return bookRepository.save(book);
    }

    //all
    public List<BookEntity> findAll() {
        return bookRepository.findAll();
    }

    //delete
    public void delete(BookEntity id) {bookRepository.delete(id);}

    //one
    public Optional<BookEntity> findById(Long id) {
        return bookRepository.findById(id);
    }
}
