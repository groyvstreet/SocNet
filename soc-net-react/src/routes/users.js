import { useEffect, useState } from "react";
import { getUsers } from "../api/userService";
import './users.css'

export default function Users() {
    const [users, setUsers] = useState([]);

    useEffect(() => {
        loadUsers();
    }, [])

    return (
        <section
            className="users-container"
        >
            {users.map(user => (
                <p
                    className="user-p"
                    key={user.id}
                >
                    {user.firstName} {user.lastName}
                </p>
            ))}
        </section>
    );

    async function loadUsers() {
        const users = await getUsers();
        setUsers(users);
    }
}
