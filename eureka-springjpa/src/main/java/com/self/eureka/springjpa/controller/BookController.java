package com.self.eureka.springjpa.controller;

import com.self.eureka.springjpa.assembler.BookAssembler;
import com.self.eureka.springjpa.dto.BookDto;
import com.self.eureka.springjpa.entity.Book;
import com.self.eureka.springjpa.entity.Grade;
import com.self.eureka.springjpa.service.BookService;
import com.self.eureka.springjpa.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author xiaohe
 * @description:
 * @date 2020/3/17 17:19
 */
@RestController
@RequestMapping("book")
public class BookController {

    @Autowired
    private BookService bookService;
    @Autowired
    private BookAssembler bookAssembler;

    @PostMapping
    public String getGrade(@RequestBody Book book) {
        bookService.save(book);
        return "save success!";
    }

    @GetMapping("{bookId}")
    public BookDto getBook(@PathVariable Integer bookId) {
        return bookAssembler.toDto(bookService.get(bookId));
    }

    @DeleteMapping("{bookId}")
    public String delBook(@PathVariable Integer bookId) {
        bookService.del(bookId);
        return "save success!";
    }
}
