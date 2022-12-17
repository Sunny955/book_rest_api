package com.bootrestbook.restapi.controllers;


import com.bootrestbook.restapi.entities.Book;
import com.bootrestbook.restapi.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class BookController {

    @Autowired
    private BookService bookService;

    // Get all books handler
    @GetMapping("/books")
    public ResponseEntity<List<Book>> getBooks() {
        List<Book> books = this.bookService.getAllBooks();
        if(books.size()<=0) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(books);
    }

    // Get single book handler
    @GetMapping("books/{id}")
    public ResponseEntity<Book> getBook(@PathVariable("id") int id) {
        Book b = this.bookService.getBookById(id);
        if(b==null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.of(Optional.of(b));
    }

    // Create a single book handler
    @PostMapping("/books")
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        Book b = null;
        try{
            b = this.bookService.addBook(book);
            return ResponseEntity.of(Optional.of(b));
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Update book handler
    @PutMapping("/books/{bid}")
    public ResponseEntity<Book> updateBook(@RequestBody Book book,@PathVariable("bid") int bid) {
        Book b = null;

        try{
            b = this.bookService.updateBook(book,bid);
            if(b == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
            return ResponseEntity.of(Optional.of(b));
        }catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Delete book handler
    @DeleteMapping("/books/{id}")
    public ResponseEntity<Book> deleteBook(@PathVariable("id") int id) {
        Book b = this.bookService.deleteBook(id);
        if(b == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.of(Optional.of(b));
    }
}