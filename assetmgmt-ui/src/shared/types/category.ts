export type AssetClass = "FF" | "EQ" | "IT" | "VEHICLE"; 

export interface CategoryRequest {
  name: string;
  assetClass: AssetClass;
  description?: string;
}

export interface CategoryResponse {
  id: string;
  name: string;
  assetClass: AssetClass;
  description?: string;
}