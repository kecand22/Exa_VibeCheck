import {type FC, useEffect, useState} from "react";
import {useParams} from "react-router";
import {BackendService} from "../services/BackendService.ts";
import type {EventModel} from "../models/EventModel.ts";
import {Box, Card, CardContent, Grid, Typography} from "@mui/material";
import RatingForm from "../pages/RatingForm.tsx";

const EventDetailPage: FC = () => {

    const { eventId } = useParams<{ eventId: string }>();
    const [event, setEvent] = useState<EventModel | undefined>();

    useEffect(() => {
        BackendService.loadAllEvents()
            .then(events => {
                const e = events.find(ev => ev.eventId === Number(eventId));
                setEvent(e);
            });
    }, [eventId]);
    console.log(event)
    if (!event) {
        return <div>Loading...</div>;
    }

    const avgRating =
        event.ratings.length === 0
            ? 0
            : event.ratings.reduce((s, r) => s + r.stars, 0) / event.ratings.length;

    return (
        <>
            <h1>{event.title}</h1>

            <Typography>{event.location}</Typography>

            <img src={event.imageUrl} width={400}/>

            <h2>Artists</h2>

            <Grid container spacing={2}>
                {event.artists.map(a => (
                    <Grid key={a.artistId}>
                        <Typography>
                            {a.firstName} {a.lastName}
                        </Typography>
                    </Grid>
                ))}
            </Grid>

            <h2>Average Rating: {avgRating.toFixed(2)}</h2>

            <h2>Ratings</h2>

            {event.ratings.map((r, i) => (
                <Card key={i} sx={{mt:2}}>
                    <CardContent>
                        <Typography>Stars: {r.stars}</Typography>
                        <Typography>{r.comment}</Typography>
                    </CardContent>
                </Card>
            ))}

            <Box sx={{mt:4}}>
                <RatingForm eventId={event.eventId}/>
            </Box>

        </>
    );
};

export default EventDetailPage;