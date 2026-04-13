import type { AssetClass } from "./asset";

export interface CategoryRequest {
  name: string;
  description?: string;
}

export interface CategoryResponse {
  id: string;
  name: string;
  description?: string;
}

export type { AssetClass };