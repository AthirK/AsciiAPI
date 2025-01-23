package com.example.AsciiAPI.ascii;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Optional;

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

    private static class CreateAsciiDTO
    {
        public String artist;
        public LocalDate date;
        public String title;
        public String art;
    }

    @GetMapping("/search-by-title")
    public ResponseEntity<?> searchByTitle(@RequestParam String title) {
        try {
            Ascii ascii = asciiService.searchByTitle(title);
            return ResponseEntity.ok(ascii);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
