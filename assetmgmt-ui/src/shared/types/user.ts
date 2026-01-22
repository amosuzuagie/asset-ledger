export type UserSummary = {
    id: string;
    email: string;
    firstName: string;
    lastName: string;
    role: 'ADMIN' | 'AUDIT' | 'FINANCE' | 'EMPLOYEE' | 'MANAGERS' | 'DIRECTORS';
}