package com.company.assetmgmt.controller;

import com.company.assetmgmt.dto.BranchRequest;
import com.company.assetmgmt.dto.BranchResponse;
import com.company.assetmgmt.service.BranchService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/branches")
public class BranchController {
    private final BranchService branchService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<BranchResponse> create(
            @Valid @RequestBody BranchRequest request
    ) {
        return ResponseEntity.ok(branchService.create(request));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<BranchResponse> update(
            @PathVariable UUID id,
            @Valid @RequestBody BranchRequest request
    ) {
        return ResponseEntity.ok(branchService.update(id, request));
    }

    @PreAuthorize("hasAnyRole('ADMIN','FINANCE','MANAGERS','DIRECTORS','AUDIT')")
    @GetMapping
    public ResponseEntity<List<BranchResponse>> findAll() {
        return ResponseEntity.ok(branchService.findAll());
    }
}
