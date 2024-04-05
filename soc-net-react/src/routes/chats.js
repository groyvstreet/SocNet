import { useContext, useEffect, useState } from "react";
import { getChats, addChat } from "../api/chatService";
import { AppContext } from "../contexts/contexts";
import { useNavigate } from "react-router-dom";
import './chats.css';
import ChatCard from "../components/chatCard";

export default function Chats() {
    const appContext = useContext(AppContext);

    const navigate = useNavigate();

    const [chats, setChats] = useState([]);
    const [name, setName] = useState('');

    useEffect(() => {
        loadChats();
    }, []);

    return (
        <section
            className="chats-container"
        >
            <div
                className="chats-create-container"
            >
                <input
                    placeholder="Название"
                    onChange={e => setName(e.target.value)}
                />
                <button
                    className="background-color-green"
                    onClick={addChatButtonOnClick}
                >
                    Создать
                </button>
            </div>
            {chats.map(chat => (
                <ChatCard
                    key={chat.id}
                    chat={chat}
                    onClick={() => navigateToChat(chat.id)}
                />
            ))}
        </section>
    );

    async function loadChats() {
        try {
            const chats = await getChats();
            setChats(chats);
        } catch (exception) {
            console.log(exception);
        }
    }

    async function addChatButtonOnClick() {
        await addChat(name, appContext.user.id);
        await loadChats();
    }

    function navigateToChat(chatId) {
        navigate(`/chats/${chatId}`);
    }
}
