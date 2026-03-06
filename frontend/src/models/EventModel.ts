import type {Artist} from "./Artist.ts";
import type {Rating} from "./Rating.ts";

export interface EventModel  {
    eventId: number,
    title: string,
    location: string,
    eventDate: Date,
    imageUrl: string,
    artists : Artist[],
    ratings : Rating[]



}

/*
 "title": "Neon Nights Festival",
  "location": "Berlin",
  "eventDate": "2025-06-12",
  "imageUrl": "https://images.unsplash.com/photo-1459749411175-04bf5292ceea?auto=format&fit=crop&w=800&q=60",
  "artists": [],
  "ratings": [
    {
      "comment": "Incredible atmosphere!",
      "createdAt": "2025-06-13T10:15:00",
      "stars": 5
    },
    {
      "comment": "Great visuals and sound.",
      "createdAt": "2025-06-13T11:20:00",
      "stars": 4
    }
  ]
 */