package com.github.cuzanzuca.crudeV3Application.controller;

import com.github.cuzanzuca.crudeV3Application.entity.Book;
import com.github.cuzanzuca.crudeV3Application.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class BookController
{
    @Autowired
    private BookRepository bookRepository ;

    @GetMapping("/getAllBok")
    public ResponseEntity<List<Book>> getAllBooks()
    {
        try
        {
            List<Book> bookList = new ArrayList<>();
            bookRepository.findAll().forEach(bookList::add);
            if(bookList.isEmpty())
            {
                return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(bookList,HttpStatus.OK);

        }catch (Exception exception)
        {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/getBookById/{id}")
    public ResponseEntity getBookById(@PathVariable Long id)
    {
        Optional<Book> optionalBook = bookRepository.findById(id);
        if(optionalBook.isPresent())
        {
            return new ResponseEntity<>(optionalBook.get(),HttpStatus.OK);
        }
        return  new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }
    @PostMapping("/addBook")
    public ResponseEntity<Book> addBook (@RequestBody Book book)
    {
        Book addBook = bookRepository.save(book);
        return new ResponseEntity<>(addBook,HttpStatus.OK);
    }

    @PostMapping("/updateBookById/{id}")
    public  ResponseEntity<Book>  updateBook(@PathVariable Long id, @RequestBody Book book)
    {
        Optional<Book> bookOptional = bookRepository.findById(id);
        if(bookOptional.isPresent())
        {
            Book updateBooks = bookOptional.get();
            updateBooks.setTitle(book.getTitle());
            updateBooks.setAuthor(book.getAuthor());

            Book bookObj = bookRepository.save(updateBooks);
            return  new ResponseEntity<>(bookObj,HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }


    @DeleteMapping("/deleteBookById/{id}")
    public ResponseEntity<HttpStatus> deleteBookById(@PathVariable Long id)
    {
         bookRepository.deleteById(id);
         return  new ResponseEntity<>(HttpStatus.OK);

    }
}
