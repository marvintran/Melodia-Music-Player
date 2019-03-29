package comp3350.melodia.presentation;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import comp3350.melodia.R;
import comp3350.melodia.objects.Playlist;


// inspiration was taken from the following:
// https://developer.android.com/guide/topics/ui/layout/recyclerview
// https://code.tutsplus.com/tutorials/android-from-scratch-understanding-adapters-and-adapter-views--cms-26646
// https://guides.codepath.com/android/using-the-recyclerview
public class PlaylistAdapter
        extends RecyclerView.Adapter<PlaylistAdapter.PlaylistViewHolder>{

    private List<Playlist> playlists;
    private OnPlaylistClickedListener listenerClick;
    private OnPlaylistLongClickedListener listenerLongClick;

    // PlaylistFragment must implement these interfaces.
    // This lets the adapter send data back to the fragment.
    public interface OnPlaylistClickedListener {
        void onPlaylistClicked(int playlistIndex);
    }
    public interface OnPlaylistLongClickedListener {
        void onPlaylistLongClicked(int playlistIndex);
    }

    public static class PlaylistViewHolder extends RecyclerView.ViewHolder {
        TextView playlistTitle;
        TextView numberOfSongs;
        LinearLayout linearlayout;

        PlaylistViewHolder(View itemView) {
            super(itemView);
            playlistTitle = (TextView)itemView.findViewById(
                    R.id.playlist_title);
            numberOfSongs = (TextView)itemView.findViewById(
                    R.id.total_songs);
            this.linearlayout = (LinearLayout) itemView.findViewById(
                    R.id.linearlayout);
        }
    }

    // constructor
    public PlaylistAdapter(List<Playlist> playlists,
                           OnPlaylistClickedListener listenerClick,
                           OnPlaylistLongClickedListener listenerLongClick) {
        this.playlists = playlists;
        this.listenerClick = listenerClick;
        this.listenerLongClick = listenerLongClick;
    }

    // RecyclerView.Adapter has three abstract methods that we must override.
    // getItemCount(), onCreateViewHolder() and onBindViewHolder().

    @Override
    public int getItemCount() {
        return playlists.size();
    }


    @Override
    public PlaylistViewHolder onCreateViewHolder(ViewGroup parent,
                                                 int viewType) {
        View playlistView = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.playlist_item, parent, false);
        PlaylistViewHolder playlistViewHolder =
                new PlaylistViewHolder(playlistView);
        return playlistViewHolder;
    }

    @Override
    public void onBindViewHolder(PlaylistViewHolder playlistViewHolder,
                                 final int viewType) {

        String numberOfSongs = Integer.toString(
                playlists.get(viewType).getNumberOfSongs());
        if(numberOfSongs.equals("1"))
            playlistViewHolder.numberOfSongs.setText(
                    numberOfSongs + " Song");
        else
            playlistViewHolder.numberOfSongs.setText(
                    numberOfSongs + " Songs");

        playlistViewHolder.playlistTitle.setText(
                playlists.get(viewType).getPlaylistName());

        // implementing onClick() in RecyclerView https://stackoverflow.com/a/38090900
        playlistViewHolder.linearlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listenerClick.onPlaylistClicked(viewType);
            }
        });

        playlistViewHolder.linearlayout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                listenerLongClick.onPlaylistLongClicked(viewType);
                return true;
            }
        });
    }

    void updateItems(List<Playlist> allPlaylists){
        playlists = allPlaylists;
        notifyDataSetChanged();
    }
}
