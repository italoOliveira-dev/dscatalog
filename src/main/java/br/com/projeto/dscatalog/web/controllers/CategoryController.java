package br.com.projeto.dscatalog.web.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.projeto.dscatalog.models.services.CategoryService;
import br.com.projeto.dscatalog.web.dto.CategoryResponseDTO;

@RestController
@RequestMapping("/categories")
public class CategoryController {

  @Autowired
  private CategoryService categoryService;
  
  @GetMapping
  public ResponseEntity<List<CategoryResponseDTO>> findAll() {
    
    List<CategoryResponseDTO> list = categoryService.findAll();

    return ResponseEntity.ok(list);
  }
}
