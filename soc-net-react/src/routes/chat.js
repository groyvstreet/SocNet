import { useLoaderData, useNavigate } from "react-router-dom";
import { addUserToChat, getChatById, removeChatById, removeUserFromChat, updateChat } from "../api/chatService";
import { useContext, useEffect, useState } from "react";
import { getUsersByChatId } from "../api/userService";
import { addMessage, getMessagesByChatId, removeMessageById, updateMessage } from "../api/messageService";
import { AppContext } from "../contexts/contexts";

export async function chatLoader({ params }) {
    const chat = await getChatById(params.chatId);

    return { chat }
}

export default function Chat() {
    const { chat } = useLoaderData();

    const appContext = useContext(AppContext);

    const navigate = useNavigate();

    const [name, setName] = useState(chat.name);
    const [users, setUsers] = useState([]);
    const [chatUsers, setChatUsers] = useState([]);
    const [reload, setReload] = useState(false);

    const [messages, setMessages] = useState([]);
    const [text, setText] = useState('');

    useEffect(() => {
        loadUsers(chat.id);
        loadChatUsers(chat.id);
        loadMessages(chat.id)
    }, [reload]);

    return (
        <>
            {users.map(user => (
                <div key={user.id}>
                    <button
                        onClick={() => addUserToChatButtonOnClick(chat.id, user.id)}
                    >
                        {user.firstName} {user.lastName}
                    </button>
                    <hr />
                </div>
            ))}
            <h1>{name}</h1>
            <input
                placeholder="Название"
                value={name}
                onChange={e => setName(e.target.value)}
            />
            <button
                onClick={updateChatButtonOnClick}
            >
                Изменить
            </button>
            <button
                onClick={removeChatButtonOnClick}
            >
                Удалить
            </button>
            <h2>Пользователи</h2>
            {chatUsers.map(user => (
                <div key={user.id}>
                    <button
                        onClick={() => removeUserFromChatButtonOnClick(chat.id, user.id)}
                    >
                        {user.firstName} {user.lastName}
                    </button>
                    <hr />
                </div>
            ))}
            <h2>Сообщения</h2>
            <input
                placeholder="Введите текст"
                value={text}
                onChange={e => setText(e.target.value)}
                
            />
            <button
                onClick={addMessageButtonOnClick}
            >
                Отправить
            </button>
            {messages.map(message => (
                <>
                    {message.text}
                    <button
                        onClick={() => updateMessageButtonOnClick(message.id)}
                    >
                        Изменить
                    </button>
                    <button
                        onClick={() => removeMessageButtonOnClick(message.id)}
                    >
                        Удалить
                    </button>
                </>
            ))}
        </>
    );

    async function updateChatButtonOnClick() {
        await updateChat(chat.id, name);
    }

    async function removeChatButtonOnClick() {
        await removeChatById(chat.id);
        navigate('/chats');
    }

    async function loadUsers(chatId) {
        const users = await getUsersByChatId(chatId, false);
        setUsers(users);
    }

    async function loadChatUsers(chatId) {
        const chatUsers = await getUsersByChatId(chatId, true);
        setChatUsers(chatUsers);
    }

    async function addUserToChatButtonOnClick(chatId, userId) {
        await addUserToChat(chatId, userId);
        setReload(!reload);
    }

    async function removeUserFromChatButtonOnClick(chatId, userId) {
        await removeUserFromChat(chatId, userId);
        setReload(!reload);
    }

    async function loadMessages(chatId) {
        const messages = await getMessagesByChatId(chatId);
        setMessages(messages);
    }

    async function addMessageButtonOnClick() {
        await addMessage(chat.id, appContext.user.id, text);
        setReload(!reload);
    }

    async function updateMessageButtonOnClick(messageId) {
        await updateMessage(messageId, text);
        setReload(!reload);
    }

    async function removeMessageButtonOnClick(messageId) {
        await removeMessageById(messageId);
        setReload(!reload);
    }
}
