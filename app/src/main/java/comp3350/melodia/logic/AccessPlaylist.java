package comp3350.melodia.logic;

import java.util.List;

import comp3350.melodia.objects.Playlist;
import comp3350.melodia.persistence.PlaylistPersistence;

public class AccessPlaylist {
    private PlaylistPersistence playlistPersistence;

    public AccessPlaylist(PlaylistPersistence playlistPersistence){
        this.playlistPersistence = playlistPersistence;
    }

    public List<Playlist> getPlaylists(){
        List<Playlist> allPlaylists = playlistPersistence.getAllPlaylists();
        int count = 0;
        boolean sawQueuePlaylist = false;

        while(!sawQueuePlaylist && count < allPlaylists.size())
        {
            Playlist currentPlaylist = allPlaylists.get(count);
            if(currentPlaylist.getPlaylistID() == 0) {
                sawQueuePlaylist = true;
                allPlaylists.remove(count);
            }
            count++;
        }

        return allPlaylists;
    }

    public Playlist getSpecificPlaylist(int playlistID) {
        return playlistPersistence.getSpecificPlaylist(playlistID);
    }

    public void insertPlaylist(String playlistName){
        playlistPersistence.insertPlaylist(playlistName);
    }

    public void deletePlaylist(int playlistID) {
        playlistPersistence.deletePlaylist(playlistID);
    }
}
