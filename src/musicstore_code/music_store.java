package musicstore_code;
/*
 * This creates an in-memory store for recently played songs that can accommodate N songs per user,
 *  with a fixed initial capacity which is 3 in our case.
 * This store has the capability to store a list of song-user pairs , with each song linked to a user. 
 * It fetches recently played songs based on the user and eliminates the least recently played songs when the store becomes full
 */

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.*;

public class music_store 
{
    private int songs_count; // Max number of songs  user can have in their playlist
    private Map<String, LinkedHashSet<String>> recentlyPlayed; //playlist -> songs recently played by user.
    
    public music_store(int songs_count) 
    {
        this.songs_count = songs_count; 
        this.recentlyPlayed = new ConcurrentHashMap<>();
    }

  
    public void Play_songs(String userId, String song) //method to play song and manage playlist
    {
       
        synchronized (recentlyPlayed)  // for thread-safe access and update
        {
        	
            recentlyPlayed.computeIfAbsent(userId, k -> new LinkedHashSet<>()); //Initializing the LinkedHashSet for the user if it doesn't exist already.
            
            LinkedHashSet<String> songs = recentlyPlayed.get(userId); //Retrieving the playlist for the user.
           
            songs.remove(song);// Removing the song from the playlist if it already exists to reorder it to the front
           
            songs.add(song); //  Adding the song to the front of the playlist
            
            if (songs.size() > songs_count) //Checking if the playlist exceeds songs_count
            {
            	
                Iterator<String> iterator = songs.iterator();//Creates an iterator to iterate over the playlist.
                
                iterator.next();//Moves to the oldest song in the playlist.
             
                iterator.remove();// Remove the oldest song 
            }
         
        }
    }

    //Method to retrieve the list of recently played songs for a given userId.
    public List<String> getRecentlyPlayed(String userId) 
    {
    	//Retrieves the playlist (LinkedHashSet) for the user or initializes an empty set if it doesn't exist.
        return recentlyPlayed.getOrDefault(userId, new LinkedHashSet<>())
        		
           .stream()//Converting the LinkedHashSet to a stream 
                              
           .collect(Collectors.toList());//Collecting the stream elements into a List
    }

    public static void main(String[] args) 
    {
        music_store store = new music_store(3);

        store.Play_songs("user1", "S1");
        store.Play_songs("user1", "S2");
        store.Play_songs("user1", "S3");
        
        System.out.println("Current playlist for user1 is : " + store.getRecentlyPlayed("user1"));

        store.Play_songs("user1", "S4");
        System.out.println("Updated playlist for user1 after playing song S4: " + store.getRecentlyPlayed("user1"));

        store.Play_songs("user1", "S2");
        System.out.println("Updated playlist for user1 after playing song S2 again: " + store.getRecentlyPlayed("user1"));

        store.Play_songs("user1", "S1");
        System.out.println("Updated playlist for user1 after playing songs S1 again: " + store.getRecentlyPlayed("user1"));
    }
}