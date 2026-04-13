package com.company.assetmgmt.controller;

import com.company.assetmgmt.dto.AssetImportPreviewResponse;
import com.company.assetmgmt.dto.AssetImportResultResponse;
import com.company.assetmgmt.dto.AssetImportRow;
import com.company.assetmgmt.service.AssetBulkImportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/assets/import")
public class AssetBulkImportController {

    private final AssetBulkImportService bulkImportService;

    @PostMapping("/preview")
    public ResponseEntity<AssetImportPreviewResponse> preview(@RequestParam("file")MultipartFile file) {
        return ResponseEntity.ok(bulkImportService.preview(file));
    }

    @PostMapping("/confirm")
    public ResponseEntity<AssetImportResultResponse> confirm(@RequestBody List<AssetImportRow> rows) {
        return ResponseEntity.ok(bulkImportService.importAssets(rows));
    }
}
