package com.infovergne.restgw.config;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.util.List;
import java.util.Map;


@Configuration
public class KodiConfig {

    private final Environment env;

    public KodiConfig(Environment env) {
        this.env = env;
    }

    public String getKodiUrl() {
        return env.getProperty("project.kodi.url");
    }

    public String getToken() {
        return env.getProperty("project.token");
    }

    public final static JSONArray PAYLOAD_PLAYPAUSE = new JSONArray(List.of(new JSONObject(Map.of("jsonrpc", "2.0", "id", 1,
            "method", "Player.PlayPause", "params", new JSONObject(Map.of("playerid", 0))))));

    public final static JSONArray PAYLOAD_STOP = new JSONArray(List.of(
            new JSONObject(Map.of("jsonrpc", "2.0", "id", 1, "method", "Player.Stop", "params", new JSONObject(Map.of("playerid", 0)))),
            new JSONObject(Map.of("jsonrpc", "2.0", "id", 1, "method", "Playlist.Clear", "params", new JSONObject(Map.of("playlistid", 0))))
    ));

    public final static JSONArray PAYLOAD_VOL_UP = new JSONArray(List.of(
            new JSONObject(Map.of("jsonrpc", "2.0", "id", 1, "method", "Application.SetVolume", "params", new JSONObject(Map.of("volume", "increment")))),
            new JSONObject(Map.of("jsonrpc", "2.0", "id", 1, "method", "Application.SetVolume", "params", new JSONObject(Map.of("volume", "increment")))),
            new JSONObject(Map.of("jsonrpc", "2.0", "id", 1, "method", "Application.SetVolume", "params", new JSONObject(Map.of("volume", "increment")))),
            new JSONObject(Map.of("jsonrpc", "2.0", "id", 1, "method", "Application.SetVolume", "params", new JSONObject(Map.of("volume", "increment"))))
    ));

    public final static JSONArray PAYLOAD_VOL_DOWN = new JSONArray(List.of(
            new JSONObject(Map.of("jsonrpc", "2.0", "id", 1, "method", "Application.SetVolume", "params", new JSONObject(Map.of("volume", "decrement")))),
            new JSONObject(Map.of("jsonrpc", "2.0", "id", 1, "method", "Application.SetVolume", "params", new JSONObject(Map.of("volume", "decrement")))),
            new JSONObject(Map.of("jsonrpc", "2.0", "id", 1, "method", "Application.SetVolume", "params", new JSONObject(Map.of("volume", "decrement")))),
            new JSONObject(Map.of("jsonrpc", "2.0", "id", 1, "method", "Application.SetVolume", "params", new JSONObject(Map.of("volume", "decrement"))))
    ));


    public final static JSONArray PAYLOAD_RADIO_CAMPUS = new JSONArray(List.of(
            new JSONObject(Map.of("jsonrpc", "2.0", "id", 1, "method", "Player.Stop", "params", new JSONObject(Map.of("playerid", 0)))),
            new JSONObject(Map.of("jsonrpc", "2.0", "id", 1, "method", "Playlist.Clear", "params", new JSONObject(Map.of("playlistid", 0)))),
            new JSONObject(Map.of("jsonrpc", "2.0", "id", 1, "method", "Player.Open", "params", new JSONObject(Map.of("item", new JSONObject(Map.of("file", "https://live.radio-campus.org:8002/clermont-ferrand"))))))
    ));

    public final static JSONArray PAYLOAD_RADIO_NOVA = new JSONArray(List.of(
            new JSONObject(Map.of("jsonrpc", "2.0", "id", 1, "method", "Player.Stop", "params", new JSONObject(Map.of("playerid", 0)))),
            new JSONObject(Map.of("jsonrpc", "2.0", "id", 1, "method", "Player.Open", "params", new JSONObject(Map.of("item", new JSONObject(Map.of("file", "https://novazz.ice.infomaniak.ch/novazz-128.mp3"))))))
    ));

    public final static JSONArray PAYLOAD_RADIO_CLASSIQUE = new JSONArray(List.of(
            new JSONObject(Map.of("jsonrpc", "2.0", "id", 1, "method", "Player.Stop", "params", new JSONObject(Map.of("playerid", 0)))),
            new JSONObject(Map.of("jsonrpc", "2.0", "id", 1, "method", "Playlist.Clear", "params", new JSONObject(Map.of("playlistid", 0)))),
            new JSONObject(Map.of("jsonrpc", "2.0", "id", 1, "method", "Player.Open", "params", new JSONObject(Map.of("item", new JSONObject(Map.of("file", "https://novazzradioclassique.ice.infomaniak.ch/radioclassique-high.mp3"))))))
    ));

