package br.com.projeto.dscatalog.models.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.projeto.dscatalog.models.repositories.CategoryRepository;
import br.com.projeto.dscatalog.web.dto.CategoryResponseDTO;

@Service
public class CategoryService {
  
  @Autowired
  private CategoryRepository categoryRepository;

  @Transactional(readOnly = true)
  public List<CategoryResponseDTO> findAll() {
    return categoryRepository.findAll().stream().map(CategoryResponseDTO::fromCategory).toList();
  }
}
