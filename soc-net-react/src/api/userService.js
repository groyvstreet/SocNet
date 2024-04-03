import axios from "axios"

export async function getUserById(id) {
    const response = await axios.get(`/users/${id}`);

    return response.data;
}
