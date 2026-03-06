import type {Artist} from "../models/Artist.ts";
import axios from "axios";
import type {EventModel} from "../models/EventModel.ts";



export class BackendService {
    public static async loadAllArtists(): Promise<Artist[]> {
        const res = await axios.get<Artist[]>('http://localhost:8080/api/artists');
        return res.data;
    }

    public static async loadAllEvents(): Promise<EventModel[]> {
        const res = await axios.get<EventModel[]>('http://localhost:8080/api/events');
        return res.data;
    }
}