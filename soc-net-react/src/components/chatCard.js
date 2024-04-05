import './chatCard.css'

export default function ChatCard({ chat, onClick }) {
    return (
        <section
            className="chat-card"
            onClick={onClick}
        >
            {chat.name}
        </section>
    );
}
