/*
Playlist
Dan Pike
7/24/2020
 */

package TopThings;

import java.util.ArrayList;

public class Playlist{
    private final ArrayList<Song> playlist = new ArrayList<>();

    public Playlist() {
    }

    public void addSong(Song song) {
        this.playlist.add(song);
    }

    public void addSong(int index, Song song) {
        this.playlist.add(index, song);
    }

    public Song get(int i){
        return playlist.get(i);
    }

    public boolean containsSong(Song song) {
        boolean ants = false;

        for (int i = 0; i < playlist.size(); i++) {
            //returns true if the song name and artist match
            if (song.getName().equalsIgnoreCase(playlist.get(i).getName()) &&
                    //song.getAlbum().equalsIgnoreCase(playlist.get(i).getAlbum()) &&
                    song.getArtist().equalsIgnoreCase(playlist.get(i).getArtist())) {
                ants = true;
                break;
            }
        }
        return ants;
}

    //returns index of a certain song
    public int search(Playlist playlist, Song song){
        int j = -1;
        for (int i = 0; i < playlist.size(); i++) {
            if (song.getName().equalsIgnoreCase(playlist.get(i).getName()) &&
                    song.getArtist().equalsIgnoreCase(playlist.get(i).getArtist())) {
                j = i;
                break;
            }
        }
        return j;
    }

    public int size(){
        return playlist.size();
    }

    public ArrayList<Song> getSong(){
        return playlist;
    }

    public void sort() {
        int n = playlist.size();

        //sorts from lowest to highest score
        for(int f = 0; f < n - 1; f++) {
            int min_idx = f;
            for (int j = f + 1; j < n; j++){
                if (playlist.get(j).getScore() < playlist.get(min_idx).getScore())
                    min_idx = j;

                //tiebreaker for score
                else if (playlist.get(j).getScore() == playlist.get(min_idx).getScore()) {
                    //lower highscore = lower on list
                    if (playlist.get(j).getMaxScore() < playlist.get(min_idx).getMaxScore())
                        min_idx = j;

                    //highscore tiebreaker
                    else if (playlist.get(j).getMaxScore() == playlist.get(min_idx).getMaxScore())

                        //on less lists = lower on list, after this it leaves things in the input order i think
                        if (playlist.get(j).getSongCount() < playlist.get(min_idx).getSongCount())
                        min_idx = j;
                }

                Song temp =  playlist.get(min_idx);
                playlist.set(min_idx, playlist.get(f));
                playlist.set(f, temp);
            }
        }

    }

    public Playlist topList(){

        Playlist topStyle = new Playlist();

        for (int tops = 0; tops < playlist.size(); tops++){
            topStyle.addSong(tops, playlist.get(playlist.size() - (tops + 1)));
            playlist.get(playlist.size() - (tops + 1)).setRank(tops + 1);
        }

return topStyle;
    }

    //work on these methods
    public String topArtist() {
        String topArtist = "";
        String checkArtist = "";
        int topScore = 0;
        int count = 0;
        for (int i = 0; i < playlist.size(); i++) {
            count = 0;
            for (int j = 0; j < playlist.size(); j++) {
                if ((playlist.get(i).getArtist()).equalsIgnoreCase((playlist.get(j)).getArtist()))
                    count++;
                if (count > topScore) {
                    topArtist = playlist.get(i).getArtist();
                    topScore = count;
                }
            }
        }
        return (topArtist + " with " + topScore + " songs");
    }

    public int topArtistCount(){
        int topScore = 0;
        int count = 0;

        for (int i = 0; i < playlist.size(); i++) {
            count = 0;

            for (int j = 0; j < playlist.size(); j++) {
                if ((playlist.get(i).getArtist()).equalsIgnoreCase(playlist.get(j).getArtist()))
                    count++;
                }if (count > topScore) {
                    topScore = count;
            }
        }
        return topScore;
    }

    public String toString(){
        String str = "";
        for (Song song: getSong()){
            str = str + "\n" + song.toString();
        }
        return str;
    }

}

