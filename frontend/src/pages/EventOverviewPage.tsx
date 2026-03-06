import React, {useEffect} from 'react';
import {Grid} from "@mui/material";
import {useAppStore} from "../store/appstore.ts";
import {BackendService} from "../services/BackendService.ts";
import EventCard from "../components/EventCard.tsx";
import type {EventModel} from "../models/EventModel.ts";

const EventOverviewPage: React.FC = () => {

    const { events, setEvents } = useAppStore();

    useEffect(() => {
        BackendService.loadAllEvents()
            .then(e => {
                setEvents(
                    e.map(ev => {
                        const newEvent: EventModel = {
                            ...ev,
                            eventDate: new Date(ev.eventDate)
                        };

                        return newEvent;
                    })
                );
            })
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