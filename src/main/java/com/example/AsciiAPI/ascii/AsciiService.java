package com.example.AsciiAPI.ascii;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AsciiService
{
    private final AsciiRepository asciiRepository;

    /**
     * Creates an Ascii object with some parameters.
     *
     * @param artist the artist who made the Ascii-art
     * @param date the date when the art was uploaded to database.
     * @param title the name of the Ascii-art
     * @param art Ascii-art
     * @return ascii object with JSON body with all the above params.
     */
    public Ascii createAscii(
            String artist,
            LocalDate date,
            String title,
            String art
    )
    {
        if (title == null || title.isBlank())
        {
            throw new IllegalArgumentException("Title may not be null or empty");
        }

        Ascii ascii = new Ascii(artist, date, title, art);

        asciiRepository.save(ascii);

        return ascii;
    }

    /**
     * This method deletes an Ascii-art based on the title from the database.
     *
     * @param title the title of the Ascii-art being deleted
     */
    public void deleteAscii(String title)
    {
        Ascii ascii = asciiRepository.findByTitle(title).orElseThrow(() -> new IllegalArgumentException(title + " not found"));
        asciiRepository.delete(ascii);
    }

    /**
     * A method that displays all Ascii-artwork in the database.
     *
     * @return returns all Ascii-artworks
     */
    public List<Ascii> viewAsciis()
    {
        return asciiRepository.findAll();
    }
}
