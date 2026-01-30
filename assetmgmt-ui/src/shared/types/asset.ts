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
