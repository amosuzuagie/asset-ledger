import { http } from "../../shared/api/http";
import type { AssetCreateRequest, AssetResponse } from "../../shared/types/asset";


export const assetApi = {
  getAll: () =>
    http.get<AssetResponse[]>('/api/assets')
      .then(r => r.data),

  getById: (id: string) =>
    http.get<AssetResponse>(`/api/assets/${id}`)
      .then(r => r.data),

  create: (categoryId: string, data: AssetCreateRequest) =>
    http.post<AssetResponse>(
      `/api/assets/${categoryId}`,
      data
    ).then(r => r.data),

  update: (assetId: string, categoryId: string, data: any) =>
    http.put<AssetResponse>(
      `/api/assets/${assetId}/category/${categoryId}`,
      data
    ).then(r => r.data),

  remove: (id: string) =>
    http.delete(`/api/assets/${id}`),

  restore: (id: string) =>
    http.put(`/api/assets/${id}/restore`),

  assign: (assetId: string, branchId: string) =>
    http.put(`/api/assets/${assetId}/assign/${branchId}`),
};
