import { Navigate, Route, Routes } from "react-router-dom";
import { RequireAuth } from "./requireAuth";
import { RequireRole } from "./RequireRole";

import LoginPage from "../pages/LoginPage";
import RegisterPage from "../pages/RegisterPage";
import DashboardPage from "../pages/DashboardPage";

import { AssetListPage } from "../features/assets/pages/AssetListPage";
import { CreateAssetPage } from "../features/assets/pages/CreateAssetPage";
import { EditAssetPage } from "../features/assets/pages/EditAssetPage";
import { AssetDetailsPage } from "../features/assets/pages/AssetDetailsPage";

export const AppRouter = () => {
  return (
    <Routes>
      {/* Default */}
      <Route path="/" element={<Navigate to="/dashboard" />} />

      {/* Public */}
      <Route path="/login" element={<LoginPage />} />
      <Route path="/register" element={<RegisterPage />} />

      {/* Protected */}
      <Route
        path="/dashboard"
        element={
          <RequireAuth>
            <DashboardPage />
          </RequireAuth>
        }
      />

      {/* ASSETS (Protected) */}
      <Route
        path="/assets"
        element={
          <RequireAuth>
            <AssetListPage />
          </RequireAuth>
        }
      />

      {/* Future asset routes (placeholders for now) */}
      <Route
        path="/assets/new"
        element={
          <RequireAuth>
            <CreateAssetPage />
          </RequireAuth>
        }
      />

      <Route
        path="/assets/:id/edit"
        element={
          <RequireAuth>
            <EditAssetPage />
          </RequireAuth>
        }
      />

      <Route
        path="/assets/:id"
        element={
          <RequireAuth>
            <AssetDetailsPage />
          </RequireAuth>
        }
      />

      {/* Admin */}
      <Route
        path="/admin"
        element={
          <RequireRole role={["ADMIN"]}>
            <p>AdminDashboard</p>
          </RequireRole>
        }
      />
    </Routes>
  );
};
