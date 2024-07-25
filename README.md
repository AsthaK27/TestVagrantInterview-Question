# TestVagrantInterview-Question


# Music Store

The **Music Store** project implements an in-memory store for managing recently played songs for users. It allows users to maintain a playlist of their recently played songs with a specified maximum capacity. When the playlist reaches its capacity, it automatically removes the least recently played songs to accommodate new ones.

## Features

- **Play Song**: Adds a song to a user's playlist. If the playlist exceeds the specified maximum capacity, it removes the least recently played song to make space.
  
- **Get Recently Played**: Retrieves the list of recently played songs for a given user.

## Implementation Details

The project uses Java and includes the following components:

- **music_store.java**: Contains the main implementation of the `music_store` class, which manages the playlist functionality.
  
- **Usage Example (main method)**: Demonstrates how to use the `music_store` class to play songs and retrieve the recently played playlist for a user.

## Installation

To run the Music Store project:

1. Clone the repository:

   ```bash
   git clone https://github.com/your-username/music-store.git
   cd music-store
   ```

2. Ensure you have Java Development Kit (JDK) installed (version 8 or higher).

3. Compile the Java file:

   ```bash
   javac music_store.java
   ```

4. Run the example provided in the `main` method:

   ```bash
   java music_store
   ```

## Usage

Modify the `main` method in `music_store.java` to integrate the `music_store` functionality into your application. Here's a quick overview:

- Initialize a `music_store` object with a maximum number of songs a user can have in their playlist.
- Use `playSong(userId, song)` to add a song to a user's playlist.
- Use `getRecentlyPlayed(userId)` to retrieve the recently played songs for a user.

