package com.codeup.blog.controllers;

import com.codeup.blog.models.Roll;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
@SessionAttributes("rollList")
public class DiceController {

    @GetMapping("/roll-dice")
    public String landing() {
        return "roll-dice";
    }

    @GetMapping("/roll-dice/{guess}")
    public String guessResponse(@PathVariable String guess, Model model, @ModelAttribute ArrayList<Roll> guessResults) {
        model.addAttribute("attempt", true);
        if (guessResults != null) {
            int actual = randomGenerator();
            guessResults.add(new Roll(guess, actual));
        } else {
            guessResults = new ArrayList<>();
            int actual = randomGenerator();
            guessResults.add(new Roll(guess, actual));
        }

        int correct = 0;
        for (Roll attempt : guessResults) {
            if (attempt.getActual() == Integer.parseInt(attempt.getGuess())) {
                correct++;
            }
        }
        model.addAttribute("correct", correct);
        model.addAttribute("roll", guessResults);
        return "roll-dice";
    }

    private static int randomGenerator() {
        int min = 1;
        int max = 6;
        return (int) (Math.random() * (max - min + 1) + min);
    }

    @ModelAttribute("rollList")
    public ArrayList<Roll> rollList() {
        return new ArrayList<>();
    }
}
