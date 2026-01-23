import { useAuth } from "../app/authContext";

export default function DashboardPage() {
    const { user, logout } = useAuth();

    return (
        <div className="p-6">
            <h1 className="text-2xl font-bold">Welcome, {user?.firstName}</h1>
            <button
                onClick={logout}
                className="mt-4 text-red-600 underline"
            >Logout</button>
        </div>
    );
}