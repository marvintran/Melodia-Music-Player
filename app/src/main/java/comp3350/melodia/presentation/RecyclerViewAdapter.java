package comp3350.melodia.presentation;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import comp3350.melodia.R;
import comp3350.melodia.objects.Song;


// inspiration was taken from the following:
// https://developer.android.com/guide/topics/ui/layout/recyclerview
// https://code.tutsplus.com/tutorials/android-from-scratch-understanding-adapters-and-adapter-views--cms-26646
// https://guides.codepath.com/android/using-the-recyclerview
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.SongViewHolder>{

    ArrayList<Song> songs;

    // creates a SongViewHolder which contains references to all the views in this view row
    public static class SongViewHolder extends RecyclerView.ViewHolder {
        TextView songName;
        TextView artistName;
        TextView trackDuration;

        SongViewHolder(View itemView) {
            super(itemView);
            songName = (TextView)itemView.findViewById(R.id.song_name);
            artistName = (TextView)itemView.findViewById(R.id.artist_name);
            trackDuration = (TextView)itemView.findViewById(R.id.track_duration);
        }
    }

    // constructor
    public RecyclerViewAdapter(ArrayList<Song> songs){
        this.songs = songs;
    }

    // RecyclerView.Adapter has three abstract methods that we must override
    // getItemCount(), onCreateViewHolder() and onBindViewHolder()


    // returns the size of the list of songs to display
    @Override
    public int getItemCount() {
        return songs.size();
    }

    // creates a view row for an item where views contained in the view row are stored in a SongViewHolder
    // does not give the views in the SongViewHolder any data,
    @Override
    public SongViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a view row as defined by our item.xml file
        View songView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        // create a songViewHolder which contains references to the views for this row
        SongViewHolder songViewHolder = new SongViewHolder(songView);
        return songViewHolder;
    }

    // populates the views contained in this SongViewHolder
    @Override
    public void onBindViewHolder(SongViewHolder songViewHolder, int viewType) {
        songViewHolder.songName.setText( songs.get(viewType).getSongName() );
        songViewHolder.artistName.setText( songs.get(viewType).getArtistName() );
        songViewHolder.trackDuration.setText( Integer.toString(songs.get(viewType).getSongTime()) );
    }
}
