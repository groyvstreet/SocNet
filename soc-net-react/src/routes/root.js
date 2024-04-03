import { Outlet } from "react-router-dom";
import Navbar from "../components/navbar";

export default function Root() {
    return (
        <main>
            <Navbar />
            <Outlet />
        </main>
    );
}
