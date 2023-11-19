package com.example.project_library.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;

@Entity
@Table(name = "book")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private Long bookId;


    @Column(name = "book_name")
    private String name;


    @Column(name = "book_author")
    private String author;
    
    @Column(name = "book_year_of_publish")
    private Long year;

    @ManyToOne
    @JoinColumn(name = "borrower_id")
    private Person borrower;

    @Override
    public String toString() {
        return "Book{" +
                "bookId=" + bookId +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", year=" + year +
                ", borrower=" + borrower +
                '}';
    }
}

