import React, {useEffect, useState} from 'react';
import {Card, CardContent, CardMedia, Rating, Typography} from "@mui/material";
import type {EventModel} from "../models/EventModel.ts";
import {useNavigate} from "react-router";

export interface EventCardProps {
    event: EventModel
}


const EventCard: React.FC<EventCardProps> = ({event}) => {
    const navigate = useNavigate();
    const [average, setAverage] = useState<number>(0.0);

    useEffect(() => {
        console.log(typeof event.eventDate);

        let sum = 0;
        event.ratings.forEach(r => sum += r.stars);

        setAverage(event.ratings.length ? sum / event.ratings.length : 0);
    }, [event]);


    return (
        <>
            <Card sx={{ maxWidth: 345 }}
                  onClick={() => {
                      console.log(event.title); navigate(`/events/${event.eventId}`)}}>
                <CardMedia
                    sx={{ height: 140 }}
                    image={event.imageUrl}
                    title="artist"
                />
                <CardContent>
                    <Typography gutterBottom variant="h5" component="div">
                        {event.title}
                    </Typography>
                    <Typography variant="body2" sx={{ color: 'text.secondary' }}>
                        {event.location} {event.eventDate.toLocaleDateString()}
                    </Typography>
                    <Rating name="read-only" value={average} readOnly />
                </CardContent>
            </Card>
        </>
    );
};

export default EventCard;