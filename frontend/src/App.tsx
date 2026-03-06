import './App.css'

import {type ReactElement} from "react";
import {Route, Routes, useNavigate} from "react-router";
import ArtistOverviewPage from "./pages/ArtistOverviewPage.tsx";

import EventOverviewPage from "./pages/EventOverviewPage.tsx";
import {AppBar, Box, Button, IconButton, Toolbar, Typography} from "@mui/material";
import EventDetailPage from "./pages/EventDetailPage.tsx";

function App(): ReactElement {
    const navigate = useNavigate();
    return (
        <div>
            <Box sx={{ flexGrow: 1 }}>
                <AppBar position="static">
                    <Toolbar>
                        <IconButton
                            size="large"
                            edge="start"
                            color="inherit"
                            aria-label="menu"
                            sx={{ mr: 2 }}
                        >

                        </IconButton>
                        <h2>VibeCheck</h2>
                        <Typography variant="h6" component="div" sx={{ flexGrow: 1 }}
                                    onClick={() => navigate("/events")}>
                            Events

                        </Typography>
                        <Button color="inherit"
                                onClick={() => navigate("/artists")}>Artists</Button>
                    </Toolbar>
                </AppBar>
            </Box>

            <Routes>
                <Route path={"/"} element={<ArtistOverviewPage />} />
                <Route path={"artists"} element={<ArtistOverviewPage />} />
                <Route path={"events"} element={<EventOverviewPage />} />
                <Route path={"events/:eventId"} element={<EventDetailPage />} />
            </Routes>
        </div>
    )
}

export default App
