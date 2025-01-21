package com.example.AsciiAPI.ascii;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Entity
@Data
@NoArgsConstructor
public class Ascii
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String artist;

    private LocalDate date;

    @Column(unique = true, nullable = false)
    private String title;

    private String art;

    public Ascii(String artist, LocalDate date, String title, String art)
    {
        this.artist = artist;
        this.date = date;
        this.title = title;
        this.art = art;
    }
}
