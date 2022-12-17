package com.bootrestbook.restapi.services;

import com.bootrestbook.restapi.dao.BookRepository;
import com.bootrestbook.restapi.entities.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    // Get all books
    public List<Book> getAllBooks() {
        List<Book> list = (List<Book>) this.bookRepository.findAll();
        return list;
    }

    // Get single book by id
    public Book getBookById(int id) {
        Book b = null;
        try {
            b = this.bookRepository.findById(id);
        }catch (Exception e){
            e.printStackTrace();
        }
        return b;
    }

    // Adding new book
    public Book addBook(Book book) {
        Book b = this.bookRepository.save(book);
        return b;
    }

    // Updating a book by id
    public Book updateBook(Book book,int id) {
        Book b = null;
        try{
            b = this.bookRepository.findById(id);
            b.setAuthor(book.getAuthor());
            b.setTitle(book.getTitle());
            this.bookRepository.save(b);
        }catch(Exception e){
            e.printStackTrace();
        }
        return b;
    }

    // Deleting a book by id
    public Book deleteBook(int id) {
        Book b = null;
        try{
            b = this.bookRepository.findById(id);
        }catch(Exception e){
            e.printStackTrace();
        }
        this.bookRepository.deleteById(id);
        return b;
    }
}