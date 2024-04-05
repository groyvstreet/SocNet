import axios from "axios";

export async function addMessage(chatId, userId, text) {
    const response = await axios.post('/messages', {
        text,
        chatId,
        userId
    });

    return response.data;
}

export async function updateMessage(id, text) {
    const response = await axios.put('/messages', {
        id,
        text
    });

    return response.data;
}

export async function removeMessageById(id) {
    const response = await axios.delete(`/messages/${id}`);

    return response.data;
}

export async function getMessagesByChatId(chatId) {
    const response = await axios.get(`/chats/${chatId}/messages`);

    return response.data;
}
