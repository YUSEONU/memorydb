package com.example.memorydb.book.controller;

import com.example.memorydb.book.db.entity.BookEntity;
import com.example.memorydb.book.service.BookService;
import com.example.memorydb.user.model.UserEntity;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/book")
@RequiredArgsConstructor
public class BookApiController {

    private final BookService bookService;


    @PostMapping("")
    public BookEntity create(
            @RequestBody BookEntity bookEntity
    ){
        return bookService.create(bookEntity);
    }

    //book 엔티티 전체 찾기
    @GetMapping("/all")
    public List<BookEntity> findAll(){
        return bookService.findAll();
    }

    @DeleteMapping("/id/{id}")
    public void delete(
            @PathVariable Long id
    ){
        bookService.delete(id);
    }

    //book 엔티티 개별 찾기
    @GetMapping("/id/{id}")
    public BookEntity findOne(
            @PathVariable Long id
    ){
        var response = bookService.findById(id);
        return response.get();
    }
}
