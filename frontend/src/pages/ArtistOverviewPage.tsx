import {type FC, useEffect} from 'react';
import {BackendService} from "../services/BackendService.ts";
import {useAppStore} from "../store/appstore.ts";
import ArtistCard from "../components/ArtistCard.tsx";
import {Grid} from "@mui/material";



const ArtistOverviewPage: FC = () => {
    const { artists, setArtists } = useAppStore();

    useEffect(() => {
        BackendService.loadAllArtists()
            .then(a => {
                setArtists(a);
            })
    }, []);


    return (
        <>
            <h1>Artists</h1>


            <br/>

            <Grid container spacing={{ xs: 2, md: 3 }} columns={{ xs: 4, sm: 8, md: 12 }}>
                {artists.map(a => (
                    <Grid key={a.artistId} size={{ xs: 2, sm: 4, md: 4 }}>
                        <ArtistCard artist={a} />
                    </Grid>
                ))}
            </Grid>



        </>
    );
};

export default ArtistOverviewPage;