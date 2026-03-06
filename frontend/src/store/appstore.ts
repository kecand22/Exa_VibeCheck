import { create } from "zustand";
import { devtools } from "zustand/middleware";
import type {AppStore} from "../models/AppStore.ts";

export const useAppStore = create<AppStore>()(
    devtools(
        (setState) => ({
            artists: [],
            setArtists: (artists) => {
                setState(
                    (state) => ({ ...state, artists }),
                    false,
                    "setArtist"
                );
            },
            events: [],
            setEvents: (events) => {
                setState(
                    (state) => ({ ...state, events }),
                    false,
                    "setArtist"
                );
            },
        }),
        { name: "AppStore" }
    )
);