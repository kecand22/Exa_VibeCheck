import type {Artist} from "./Artist.ts";
import type {EventModel} from "./EventModel.ts";

export interface AppStore {
    artists: Artist[],
    setArtists: (artists: Artist[]) => void;
    events: EventModel[],
    setEvents: (events: EventModel[]) => void;
}