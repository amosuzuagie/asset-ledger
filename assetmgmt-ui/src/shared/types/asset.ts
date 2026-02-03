export type AssetResponse = {
  id: string;
  assetCode: string;
  description: string;
  assetClass: 'FF' | 'EQ' | 'IT' | 'VEHICLE';

  categoryId: string;
  categoryName: string;

  status: 'IN_STORE' | 'ASSIGNED' | 'DISPOSED';

  branchId?: string;
  branchName?: string;
  
  amount?: number;
  dateOfAcquisition?: string;

  costOfDisposal?: string;
  disposalRemark?: string;
};

export type AssetCreateRequest = {
  assetCode: string;
  description: string;
  categoryId: string;
  serialNumber?: string;
  dateOfAcquisition?: string;
  amount?: number;
  subsidiary?: string;
  remark?: string;
  branchId?: string;
};

export type AssetDisposalRequest = {
  assetId: string;
  costOfDisposal?: number;
  remark?: string;
};

export type AssetMovementResponse = {
  id: string;
  assetId: string;

  fromBranchId?: string;
  fromBranchName?: string;

  toBranchId?: string;
  toBranchName?: string;

  reason?: string;
  movementDate: string; // Instant → ISO string
};