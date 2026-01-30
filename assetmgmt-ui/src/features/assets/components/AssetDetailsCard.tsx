import type { AssetResponse } from "../../../shared/types/asset";

export const AssetDetailsCard = ({ asset }: { asset: AssetResponse }) => {
  return (
    <div className="bg-white rounded-lg shadow p-6 grid grid-cols-2 gap-4">
      <Detail label="Asset Code" value={asset.assetCode} />
      <Detail label="Description" value={asset.description} />
      <Detail label="Category" value={asset.categoryName} />
      <Detail label="Class" value={asset.assetClass} />
      <Detail label="Status" value={asset.status} />
      <Detail label="Branch" value={asset.branchName ?? "-"} />
      <Detail label="Amount" value={asset.amount?.toLocaleString() ?? "-"} />
      <Detail label="Acquired" value={asset.dateOfAcquisition ?? "-"} />
    </div>
  );
};

const Detail = ({ label, value }: { label: string; value: string }) => (
  <div>
    <p className="text-xs text-gray-500">{label}</p>
    <p className="font-medium">{value}</p>
  </div>
);
