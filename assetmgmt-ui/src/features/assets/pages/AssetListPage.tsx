import { useEffect, useState } from "react";
import type { AssetResponse } from "../../../shared/types/asset";
import { useNavigate } from "react-router-dom";
import { assetApi } from "../api";
import { AssetTable } from "../components/AssetTable";

export const AssetListPage = () => {
  const [assets, setAssets] = useState<AssetResponse[]>([]);
  const [loading, setLoading] = useState(true);
  const navigate = useNavigate();

  useEffect(() => {
    assetApi.getAll()
      .then(setAssets)
      .finally(() => setLoading(false));
  }, []);

  const handleDelete = async (id: string) => {
    if (!confirm("Are you sure you want to delete this asset?")) return;

    await assetApi.remove(id);
    setAssets(prev => prev.filter(a => a.id !== id));
  };

  if (loading) {
    return (
      <div className="p-6 text-gray-600">
        Loading assets...
      </div>
    );
  }

  return (
    <div className="p-6 space-y-6">
      {/* Header */}
      <div className="flex items-center justify-between">
        <h2 className="text-2xl font-semibold text-gray-800">
          Assets
        </h2>

        <button
          onClick={() => navigate("/assets/new")}
          className="
            inline-flex items-center gap-2
            rounded-md bg-blue-600 px-4 py-2
            text-sm font-medium text-white
            hover:bg-blue-700
            focus:outline-none focus:ring-2 focus:ring-blue-500
          "
        >
          + Create Asset
        </button>
      </div>

      {/* Table */}
      <div className="rounded-lg border border-gray-200 bg-white shadow-sm">
        <AssetTable
          assets={assets}
          onView={id => navigate(`/assets/${id}`)}
          onEdit={id => navigate(`/assets/${id}/edit`)}
          onDelete={handleDelete}
        />
      </div>
    </div>
  );
};
