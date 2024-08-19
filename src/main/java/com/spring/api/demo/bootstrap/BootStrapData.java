package com.spring.api.demo.bootstrap;

import com.spring.api.demo.model.Author;
import com.spring.api.demo.model.Book;
import com.spring.api.demo.model.Publisher;
import com.spring.api.demo.repositories.AuthorRepository;
import com.spring.api.demo.repositories.BookRepository;
import com.spring.api.demo.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    private final PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository,PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        //Crear un autor
        Author erick = new Author("Erick","Garita");
        //Crear un nuevo libro
        Book principito = new Book("El Principito", "1q7rqewq7392");
        //crear una editorial
        Publisher santillana = new Publisher("Santillana", "Addres line 1", "San Jose", "Costa Rica","50190");

        //asignar un libro a un autor
        erick.getBooks().add(principito);
        principito.getAuthor().add(erick);
        santillana.getBooks().add(principito);


        authorRepository.save(erick);
        bookRepository.save(principito);
        publisherRepository.save(santillana);

        principito.setPublisher(santillana);




        Author edgar = new Author("Edgar", "Salas");
        Book alas_de_sangre =  new Book("Alas de Sangre", "213j2o030s12");
        edgar.getBooks().add(alas_de_sangre);
        alas_de_sangre.getAuthor().add(edgar);
        alas_de_sangre.setPublisher(santillana);
        santillana.getBooks().add(alas_de_sangre);

        authorRepository.save(edgar);
        bookRepository.save(alas_de_sangre);

        principito.getAuthor().add(edgar);






        System.out.println("Started in Bootstrap");
        System.out.println("Number of books: "+bookRepository.count());
        System.out.println("Number of books of "+santillana.getName()+": "+ santillana.getBooks().size());
        System.out.println("=============================");
        System.out.println("Book Name: "+ principito.getTitle() +'\n'+"Publisher: "+ principito.getPublisher().getName()+'\n'+ "Author: ");
        System.out.println("Authors: ");
        for (Author author : principito.getAuthor()) {
            System.out.println("-"+author.getFirstName() + " " + author.getLastName());
        }
        System.out.println("=============================");

    }
}
