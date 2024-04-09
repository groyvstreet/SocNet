import { getChatById } from "../api/chatService";

export default async function chatLoader({ params }) {
    const chat = await getChatById(params.chatId);

    return { chat }
}
