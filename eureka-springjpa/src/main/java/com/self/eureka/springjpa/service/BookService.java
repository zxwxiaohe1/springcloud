package com.self.eureka.springjpa.service;

import com.self.eureka.springjpa.entity.Book;
import com.self.eureka.springjpa.repository.BookRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author xiaohe
 * @description:
 * @date 2020/7/28 9:30
 */
@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public Book get(@NonNull Integer stuId) {
        return bookRepository.getOne(stuId);
    }

    public void save(@NonNull Book book) {
        bookRepository.save(book);
    }

    public void del(@NonNull Integer stuId) {
        bookRepository.deleteById(stuId);
    }
}
