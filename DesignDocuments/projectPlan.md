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

📅 Week-by-Week Schedule
Weeks 1–2 — Project Definition & Planning

✔ Problem Statement
✔ User Stories (MVP vs Stretch)
✔ Screen Design & Application Flow
✔ Project Plan (this document)
✔ Weekly Reflection pushed to GitHub

Tasks

Finalize project scope (MVP vs Stretch)
Define core entities and relationships
Create basic screen wireframes (dashboard, backlog list, add media, login)

Weeks 3–4 — Architecture & Database Design (Checkpoint 1 Due)
Technical Focus
Finalize data model
Design database schema (tables & relationships)
Database Design Tasks
Define entities:
-User
-MediaItem (base class)
    -Movie
    -TvShow
-BacklogEntry
-Review/Note (optional in MVP)

Define relationships:
User → BacklogEntries (One-to-Many)
MediaItem → BacklogEntries (One-to-Many)
MediaItem → Reviews (One-to-Many)

Create:
ER diagram (for GitHub)


Weeks 5–6 — Persistence Layer (DAO + Hibernate)
Goal: Meet Checkpoint 2 requirements

Tasks:
Configure Hibernate & JPA
Create database (MySQL, PostgreSQL, or AWS RDS)
Implement at least one DAO with full CRUD:
    -BacklogEntryDAO (Create, Read, Update, Delete)
Implement Log4J (replace all System.out.println)
Write JUnit tests for DAO:
    -createBacklogEntry()
    -getBacklogEntryById()
    -updateBacklogEntry()
    -deleteBacklogEntry()

Deliverable by end of Week 7:
Working database
Working DAO with tests
Log4J logging in persistence layer

Weeks 7–8 — Web Layer + API Integration
TMDB API Integration (MVP)

Tasks:
-Register for TMDB API key
-Create Java service to:
    -Search movies
    -Search TV shows
    -Retrieve details by TMDB ID
-Store only necessary metadata in database:
    -Title
    -Overview
    -Release date
    -Poster URL
    -TMDB ID

Web Application (JSP/Servlet or MVC framework)
-Create basic pages:
-Login page (Cognito)
-Dashboard (counts by status)
-Backlog list page
-Add media page (search TMDB + add to backlog)
-Edit backlog entry page (status, rating, notes)

Week 9 — Authentication + Deployment (Checkpoint 3)
AWS Cognito Authentication
-Configure Cognito user pool
-Integrate login/logout with your Java web app
-Ensure each user only sees their own backlog

AWS Deployment
Deploy application to:
-AWS EC2

Verify:
-App is publicly accessible
-Login works
-Backlog data persists
-JSP displays data from database

Submit:
Link to deployed application