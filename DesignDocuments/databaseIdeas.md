# Data Model

## Core Entities

### User
Represents an authenticated user of the system.

### MediaItem (Base Entity)
Represents shared media metadata retrieved from external APIs.

Subclasses:
- Movie
- TvShow
- Game (planned)

### BacklogEntry
Represents a user's relationship to a specific media item.

Each entry includes:
- Status (Planned, In Progress, Completed, Dropped)
- User rating
- Personal notes
- Date added
- Date completed
- Favorite flag
- Rewatch / replay count

For TV shows:
- Episodes watched
- Current season and episode

---

## Relationships

- One User → Many BacklogEntries
- One MediaItem → Many BacklogEntries
- One MediaItem → Many Reviews (optional extension)

---

## Media Metadata

Common fields:
- Title
- Overview
- Release date
- Poster URL
- Genres
- External API ID

Movie-specific:
- Runtime
- MPAA rating

TV-specific:
- Number of seasons
- Total episodes
- First air date
- Ongoing or ended

Game-specific (stretch):
- Platforms
- Average playtime
- Developer / publisher
