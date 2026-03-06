import type { EventModel } from "./EventModel.ts";
import type {Rating} from "./Rating.ts";

export interface EventStore {
    events: EventModel[];
    selectedEvent?: EventModel;

    setEvents: (events: EventModel[]) => void;
    setSelectedEvent: (event: EventModel) => void;

    addRatingToEvent: (eventId: number, rating: Rating) => void;
}