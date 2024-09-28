package br.com.projeto.dscatalog.web.dto;

import br.com.projeto.dscatalog.models.entities.Category;

public record CategoryResponseDTO(Long id, String name) {
  public static CategoryResponseDTO fromCategory(Category category) {
    return new CategoryResponseDTO(category.getId(), category.getName());
  }
}
