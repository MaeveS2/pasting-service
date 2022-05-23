package io.github.maeves2.pasting_service.view;

import io.github.maeves2.pasting_service.PasteRepository;
import io.github.maeves2.pasting_service.model.Paste;
import io.github.maeves2.pasting_service.util.Utilities;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Comparator;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


@Controller
public class ThymeleafController implements ErrorController {
    private PasteRepository repo;

    public ThymeleafController(PasteRepository repo) {
        this.repo = repo;
        Utilities.initTestPastes(this.repo);
    }

    @GetMapping("/paste/{id}")
    public String getPaste(Model model, @PathVariable String id) {
        if (this.repo.findById(id).isEmpty()) {
            model.addAttribute("code", "404");
            model.addAttribute("message", "Page not found (paste does not exist)");
            return "error";
        }
        var paste = this.repo.findById(id).get();
        model.addAttribute("title", id);
        model.addAttribute("title", paste.getTitle());
        model.addAttribute("text", paste.getText());
        model.addAttribute("size", String.valueOf((float) paste.getSize()/1024).substring(0, 4) + "KB");
        model.addAttribute("lang", paste.getLanguage());

        model.addAttribute("time", Utilities.humanReadableDate(paste.getTimestamp()));
        return "paste";
    }

    @GetMapping("/latest")
    public String getLatest(Model model) {
        model.addAttribute("amount", this.repo.count());

        var latest = StreamSupport.stream(this.repo.findAll().spliterator(), false)
                .sorted(Comparator.comparing(Paste::getTimestamp).reversed())
                .collect(Collectors.toList());
        model.addAttribute("latest", latest);

        return "latest";
    }

    // error handling
    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, Model model) {
        var code = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE).toString();
        var message = request.getAttribute(RequestDispatcher.ERROR_MESSAGE).toString();
        model.addAttribute("code", code);
        model.addAttribute("message", message);
        return "error";
    }
}
