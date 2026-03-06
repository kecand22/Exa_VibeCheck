import { create } from "zustand";
import { devtools } from "zustand/middleware";
import type { EventStore } from "../models/EventStore.ts";

export const useEventStore = create<EventStore>()(
    devtools(
        (setState) => ({
            events: [],
            selectedEvent: undefined,

            setEvents: (events) => {
                setState(
                    (state) => ({ ...state, events }),
                    false,
                    "setEvents"
                );
            },

            setSelectedEvent: (event) => {
                setState(
                    (state) => ({ ...state, selectedEvent: event }),
                    false,
                    "setSelectedEvent"
                );
            },

            addRatingToEvent: (eventId, rating) => {
                setState(
                    (state) => ({
                        ...state,
                        events: state.events.map((event) =>
                            event.eventId === eventId
                                ? { ...event, ratings: [...event.ratings, rating] }
                                : event
                        ),
                    }),
                    false,
                    "addRatingToEvent"
                );
            },
        }),
        { name: "EventStore" }
    )
);