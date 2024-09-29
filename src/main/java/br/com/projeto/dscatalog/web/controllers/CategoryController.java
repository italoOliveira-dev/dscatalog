package br.com.projeto.dscatalog.web.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.projeto.dscatalog.models.services.CategoryService;
import br.com.projeto.dscatalog.web.dto.CategoryCreateDTO;
import br.com.projeto.dscatalog.web.dto.CategoryResponseDTO;

@RestController
@RequestMapping("/categories")
public class CategoryController {

  @Autowired
  private CategoryService categoryService;

  @PostMapping
  public ResponseEntity<CategoryResponseDTO> insert(@RequestBody CategoryCreateDTO dto, UriComponentsBuilder builderURI) {
    CategoryResponseDTO category = categoryService.saveCategory(dto);
    URI uri = builderURI.path("/categories/{id}").buildAndExpand(category.id()).toUri();

    return ResponseEntity.created(uri).body(category);
  }

  @GetMapping("/{id}")
  public ResponseEntity<CategoryResponseDTO> findById(@PathVariable Long id) {
    CategoryResponseDTO category = categoryService.getCategory(id);

    return ResponseEntity.ok(category);
  }
  
  @GetMapping
  public ResponseEntity<List<CategoryResponseDTO>> findAll() {
    
    List<CategoryResponseDTO> list = categoryService.findAll();

    return ResponseEntity.ok(list);
  }
}
