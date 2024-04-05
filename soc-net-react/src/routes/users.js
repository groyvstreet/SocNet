import { useEffect, useState } from "react";
import { getUsers } from "../api/userService";

export default function Users() {
    const [users, setUsers] = useState([]);

    useEffect(() => {
        loadUsers();
    }, [])

    return (
        <section>
            {users.map(user => (
                <p
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
