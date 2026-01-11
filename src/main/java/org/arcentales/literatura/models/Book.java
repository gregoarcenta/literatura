package org.arcentales.literatura.models;

import jakarta.persistence.*;

@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String language;

    private Integer downloadCount;

    private String coverUrl;

    @ManyToOne(fetch = FetchType.EAGER)
    private Author author;

    public Book() {}

    public Book(String title, String language, Integer downloadCount, String coverUrl, Author author) {
        this.title = title;
        this.language = language;
        this.downloadCount = downloadCount;
        this.coverUrl = coverUrl;
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public Author getAuthor() {
        return author;
    }
}
