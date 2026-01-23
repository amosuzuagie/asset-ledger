import { Navigate } from 'react-router-dom';
import { useAuth } from './authContext';
import type { JSX } from 'react';

export const RequireAuth = ({ children }: { children: JSX.Element }) => {
  const { user } = useAuth();

  if (!user) {
    return <Navigate to="/login" replace />;
  }

  return children;
};
