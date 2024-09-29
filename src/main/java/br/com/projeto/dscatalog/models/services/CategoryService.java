package br.com.projeto.dscatalog.models.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.projeto.dscatalog.models.entities.Category;
import br.com.projeto.dscatalog.models.repositories.CategoryRepository;
import br.com.projeto.dscatalog.models.services.exceptions.EntityNotFoundException;
import br.com.projeto.dscatalog.web.dto.CategoryCreateDTO;
import br.com.projeto.dscatalog.web.dto.CategoryResponseDTO;
import br.com.projeto.dscatalog.web.dto.CategoryUpdateDTO;

@Service
public class CategoryService {
  
  @Autowired
  private CategoryRepository categoryRepository;

  @Transactional
  public CategoryResponseDTO saveCategory(CategoryCreateDTO CategoryDTO) {
    return CategoryResponseDTO.fromCategory(categoryRepository.save(CategoryDTO.toCategory()));
  }

  @Transactional(readOnly = true)
  public List<CategoryResponseDTO> findAll() {
    return categoryRepository.findAll().stream().map(CategoryResponseDTO::fromCategory).toList();
  }

  @Transactional(readOnly = true)
  private Category getById(Long id) {
    return categoryRepository
      .findById(id)
      .orElseThrow(() -> new EntityNotFoundException(String.format("Categoria com id %d não encontrada", id)));
  }

  public CategoryResponseDTO getCategory(Long id) {
    return CategoryResponseDTO.fromCategory(getById(id));
  }

  @Transactional
  public CategoryResponseDTO updateCategory(Long id, CategoryUpdateDTO dto) {
    Category entity = getById(id);
    entity.setName(dto.name());
    categoryRepository.save(entity);

    return CategoryResponseDTO.fromCategory(entity);
  }
}
