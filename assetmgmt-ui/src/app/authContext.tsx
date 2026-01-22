import React, { createContext, useCallback, useContext, useMemo, useState } from "react";
import type { UserSummary } from "../shared/types/user";
import { setAuthToken } from "../shared/api/http";

type AuthContexType = {
    user: UserSummary | null;
    login: (token: string, user: UserSummary) => void;
    logout: () => void;
};

const AuthContext = createContext<AuthContexType | null>(null);

export const AuthProvider = ({ children }: { children: React.ReactNode }) => {
    const [user, setUser] = useState<UserSummary | null>(null);

    const login = useCallback((token: string, user: UserSummary) => {
        setAuthToken(token);
        setUser(user);
    }, []);

    const logout = useCallback(() => {
        setAuthToken(null);
        setUser(null);
    }, []);

    const value = useMemo(
        () => ({ user, login, logout }),
        [user, login, logout]
    );

    return <AuthContext.Provider value={value}>{children}</AuthContext.Provider>
};

export const useAuth = () => {
    const ctx = useContext(AuthContext);
    if (!ctx) throw new Error('useAuth must be used within AuthProvider');
    return ctx;
}