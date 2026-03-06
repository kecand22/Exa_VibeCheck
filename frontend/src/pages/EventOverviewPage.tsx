import React, {useEffect} from 'react';
import {Grid} from "@mui/material";
import {useAppStore} from "../store/appstore.ts";
import {BackendService} from "../services/BackendService.ts";
import EventCard from "../components/EventCard.tsx";

const EventOverviewPage: React.FC = () => {

    const { events, setEvents } = useAppStore();

    useEffect(() => {
        BackendService.loadAllEvents()
            .then(e => {
                setEvents(
                    e.map(ev => ({
                        eventId: ev.eventId,
                        title: ev.title,
                        location: ev.location,
                        imageUrl: ev.imageUrl,
                        eventDate: new Date(ev.eventDate),
                        artists: ev.artists,
                        ratings: ev.ratings
                    }))
                );
            });
    }, []);
    return (
        <>
            <h1>Events</h1>

            <Grid container spacing={{ xs: 2, md: 3 }} columns={{ xs: 4, sm: 8, md: 12 }}>
                {events.map(e => (
                    <Grid key={e.eventId} size={{ xs: 2, sm: 4, md: 4 }}>
                        <EventCard event={e} />
                    </Grid>
                ))}
            </Grid>



        </>
    );
};

export default EventOverviewPage;