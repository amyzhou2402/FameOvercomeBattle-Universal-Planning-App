# FameOvercomeBattle-Universal-Planning-App

Event Planning Application

You are working for a large event-planning company based in Sydney, Australia that helps people
plan events. Examples of events include weddings, business functions, birthday parties, religious
ceremonies, and other such celebrations. You are to build a “Event Planning Application”, using
JDK 11 with OpenJFX 11 as a GUI platform and SQLite as a data storage platform.

MVP
- When an administrator logs in, they need to be able to do the following:
  • See which events (`EVENT`) already exist, create a new event or edit an existing event
  • See which guests (`GUEST`) already exist, create a new guest or edit an existing guest
  • Invite guests to an event (`INVITATION`):
    o There should be a way to select several (at least 10) existing guests (`GUEST`)
    from the database in a single action, and then invite them to the event.
    o There should be a way to insert several (at least 10) new guests (`GUEST`) into
    the database in a single action, if their records are not currently in the database,
    and then invite them to be invited to the event.
    o Each invitation (`INVITATION`) identifies the admin (`ADMIN`) who creates it.

When a guest logs in, they need to be able to do the following:
  • See which events they have been invited to (`INVITATION`).
  • Respond to an invitation (`RSVP`) with a decision, i.e., Yes or No, and leave notes about
  dietary requirements if they have any (e.g. vegetarian food only).

For each event, administrators need to be able to see:
  • Which guests have been invited;
  • For each guest invited, whether they have responded with an RSVP decision (yes/no);
  • A pie chart showing how many guests are confirmed RSVP = “Yes”, how many guests are
  confirmed RSVP = “No”, and how many guests have yet to respond.

Your software application must be able to navigate between screens using a navigation aid such
as a toolbar, tab bar, master-detail, or menu bar.
Your software application also must have an “About” screen with a copyright statement

Additional Features
- Invitation PDF
- Event Runsheet Builder
