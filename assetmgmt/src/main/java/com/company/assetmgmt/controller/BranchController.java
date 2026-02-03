package com.company.assetmgmt.controller;

import com.company.assetmgmt.dto.BranchRequest;
import com.company.assetmgmt.dto.BranchResponse;
import com.company.assetmgmt.service.BranchService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/branches")
public class BranchController {
    private final BranchService branchService;


    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<BranchResponse> create(
            @Valid @RequestBody BranchRequest request
    ) {
        return ResponseEntity.ok(branchService.create(request));
    }


    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<BranchResponse> update(
            @PathVariable UUID id,
            @Valid @RequestBody BranchRequest request
    ) {
        return ResponseEntity.ok(branchService.update(id, request));
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN','FINANCE','MANAGERS','DIRECTORS','AUDIT')")
    public ResponseEntity<List<BranchResponse>> findAll() {
        log.info("Fetching branches");
        return ResponseEntity.ok(branchService.findAll());
    }

    @GetMapping("/{branchId}")
    public ResponseEntity<BranchResponse> getById(@PathVariable UUID branchId) {
        System.out.println("DEBUGGING: Get Branch by ID: " + branchId);
        return ResponseEntity.ok(branchService.getBranchById(branchId));
    }
}
