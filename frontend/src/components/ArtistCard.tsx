import React from 'react';
import type {Artist} from "../models/Artist.ts";
import {Card, CardContent, CardMedia, Typography} from "@mui/material";

export interface ArtistCardProps {
    artist: Artist
}

/*"artistId": 15,
    "firstName": "Ella",
    "lastName": "Waves",
    "description": "Singer-songwriter inspired by ocean vibes.",
    "imageUrl": "https://images.unsplash.com/photo-1500530855697-b586d89ba3ee?auto=format&fit=crop&w=800&q=60"

 */

const ArtistCard: React.FC<ArtistCardProps> = ({ artist }) => {
    return (
        <Card sx={{ maxWidth: 345 }}>
            <CardMedia
                sx={{ height: 140 }}
                image={artist.imageUrl}
                title="artist"
            />
            <CardContent>
                <Typography gutterBottom variant="h5" component="div">
                    {artist.firstName} {artist.lastName}
                </Typography>
                <Typography variant="body2" sx={{ color: 'text.secondary' }}>
                    {artist.description}
                </Typography>
            </CardContent>
        </Card>
    );
};

export default ArtistCard;