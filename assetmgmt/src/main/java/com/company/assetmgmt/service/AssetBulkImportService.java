package com.company.assetmgmt.service;

import com.company.assetmgmt.dto.AssetImportPreviewResponse;
import com.company.assetmgmt.dto.AssetImportResultResponse;
import com.company.assetmgmt.dto.AssetImportRow;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface AssetBulkImportService {
    AssetImportPreviewResponse preview(MultipartFile file);
    AssetImportResultResponse importAssets(List<AssetImportRow> rows);
}
