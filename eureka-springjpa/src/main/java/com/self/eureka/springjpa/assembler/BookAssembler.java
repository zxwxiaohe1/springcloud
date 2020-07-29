package com.self.eureka.springjpa.assembler;

import com.self.eureka.springjpa.dto.BookDto;
import com.self.eureka.springjpa.dto.StudentDto;
import com.self.eureka.springjpa.entity.Book;
import com.self.eureka.springjpa.entity.Student;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xiaohe
 * @description:
 * @date 2020/7/29 9:51
 */
@Component
public class BookAssembler implements DTOAssembler<Book, BookDto> {

    @Override
    public Book toEntity(BookDto bookDto) {
        return null;
    }

    @Override
    public BookDto toDto(Book book) {

        if (ObjectUtils.isEmpty(book)) {
            return null;
        }
        BookDto bookDto = new BookDto();
        bookDto.setId(book.getId());
        bookDto.setName(book.getName());
        bookDto.setPrice(book.getPrice());
        if (!ObjectUtils.isEmpty(book.getStudents())) {
            List<StudentDto> stus = new ArrayList<>();
            book.getStudents().stream().forEach(e -> {
                StudentDto studentDto = new StudentDto();
                studentDto.setId(e.getId());
                studentDto.setName(e.getName());
                studentDto.setAge(e.getAge());
                stus.add(studentDto);
            });
            bookDto.setStudents(stus);
        }
        return bookDto;
    }
}
