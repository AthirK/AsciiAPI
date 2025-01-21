package com.example.AsciiAPI.ascii;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class AsciiService
{
    private final AsciiRepository asciiRepository;

    public Ascii createAscii(
            String artist,
            LocalDate date,
            String title,
            String art
    ) {
        if (title == null || title.isBlank()) {
            // Undvik att använda prints i services (men även controllers och repositories)
            // Använd endast prints i debug syfte
            // System.out.println("Title may not be null");
            throw new IllegalArgumentException("Title may not be null or empty");
        }

        Ascii ascii = new Ascii(artist, date, title, art);

        asciiRepository.save(ascii);

        return ascii;
    }
}
