package guru.springframework.spring5webapp.bootstrap;

import ch.qos.logback.core.net.SyslogOutputStream;
import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.origin.SystemEnvironmentOrigin;
import org.springframework.stereotype.Component;


@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository,
                         PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository=publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("In Bootstrap class");
        Publisher publisher = new Publisher();
        publisher.setName("SFG Publishing");
        publisher.setAddress_line_1("101");
        publisher.setCity("DPR");
        publisher.setState("RAJ");
        publisher.setZip("314001");
        publisherRepository.save(publisher);
        System.out.println("Publisher count:" + publisherRepository.count());

        Author eric = new Author("Eric","Evans");
        Book ddd= new Book("Domain Driven Design","4564515435");
       ddd.setPublisher(publisher);
        publisher.getBooks().add(ddd);

        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);

        authorRepository.save(eric);
        bookRepository.save(ddd);
        publisherRepository.save(publisher);

        Author rod =new Author("Rod","Johnson");
        Book noEJB =new Book("J2EE Devlopement Without EJB","2121545");

        rod.getBooks().add(noEJB);
        noEJB.getAuthors().add(rod);
        noEJB.setPublisher(publisher);
        publisher.getBooks().add(noEJB);
        authorRepository.save(rod);
        bookRepository.save(noEJB);
        publisherRepository.save(publisher);


        System.out.println("No. Of Book" +bookRepository.count());
        System.out.println("publisher books:"+publisher.getBooks().size());
    }
}
