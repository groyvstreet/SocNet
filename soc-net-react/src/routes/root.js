import { useContext } from "react";
import Navbar from "../components/navbar";
import { Outlet } from "react-router-dom";
import { AppContext } from "../contexts/contexts";

export default function Root() {
    const appContext = useContext(AppContext);

    return (
        <main>
            <Navbar isAuthenticated={appContext.isAuthenticated}/>
            <Outlet />
        </main>
    );
}
