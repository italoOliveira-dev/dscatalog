package br.com.projeto.dscatalog.models.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.projeto.dscatalog.models.entities.Category;
import br.com.projeto.dscatalog.models.repositories.CategoryRepository;
import br.com.projeto.dscatalog.models.services.exceptions.EntityNotFoundException;
import br.com.projeto.dscatalog.web.dto.CategoryResponseDTO;

@Service
public class CategoryService {
  
  @Autowired
  private CategoryRepository categoryRepository;

  @Transactional(readOnly = true)
  public List<CategoryResponseDTO> findAll() {
    return categoryRepository.findAll().stream().map(CategoryResponseDTO::fromCategory).toList();
  }

  @Transactional(readOnly = true)
  public Category getById(Long id) {
    return categoryRepository
      .findById(id)
      .orElseThrow(() -> new EntityNotFoundException(String.format("Categoria com id %d não encontrada", id)));
  }

  public CategoryResponseDTO getCategory(Long id) {
    return CategoryResponseDTO.fromCategory(getById(id));
  }
}