    public final static JSONArray PAYLOAD_RADIO_FRANCE_INTER = new JSONArray(List.of(
            new JSONObject(Map.of("jsonrpc", "2.0", "id", 1, "method", "Player.Stop", "params", new JSONObject(Map.of("playerid", 0)))),
            new JSONObject(Map.of("jsonrpc", "2.0", "id", 1, "method", "Playlist.Clear", "params", new JSONObject(Map.of("playlistid", 0)))),
            new JSONObject(Map.of("jsonrpc", "2.0", "id", 1, "method", "Player.Open", "params", new JSONObject(Map.of("item", new JSONObject(Map.of("file", "http://direct.franceinter.fr/live/franceinter-midfi.mp3"))))))
    ));


    public final static JSONArray PAYLOAD_PLAYLIST_TOUS = new JSONArray(List.of(
            new JSONObject(Map.of("jsonrpc", "2.0", "id", 1, "method", "Player.Stop", "params", new JSONObject(Map.of("playerid", 0)))),
            new JSONObject(Map.of("jsonrpc", "2.0", "id", 1, "method", "Playlist.Clear", "params", new JSONObject(Map.of("playlistid", 0)))),
            new JSONObject(Map.of("jsonrpc", "2.0", "id", 1, "method", "Player.SetShuffle", "params", new JSONObject(Map.of("playerid", 0, "suffle", true)))),
            new JSONObject(Map.of("jsonrpc", "2.0", "id", 1, "method", "Playlist.Add", "params", new JSONObject(Map.of("playlistid", 0, "item", new JSONObject(Map.of("directory", "special://profile/playlists/music/party-mode_tous.xsp")))))),
            new JSONObject(Map.of("jsonrpc", "2.0", "id", 1, "method", "Player.Open", "params", new JSONObject(Map.of("item", new JSONObject(Map.of("playlistid", 0, "position", 0))))))
    ));

    public final static JSONArray PAYLOAD_PLAYLIST_ALBUMS = new JSONArray(List.of(
            new JSONObject(Map.of("jsonrpc", "2.0", "id", 1, "method", "Player.Stop", "params", new JSONObject(Map.of("playerid", 0)))),
            new JSONObject(Map.of("jsonrpc", "2.0", "id", 1, "method", "Playlist.Clear", "params", new JSONObject(Map.of("playlistid", 0)))),
            new JSONObject(Map.of("jsonrpc", "2.0", "id", 1, "method", "Player.SetShuffle", "params", new JSONObject(Map.of("playerid", 0, "suffle", true)))),
            new JSONObject(Map.of("jsonrpc", "2.0", "id", 1, "method", "Playlist.Add", "params", new JSONObject(Map.of("playlistid", 0, "item", new JSONObject(Map.of("directory", "special://profile/playlists/music/party-mode_albums.xsp")))))),
            new JSONObject(Map.of("jsonrpc", "2.0", "id", 1, "method", "Player.Open", "params", new JSONObject(Map.of("item", new JSONObject(Map.of("playlistid", 0, "position", 0))))))
    ));

    public final static JSONArray PAYLOAD_PLAYLIST_OST = new JSONArray(List.of(
            new JSONObject(Map.of("jsonrpc", "2.0", "id", 1, "method", "Player.Stop", "params", new JSONObject(Map.of("playerid", 0)))),
            new JSONObject(Map.of("jsonrpc", "2.0", "id", 1, "method", "Playlist.Clear", "params", new JSONObject(Map.of("playlistid", 0)))),
            new JSONObject(Map.of("jsonrpc", "2.0", "id", 1, "method", "Player.SetShuffle", "params", new JSONObject(Map.of("playerid", 0, "suffle", true)))),
            new JSONObject(Map.of("jsonrpc", "2.0", "id", 1, "method", "Playlist.Add", "params", new JSONObject(Map.of("playlistid", 0, "item", new JSONObject(Map.of("directory", "special://profile/playlists/music/party-mode_generiques.xsp")))))),
            new JSONObject(Map.of("jsonrpc", "2.0", "id", 1, "method", "Player.Open", "params", new JSONObject(Map.of("item", new JSONObject(Map.of("playlistid", 0, "position", 0))))))
    ));

