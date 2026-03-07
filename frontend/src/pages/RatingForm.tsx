import { FC, useState } from "react";
import axios from "axios";
import { Box, Button, Rating, TextField } from "@mui/material";

interface Props {
    eventId: number;
}

const RatingForm: FC<Props> = ({ eventId }) => {

    const [stars, setStars] = useState<number>(0);
    const [comment, setComment] = useState<string>("");

    const submitRating = async () => {

        if (stars === 0) {
            alert("Please select a star rating");
            return;
        }

        const rating = {
            stars: stars,
            comment: comment,
            createdAt: new Date().toISOString()
        };

        try {

            await axios.post(
                `http://localhost:8080/api/ratings/${eventId}`,
                rating
            );


            window.location.reload();

        } catch (error) {
            console.error("Submit rating failed:", error);
            alert("Failed to submit rating");
        }
    };

    return (
        <Box sx={{ mt: 4 }}>

            <h2>Add Rating</h2>

            <Rating
                value={stars}
                onChange={(event, value) => setStars(value ?? 0)}
            />

            <TextField
                label="Comment"
                fullWidth
                sx={{ mt: 2 }}
                value={comment}
                onChange={(e) => setComment(e.target.value)}
            />

            <Button
                variant="contained"
                sx={{ mt: 2 }}
                onClick={submitRating}
            >
                Submit
            </Button>

        </Box>
    );
};

export default RatingForm;