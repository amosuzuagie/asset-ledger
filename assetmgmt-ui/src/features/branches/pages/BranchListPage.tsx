import { useEffect, useState } from "react"
import { type BranchResponse } from "../../../shared/types/branch"
import { useNavigate } from "react-router-dom";
import { branchApi } from "../api";
import { ActionButton } from "../../../shared/components/buttons/ActionButton";
import { BranchTable } from "../components/BranchTable";
import { useHasRole } from "../../../shared/hooks/useHasRole";

export const BranchListPage = () => {
    const [branches, setBranches] = useState<BranchResponse[]>([]);
    const navigate = useNavigate();

    useEffect(() => {
        branchApi.getAll().then(setBranches);
    }, []);

    const isAdmin = useHasRole("ADMIN");

    return (
        <div className="space-y-4">
            <div className="flex justify-between items-center">
                <h2 className="text-xl font-semibold">Branches</h2>
                {isAdmin && (
                    <ActionButton 
                        label="+ Create Branch"
                        onClick={() => navigate("/branches/new")}
                    />
                )}
            </div>

            <BranchTable
                branches={branches}
                onEdit={id => navigate(`/branches/${id}/edit`)}
            />
        </div>
    );
}