package com.example.melodia;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.example.melodia.Objects.*;
import com.example.melodia.logic.AccessPlaylistTest;

import comp3350.melodia.objects.Account;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        AlbumTest.class,
        ArtistTest.class,
        GenreTest.class,
        PlaylistTest.class,
        SongTest.class,
        AccountTest.class,
        AccessPlaylistTest.class
})
public class AllTests {

}