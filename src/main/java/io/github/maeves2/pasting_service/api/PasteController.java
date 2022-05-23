package io.github.maeves2.pasting_service.api;

import io.github.maeves2.pasting_service.PasteRepository;
import io.github.maeves2.pasting_service.model.Paste;
import io.github.maeves2.pasting_service.model.PastePayload;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.UUID;

@RestController
public class PasteController {
    private PasteRepository repo;

    public PasteController(PasteRepository repo) {
        this.repo = repo;
    }

    @PostMapping("/paste")
    Paste postPaste(@RequestBody PastePayload payload) {
        var title = payload.title().equals("") ? "Untitled" : payload.title();
        var paste = new Paste(UUID.randomUUID().toString(), title, payload.text(),
                Instant.now(), payload.text().getBytes().length, payload.language());
        this.repo.save(paste);
        System.out.println(paste.getId());
        return paste;
    }
}
