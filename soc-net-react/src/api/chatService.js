import axios from "axios";

export async function getChats() {
    const response = await axios.get('/chats');

    return response.data;
}

export async function addChat(name, userId) {
    const response = await axios.post('/chats', {
        name,
        userId
    });

    return response.data;
}

export async function getChatById(id) {
    const response = await axios.get(`/chats/${id}`);

    return response.data;
}

export async function addUserToChat(chatId, userId) {
    const response = await axios.post(`/chats/users?chatId=${chatId}&userId=${userId}`)

    return response.data;
}

export async function removeUserFromChat(chatId, userId) {
    const response = await axios.delete(`/chats/users?chatId=${chatId}&userId=${userId}`)

    return response.data;
}

export async function updateChat(id, name) {
    const response = await axios.put('/chats', {
        id,
        name
    });

    return response.data;
}

export async function removeChatById(id) {
    const response = await axios.delete(`/chats/${id}`);

    return response.data;
}
