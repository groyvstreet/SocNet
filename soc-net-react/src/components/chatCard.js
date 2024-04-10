import './chatCard.css'
import PropTypes from 'prop-types';

export default function ChatCard({ chat, onClick }) {
    return (
        <section
            className="chat-card"
            onClick={onClick}
            tabIndex={0}
            onKeyDown={onKeyDownHandle}
            role='button'
        >
            {chat.name}
        </section>
    );

    function onKeyDownHandle(event) {
        console.log(event)
        if (event.code === 'Enter') {
            onClick();
        }
    }
}
ChatCard.propTypes = {
    chat: PropTypes.shape({
        name: PropTypes.string
    }),
    onClick: PropTypes.func
}
