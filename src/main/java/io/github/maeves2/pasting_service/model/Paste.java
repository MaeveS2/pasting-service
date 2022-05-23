package io.github.maeves2.pasting_service.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.Instant;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Paste {
    @Id
    private String id;
    private String title;
    @Column(length = 1000000) private String text;
    private Instant timestamp;
    private int size;
    private String language;

    public Paste(String title, String text, String language) {
        this.title = title;
        this.text = text;
        this.timestamp = Instant.now();
        this.size = text.getBytes().length;
        this.language = language;
    }

    public Paste(String id, String title, String text, Instant timestamp, String language) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.timestamp = timestamp;
        this.size = text.getBytes().length;
        this.language = language;
    }
}
