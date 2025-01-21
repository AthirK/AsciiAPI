package com.example.AsciiAPI.exersics;

import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

// exercise 1.1
@RestController
public class TestController
{
    // exercise 1.2
    @GetMapping("/exercise1")
    public ResponseEntity<String> exercise1()
    {
        return ResponseEntity.ok("Hello world");
    }

    //exercise 2
    @GetMapping("/exercise2")
    public ResponseEntity<?> exercise2()
    {
        return ResponseEntity.ok(new Exercise2DTO("Hello World!"));
    }

    @AllArgsConstructor
    public static class Exercise2DTO
    {
        public String message;
    }

    //exercise 3
    @GetMapping("/exercise3")
    public ResponseEntity<?> exercise3()
    {
        return ResponseEntity
                .ok()
                .contentType(MediaType.TEXT_HTML)
                .body("<!doctype html>\n" +
                        "<html>\n" +
                        "  <head></head>\n" +
                        "  <body>\n" +
                        "    <h1>Welcome!</h1>\n" +
                        "    <script>\n" +
                        "      alert(\"Hello World!\");\n" +
                        "    </script>\n" +
                        "  </body>\n" +
                        "</html>");
    }

    //exercise 4
    @GetMapping("/exercise4/{name}")
    public ResponseEntity<?> exercise4(@PathVariable String name)
    {
        return ResponseEntity.ok("Hello, " + name + "!");
    }

    //exercise 5
    @GetMapping("/exercise5/{name}")
    public ResponseEntity<?> exercise5(@PathVariable String name)
    {
        return ResponseEntity.ok(new Exercise5DTO("Hello, " + name + "!"));
    }

    @AllArgsConstructor
    public static class Exercise5DTO
    {
        public String message;
    }

    //exercise 6
    @GetMapping("/exercise6")
    public ResponseEntity<?> exercise6(@RequestParam int a, @RequestParam int b)
    {
        return ResponseEntity.ok(new Exercise6DTO(new int[]{a, b}, a + b));
    }

    @AllArgsConstructor
    public static class Exercise6DTO
    {
        public int[] numbers;
        public int sum;
    }

    //exercise 7
    @GetMapping("/exercise7")
    public ResponseEntity<?> exercise7(@RequestBody Exercise7InputDTO inputDTO)
    {
        int result = switch (inputDTO.operation)
        {
            case ADDITION -> inputDTO.firstNumber + inputDTO.secondNumber;
            case SUBTRACTION -> inputDTO.firstNumber - inputDTO.secondNumber;
            case MULTIPLICATION -> inputDTO.firstNumber * inputDTO.secondNumber;
            case DIVISION -> inputDTO.firstNumber / inputDTO.secondNumber;
        };
        return ResponseEntity.ok(new Exercise7OutputDTO(inputDTO.operation, inputDTO.firstNumber, inputDTO.secondNumber, result));
    }

    @AllArgsConstructor
    public static class Exercise7InputDTO
    {
        public Exercise7Operation operation;
        public int firstNumber;
        public int secondNumber;
    }

    @AllArgsConstructor
    public static class Exercise7OutputDTO
    {
        public Exercise7Operation operation;
        public int firstNumber;
        public int secondNumber;
        public int result;
    }

    public enum Exercise7Operation
    {
        ADDITION,
        SUBTRACTION,
        MULTIPLICATION,
        DIVISION,
    }

    // Övning 8
    private int exercise8Counter = 0;

    @GetMapping("/exercise8")
    public ResponseEntity<?> exercise8()
    {
        return ResponseEntity.ok(exercise8Counter++);
    }

    // Övning 9
    private List<Exercise9DTO> exercise9Inputs = new ArrayList<>();

    @GetMapping("/exercise9/{input}")
    public ResponseEntity<?> exercise9(@PathVariable String input)
    {
        exercise9Inputs.add(new Exercise9DTO(input));
        return ResponseEntity.ok(exercise9Inputs);
    }

    @AllArgsConstructor
    public static class Exercise9DTO
    {
        public String input;
    }
}