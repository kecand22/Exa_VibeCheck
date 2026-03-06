import {type FC, useState} from "react";
import axios from "axios";
import {Box, Button, Rating, TextField} from "@mui/material";

interface Props {
    eventId: number
}

const RatingForm: FC<Props> = ({eventId}) => {

    const [stars, setStars] = useState<number | null>(0);
    const [comment, setComment] = useState("");

    const submitRating = async () => {

        const rating = {
            stars: stars,
            comment: comment
        };

        await axios.post(
            `http://localhost:8080/api/events/${eventId}/ratings`,
            rating
        );

        window.location.reload(); // refresh page after submit
    };

    return (
        <Box>

            <h2>Add Rating</h2>

            <Rating
                value={stars}
                onChange={(event, value) => setStars(value)}
            />

            <TextField
                label="Comment"
                fullWidth
                sx={{mt:2}}
                value={comment}
                onChange={(e) => setComment(e.target.value)}
            />

            <Button
                variant="contained"
                sx={{mt:2}}
                onClick={submitRating}
            >
                Submit
            </Button>

        </Box>
    );
};

export default RatingForm;