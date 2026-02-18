Project Plan — IndieProject Media Backlog
Project Goals 

By the end of the project, I will deliver:

-A web-based media backlog application with authentication (AWS Cognito)
-A relational database using JPA/Hibernate with multiple one-to-many relationships
-At least one external API integration (TMDB for movies/TV shows)
-Full CRUD functionality for backlog entries
-Logging with Log4J (no System.out.println)
-Unit tests using JUnit
-Deployment to AWS
-A professional portfolio-ready GitHub repository and demo video
 
Week-by-Week Schedule
Weeks 1–2 — Planning & Design

Focus:
Define scope and structure before writing code.

Work Completed
- Problem statement
- User stories (MVP vs Stretch)
- Initial screen wireframes
- Application flow design
- Project plan 
- Weekly reflection

User Stories Addressed
All MVP user stories are defined and organized during this phase.

Resources Identified
- Entity list 
- Required tables 
- Required JSP pages 
- Controller endpoints 
- DAO classes

Weeks 3–4 — Data Model & Architecture (Checkpoint 1)

Focus:
Design the database structure and application architecture.

Entities Designed
- User 
- MediaItem (base class)
  - Movie 
  - TvShow 
- BacklogEntry 
- Review (optional MVP)

Relationships
- User → BacklogEntry (One-to-Many)
- MediaItem → BacklogEntry (One-to-Many)
- MediaItem → Review (One-to-Many)

Deliverables
- ER Diagram (uploaded to GitHub)
- Database schema plan

User Stories Supported
- US-4 Add to Backlog 
- US-6 Update Status 
- US-9 Add Notes 
- US-10 Rate Media 
- US-11 Store Date Added 
- US-12 Store Date Completed

Weeks 5–6 — Persistence Layer (DAO + Hibernate)

Focus:
Implement database connectivity and CRUD operations.

Components
Tables
- user 
- media_item 
- backlog_entry 
- review

Entities
- User 
- MediaItem 
- Movie 
- TvShow 
- BacklogEntry

DAO Classes
- BacklogEntryDAO
- MediaItemDAO (if needed)
- UserDAO (if required)

Unit Tests 
- createBacklogEntry()
- getBacklogEntryById()
- updateBacklogEntry()
- deleteBacklogEntry()

Logging
-Log4J configured 
Replace all System.out.println

User Stories Completed
- US-5 Add to Backlog
- US-7 Remove from Backlog 
- US-8 View Backlog 
- US-6 Update Status 
- US-9 Add Notes 
- US-10 Rate Media

Deliverable
- Working database, DAO layer, and passing unit tests.

Weeks 7–8 — Web Layer + TMDB API Integration

Focus:
Connect the backend to the frontend and integrate external API data.

TMDB API Integration
Service Class 
- TMDBService.java

Functions
- Search movies
- Search TV shows 
- Retrieve media details by TMDB ID 

Stored Metadata 
- Title 
- Overview 
- Release date 
- Poster URL 
- TMDB ID

User Stories Completed
- US-4 Search Media
- US-5 Add to Backlog

Web Layer (JSP + Controllers)
JSP Pages
- login.jsp 
- dashboard.jsp 
- backlog.jsp 
- search.jsp 
- editBacklog.jsp

Controllers
- LoginController
- DashboardController
- BacklogController
- SearchController

User Stories Completed
- US-14 View Dashboard Summary 
- US-15 Filter Backlog 
- US-16 Responsive Interface

Week 9 — Authentication & Deployment (Checkpoint 3)
AWS Cognito Integration

Configuration 
- Create Cognito User Pool 
- Configure authentication flow 
- Restrict backlog visibility by user ID

User Stories Completed
- US-1 Register Account 
- US-2 Login 
- US-3 Session Persistence

AWS Deployment
Deployment Target
- AWS EC2

Verification Checklist
- Application publicly accessible 
- Authentication working 
- Backlog data persists 
- JSP pages correctly display database content

Stretch Goals (If Time Allows)
- US-17 Add Video Games (RAWG API)
- US-18 Track Episode Progress 
- US-19 Media Recommendations 
- US-20 Completion Trend Charts
- US-21 Social Sharing

These will require
- Additional API service layer
- Additional fields in MediaItem
- Possible analytics service layer
- Chart library integration