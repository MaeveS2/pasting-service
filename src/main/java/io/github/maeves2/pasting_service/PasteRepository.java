package io.github.maeves2.pasting_service;

import io.github.maeves2.pasting_service.model.Paste;
import org.springframework.data.repository.CrudRepository;

public interface PasteRepository extends CrudRepository<Paste, String> {
}
