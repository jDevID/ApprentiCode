package dev.id.backend.web.controllers;


import dev.id.backend.logic.exceptions.EntityDeleteException;
import dev.id.backend.logic.exceptions.EntityUpdateException;
import dev.id.backend.logic.mappers.BaseMapper;
import dev.id.backend.logic.services.BaseService;
import dev.id.backend.logic.utils.ResponseUtil;
import dev.id.backend.web.dtos.ResponseDto;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Map;


/*
*    create - Create a new entity.
*    getById - Retrieve an entity by its ID.
*    search - Search for entities using filter and pagination (GET method).
*    search - Search for entities using filter and pagination (POST method).
*    update - Update an existing entity.
*    delete - Delete an entity by its ID.
*    getAll - Fetch all entities.
 */
@RestController
@RequestMapping(path="/api/v1")
public abstract class BaseController<T, DTO, ID extends Serializable> {
    protected final BaseService<T, DTO, ID> service;
    protected final BaseMapper<DTO, T> mapper;

    public BaseController(BaseService<T, DTO, ID> service, BaseMapper<DTO, T> mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping("/")
    public ResponseEntity<Page<DTO>> getAll(
            @RequestParam(value = "page", required = false, defaultValue = "0") int page,
            @RequestParam(value = "size", required = false, defaultValue = "10") int size,
            @RequestParam(value = "sort", required = false) String sort) {
        Pageable pageable = PageRequest.of(page, size, sort != null ? Sort.by(sort) : Sort.unsorted());
        Page<T> pageResult = service.findAll(pageable);
        Page<DTO> dtoPage = pageResult.map(mapper::toDTO);
        return ResponseEntity.ok(dtoPage);
    }

    @PostMapping("/")
    public ResponseEntity<ResponseDto<DTO>> create(@RequestBody @Valid DTO dto) throws IOException {
        DTO createdDto = service.create(dto);
        ResponseDto<DTO> responseDto = ResponseUtil.buildResponse(LocalDateTime.now(), HttpStatus.CREATED, "Entity created", null, createdDto);        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto<DTO>> getById(@PathVariable ID id) {
        return service.findById(id)
                .map(entity -> {
                    DTO dto = mapper.toDTO(entity);
                    ResponseDto<DTO> response = ResponseUtil.buildResponse(LocalDateTime.now(), HttpStatus.OK, "Entity fetched successfully", null, dto);
                    return ResponseEntity.ok(response);
                })
                .orElseThrow(() -> new EntityNotFoundException("Entity not found with ID: " + id));
    }


    // GET /api/v1/yourControllerPath/search?filter=fieldName:fieldValue&page=0&size=10&sort=fieldName,asc
    @GetMapping("/search")
    public ResponseEntity<Page<DTO>> search(
            @RequestParam(value = "filter", required = false, defaultValue = "") String filterString,
            @RequestParam(value = "page", required = false, defaultValue = "0") int page,
            @RequestParam(value = "size", required = false, defaultValue = "10") int size,
            @RequestParam(value = "sort", required = false) String sort) {
        Pageable pageable = PageRequest.of(page, size, sort != null ? Sort.by(sort) : Sort.unsorted());
        Page<T> pageResult = service.findByFilterString(filterString, pageable);
        Page<DTO> dtoPage = pageResult.map(mapper::toDTO);
        return ResponseEntity.ok(dtoPage);
    }


    @PutMapping("/{id}")
    public ResponseEntity<ResponseDto<DTO>> update(@PathVariable ID id, @RequestBody @Valid DTO dto) {
        T updatedEntity = service.update(id, dto);
        if (updatedEntity == null) {
            throw new EntityUpdateException("Failed to update entity with ID: " + id);
        }

        DTO updatedDto = mapper.toDTO(updatedEntity);
        ResponseDto<DTO> responseDto = ResponseUtil.buildResponse(LocalDateTime.now(), HttpStatus.OK, "Entity updated", null, updatedDto);        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto<Void>> delete(@PathVariable ID id) {
        boolean deleted = service.deleteById(id);
        if (!deleted) {
            throw new EntityDeleteException("Failed to delete entity with ID: " + id);
        }

        ResponseDto<Void> responseDto = ResponseUtil.buildResponse(LocalDateTime.now(), HttpStatus.NO_CONTENT, "Entity deleted", null, null);        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(responseDto);
    }

    // post sensitive data thru payload
//    {
//        "filter": "fieldName:fieldValue"
//    }
    @PostMapping("/search")
    public ResponseEntity<Page<DTO>> search(@RequestBody Map<String, String> filterData, Pageable pageable) {
        String filterString = filterData.get("filter");
        Page<T> page = service.findByFilterString(filterString, pageable);
        Page<DTO> dtoPage = page.map(mapper::toDTO);
        return ResponseEntity.ok(dtoPage);
    }
}