    public final static JSONArray PAYLOAD_PLAYLIST_CLASSICAL = new JSONArray(List.of(
            new JSONObject(Map.of("jsonrpc", "2.0", "id", 1, "method", "Player.Stop", "params", new JSONObject(Map.of("playerid", 0)))),
            new JSONObject(Map.of("jsonrpc", "2.0", "id", 1, "method", "Playlist.Clear", "params", new JSONObject(Map.of("playlistid", 0)))),
            new JSONObject(Map.of("jsonrpc", "2.0", "id", 1, "method", "Player.SetShuffle", "params", new JSONObject(Map.of("playerid", 0, "suffle", true)))),
            new JSONObject(Map.of("jsonrpc", "2.0", "id", 1, "method", "Playlist.Add", "params", new JSONObject(Map.of("playlistid", 0, "item", new JSONObject(Map.of("directory", "special://profile/playlists/music/party-mode_classic.xsp")))))),
            new JSONObject(Map.of("jsonrpc", "2.0", "id", 1, "method", "Player.Open", "params", new JSONObject(Map.of("item", new JSONObject(Map.of("playlistid", 0, "position", 0))))))
    ));
    public final static JSONArray PAYLOAD_PLAYLIST_DISNEY = new JSONArray(List.of(
            new JSONObject(Map.of("jsonrpc", "2.0", "id", 1, "method", "Player.Stop", "params", new JSONObject(Map.of("playerid", 0)))),
            new JSONObject(Map.of("jsonrpc", "2.0", "id", 1, "method", "Playlist.Clear", "params", new JSONObject(Map.of("playlistid", 0)))),
            new JSONObject(Map.of("jsonrpc", "2.0", "id", 1, "method", "Player.SetShuffle", "params", new JSONObject(Map.of("playerid", 0, "suffle", true)))),
            new JSONObject(Map.of("jsonrpc", "2.0", "id", 1, "method", "Playlist.Add", "params", new JSONObject(Map.of("playlistid", 0, "item", new JSONObject(Map.of("directory", "special://profile/playlists/music/party-mode_disney.xsp")))))),
            new JSONObject(Map.of("jsonrpc", "2.0", "id", 1, "method", "Player.Open", "params", new JSONObject(Map.of("item", new JSONObject(Map.of("playlistid", 0, "position", 0))))))
    ));

    public final static JSONArray PAYLOAD_PLAYLIST_VICTOR = new JSONArray(List.of(
            new JSONObject(Map.of("jsonrpc", "2.0", "id", 1, "method", "Player.Stop", "params", new JSONObject(Map.of("playerid", 0)))),
            new JSONObject(Map.of("jsonrpc", "2.0", "id", 1, "method", "Playlist.Clear", "params", new JSONObject(Map.of("playlistid", 0)))),
            new JSONObject(Map.of("jsonrpc", "2.0", "id", 1, "method", "Player.SetShuffle", "params", new JSONObject(Map.of("playerid", 0, "suffle", true)))),
            new JSONObject(Map.of("jsonrpc", "2.0", "id", 1, "method", "Playlist.Add", "params", new JSONObject(Map.of("playlistid", 0, "item", new JSONObject(Map.of("directory", "special://profile/playlists/music/party-mode_victor.xsp")))))),
            new JSONObject(Map.of("jsonrpc", "2.0", "id", 1, "method", "Player.Open", "params", new JSONObject(Map.of("item", new JSONObject(Map.of("playlistid", 0, "position", 0))))))
    ));

    public final static JSONArray PAYLOAD_PLAYLIST_CHANSON = new JSONArray(List.of(
            new JSONObject(Map.of("jsonrpc", "2.0", "id", 1, "method", "Player.Stop", "params", new JSONObject(Map.of("playerid", 0)))),
            new JSONObject(Map.of("jsonrpc", "2.0", "id", 1, "method", "Playlist.Clear", "params", new JSONObject(Map.of("playlistid", 0)))),
            new JSONObject(Map.of("jsonrpc", "2.0", "id", 1, "method", "Player.SetShuffle", "params", new JSONObject(Map.of("playerid", 0, "suffle", true)))),
            new JSONObject(Map.of("jsonrpc", "2.0", "id", 1, "method", "Playlist.Add", "params", new JSONObject(Map.of("playlistid", 0, "item", new JSONObject(Map.of("directory", "special://profile/playlists/music/party-mode_chanson.xsp")))))),
            new JSONObject(Map.of("jsonrpc", "2.0", "id", 1, "method", "Player.Open", "params", new JSONObject(Map.of("item", new JSONObject(Map.of("playlistid", 0, "position", 0))))))
    ));

    public final static JSONArray PAYLOAD_PLAYLIST_WORLD = new JSONArray(List.of(
            new JSONObject(Map.of("jsonrpc", "2.0", "id", 1, "method", "Player.Stop", "params", new JSONObject(Map.of("playerid", 0)))),
            new JSONObject(Map.of("jsonrpc", "2.0", "id", 1, "method", "Playlist.Clear", "params", new JSONObject(Map.of("playlistid", 0)))),
            new JSONObject(Map.of("jsonrpc", "2.0", "id", 1, "method", "Player.SetShuffle", "params", new JSONObject(Map.of("playerid", 0, "suffle", true)))),
            new JSONObject(Map.of("jsonrpc", "2.0", "id", 1, "method", "Playlist.Add", "params", new JSONObject(Map.of("playlistid", 0, "item", new JSONObject(Map.of("directory", "special://profile/playlists/music/party-mode_world.xsp")))))),
            new JSONObject(Map.of("jsonrpc", "2.0", "id", 1, "method", "Player.Open", "params", new JSONObject(Map.of("item", new JSONObject(Map.of("playlistid", 0, "position", 0))))))
    ));


}
