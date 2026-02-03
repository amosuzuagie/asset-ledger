import { useEffect, useRef, useState } from "react";
import type { BranchResponse } from "../../../shared/types/branch";
import { branchApi } from "../api";

export const useBranches = () => {
  const [branches, setBranches] = useState<BranchResponse[]>([]);
  const [loading, setLoading] = useState(true);
  const fetchedRef = useRef(false); // 🔑 THIS IS THE KEY

  useEffect(() => {
    if (fetchedRef.current) return;

    fetchedRef.current = true;

    branchApi.getAll()
      .then(setBranches)
      .finally(() => setLoading(false));
  }, []);

  return { branches, loading };
};
