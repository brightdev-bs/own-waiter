package vanilla.ownwaiter.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/search")
@RequiredArgsConstructor
public class SearchController {

    @GetMapping
    public String moveToSearch() {
        return "/search/search";
    }

    @GetMapping("/restaurant")
    public String loadRestaurant(@RequestParam("input") String input, Model model) {
        System.out.println(input);
        return "/search/searchResult";
    }
}
