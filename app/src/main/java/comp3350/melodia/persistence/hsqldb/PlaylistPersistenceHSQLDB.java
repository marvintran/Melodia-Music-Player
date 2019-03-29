package comp3350.melodia.persistence.hsqldb;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import comp3350.melodia.objects.Playlist;
import comp3350.melodia.objects.Song;
import comp3350.melodia.persistence.PlaylistPersistence;

public class PlaylistPersistenceHSQLDB implements PlaylistPersistence {

    private final String dbPath;

    public PlaylistPersistenceHSQLDB(final String dbPath) {
        this.dbPath = dbPath;
    }

    private Connection connection() throws SQLException {
        return DriverManager.getConnection("jdbc:hsqldb:file:" + dbPath + ";shutdown=true", "SA", "");
    }

    private Playlist fromResultSet(final ResultSet rs) throws SQLException {

        final int playlistID = rs.getInt("playlistID");
        final String playlistName = rs.getString("playlistName");
        final int numberOfSongs = rs.getInt("numSongs");

        return new Playlist(playlistID, playlistName, numberOfSongs);
    }

    @Override
    public List<Playlist> getAllPlaylists() {
        final List<Playlist> playlists = new ArrayList<>();

        try (final Connection c = connection()) {

            final Statement st = c.createStatement();
            final ResultSet rs = st.executeQuery("SELECT * FROM PLAYLIST");
            while (rs.next())
            {
                final Playlist playlist = fromResultSet(rs);
                playlists.add(playlist);
            }
            rs.close();
            st.close();

            return playlists;
        }
        catch (final SQLException e)
        {
            throw new PersistenceException(e);
        }
    }

    @Override
    public void insertPlaylist(String playlistName) {

        try (final Connection c = connection()) {
            //final PreparedStatement st = c.prepareStatement("INSERT INTO PLAYLIST VALUES(?, ?)");
            final PreparedStatement st = c.prepareStatement("INSERT INTO PLAYLIST (PLAYLISTNAME, NUMSONGS) VALUES('"+playlistName+"', 0)");
            //st.setString(1, playlistName);
            //st.setInt(2, 0);// a new playlist has 0 songs to start
            st.executeUpdate();

        } catch (final SQLException e) {
            throw new PersistenceException(e);
        }
    }

    @Override
    public void updatePlaylist(int playlistID, int songID) {

        try (final Connection c = connection()) {

            // insert song into playlist
            final PreparedStatement st = c.prepareStatement("INSERT INTO PLAYLIST_SONGS VALUES(?, ?)");
            st.setInt(1, playlistID);
            st.setInt(2, songID);
            st.executeUpdate();

            // update the number of songs in this playlist
            final Statement st2 = c.createStatement();
            final ResultSet rs = st2.executeQuery("SELECT * FROM PLAYLIST WHERE PLAYLISTID = " + playlistID);
            rs.next();

            int numSongsPlusOne = rs.getInt("NUMSONGS") + 1;
            final PreparedStatement st3 = c.prepareStatement("UPDATE PLAYLIST SET NUMSONGS = "+numSongsPlusOne+" WHERE PLAYLISTID = "+playlistID);
            st3.executeUpdate();

        } catch (final SQLException e) {
            throw new PersistenceException(e);
        }
    }

    @Override
    public void deletePlaylist(Playlist currentPlaylist) {

        try (final Connection c = connection()) {
            final PreparedStatement sc = c.prepareStatement("DELETE FROM PLAYLIST WHERE PLAYLISTID = ?");
            sc.setInt(1, currentPlaylist.getPlaylistID());
            sc.executeUpdate();
        } catch (final SQLException e) {
            throw new PersistenceException(e);
        }
    }
}

