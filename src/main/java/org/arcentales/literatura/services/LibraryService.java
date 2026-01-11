package org.arcentales.literatura.services;

import org.arcentales.literatura.models.Author;
import org.arcentales.literatura.models.Book;
import org.arcentales.literatura.models.BookData;
import org.arcentales.literatura.repositories.AuthorRepository;
import org.arcentales.literatura.repositories.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibraryService {

    private final GutendexApiService gutendex;
    private final BookRepository booksRepository;
    private final AuthorRepository authorsRepository;

    public LibraryService(
            GutendexApiService gutendex,
            BookRepository books,
            AuthorRepository authors
    ) {
        this.gutendex = gutendex;
        this.booksRepository = books;
        this.authorsRepository = authors;
    }

    /* ---------- EXTERNO ---------- */
    public List<BookData> searchExternal(String query) {
        return gutendex.search(query);
    }

    /* ---------- GUARDADO ---------- */
    public Book saveFromGutendex(BookData data) {
        String authorName = data.authors().isEmpty()
                ? "Unknown"
                : data.authors().getFirst().name();

        Author author = authorsRepository
                .findByNameIgnoreCase(authorName)
                .orElseGet(() -> authorsRepository.save(new Author(authorName)));

//        String language = data.languages().isEmpty()
//                ? null
//                : data.languages().getFirst();

        String cover = data.formats().get("image/jpeg");

        Book book = new Book(
                data.title(),
                null,
                data.downloadCount(),
                cover,
                author
        );

        return booksRepository.save(book);
    }

    /* ---------- CONSULTAS INTERNAS ---------- */
    public List<Book> listMyBooks() {
        return booksRepository.findAll();
    }

    public List<Book> findBooksByAuthor(String name) {
        return booksRepository.findByAuthor_NameIgnoreCase(name);
    }

    public List<Book> findBooksByTitle(String title) {
        return booksRepository.findByTitleContainingIgnoreCase(title);
    }
}
