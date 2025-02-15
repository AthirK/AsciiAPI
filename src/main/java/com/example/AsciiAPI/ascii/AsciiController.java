package com.example.AsciiAPI.ascii;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
public class AsciiController
{
    private final AsciiService asciiService;

    @PostMapping("/create-ascii")
    public ResponseEntity<?> createAscii(@RequestBody CreateAsciiDTO dto)
    {
        try
        {
            Ascii ascii = asciiService.createAscii(
                    dto.artist,
                    dto.date,
                    dto.title,
                    dto.art);

            return ResponseEntity.ok(ascii);
        }
        catch (Exception e)
        {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    private static class CreateAsciiDTO
    {
        public String artist;
        public LocalDate date;
        public String title;
        public String art;
    }

    // delete ascii
    @DeleteMapping("/{title}")
    public ResponseEntity<?> deleteAscii(@PathVariable String title)
    {
        try
        {
            asciiService.deleteAscii(title);
            return ResponseEntity.ok(title + " deleted.");
        }
        catch (Exception e)
        {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/view-asciis")
    public ResponseEntity<?> viewAsciis()
    {
        return ResponseEntity.ok(asciiService.viewAsciis());
    }


    @GetMapping("/search-by-title")
    public ResponseEntity<?> searchByTitle(@RequestParam String title) {
        try {
            Ascii ascii = asciiService.searchByTitle(title);

            // Bygg HTML-svaret dynamiskt med ASCII-konst och radbrytningar
            String htmlContent = "<!doctype html>\n" +
                    "<html>\n" +
                    "  <head><title>ASCII Art</title></head>\n" +
                    "  <body>\n" +
                    "    <h1>" + ascii.getTitle() + " (" + ascii.getArtist() + ")</h1>\n" +
                    "    <p><strong>Datum:</strong> " + ascii.getDate() + "</p>\n" +
                    "    <pre>" + ascii.getArt().replace("\n", "<br>") + "</pre>\n" +
                    "  </body>\n" +
                    "</html>";

            return ResponseEntity.ok()
                    .contentType(MediaType.TEXT_HTML)
                    .body(htmlContent);

        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    // Endpoint to generate and save sample Ascii artworks
    @PostMapping("/ascii-samples")
    public ResponseEntity<?> generateAsciiSamples()
    {
        try
        {
            return ResponseEntity.ok(asciiService.generateAsciiSamples());
        }
        catch (Exception e)
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error generating samples.");
        }
    }
}