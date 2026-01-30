import { useNavigate, useParams } from "react-router-dom"
import { useAuth } from "../../../app/authContext";
import { useEffect, useState } from "react";
import { type AssetResponse } from "../../../shared/types/asset";
import { assetApi } from "../api";
import { AssetDetailsCard } from "../components/AssetDetailsCard";
import { ActionButton } from "../../../shared/components/buttons/ActionButton";

export const AssetDetailsPage = () => {
    const { id } = useParams<{ id: string }> ();
    const navigate = useNavigate();
    const { user } = useAuth();

    const [asset, setAsset] = useState<AssetResponse | null>(null);
    const [loading, setLoading] = useState(true);
    const [showAssignModal, setShowAssignModal] = useState(false);

    useEffect(() => {
        if (!id) return;

        assetApi.getById(id)
            .then(setAsset)
            .finally(() => setLoading(false))
    }, [id]);

    if (loading) return <p>Loading asset...</p>
    if (!asset) return <p>Asset not found</p>;

    const canEdit = ["ADMIN", "FINANCE"].includes(user?.role ?? "");
    const canAssign = ["ADMIN", "FINANCE"].includes(user?.role ?? "");

    return (
    <div className="max-w-4xl mx-auto space-y-6">
      <AssetDetailsCard asset={asset} />

      <div className="flex gap-3">
        {canEdit && (
            <ActionButton
            label="Edit Asset"
            variant="secondary"
            onClick={() => navigate(`/assets/${asset.id}/edit`)}
            />
        )}

        {canAssign && (
            <ActionButton
            label="Assign Asset"
            variant="primary"
            disabled={asset.status === "DISPOSED"}
            onClick={() => setShowAssignModal(true)}
            />
        )}
      </div>
    </div>
  );
}