import axios from "axios"

export async function getUserById(id) {
    const response = await axios.get(`/users/${id}`);

    return response.data;
}

export async function getUsers() {
    const response = await axios.get('/users');

    return response.data;
}

export async function getUsersByChatId(chatId, isInChat) {
    const response = await axios.get(`/chats/${chatId}/users?isInChat=${isInChat}`);

    return response.data;
}

export async function updateUser(id, firstName, lastName, birthDate, image) {
    const response = await axios.put('/users', {
        id,
        firstName,
        lastName,
        birthDate,
        image
    });

    return response.data;
}

export async function removeUserById(id) {
    const response = await axios.delete(`/users/${id}`);

    return response.data;
}
