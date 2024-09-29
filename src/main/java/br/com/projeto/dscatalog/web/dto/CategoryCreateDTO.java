package br.com.projeto.dscatalog.web.dto;

import br.com.projeto.dscatalog.models.entities.Category;

public record CategoryCreateDTO(String name) {
  public Category toCategory() {
    return new Category(name);
  }
}
