# Ticket Booking System

Java Spring Boot application for user registration, event management, seat booking, and booking history.

## Features
- User registration and management
- Event creation and listing
- Seat creation and availability tracking
- Ticket booking and cancellation
- Booking history by user and event
- Validation and exception handling
- MySQL support for production and H2 in-memory mode for quick testing

## Run for Postman testing
Use the `postman` Spring profile to run the app on an in-memory H2 database.

```bash
./gradlew.bat bootRun --args='--spring.profiles.active=postman'
```

API base URL:

```
http://localhost:8080
```

## Useful endpoints
- `POST /api/users` — create user
- `GET /api/users/{id}` — get user
- `POST /api/events` — create event
- `GET /api/events` — list events
- `POST /api/events/{eventId}/seats` — create seat for event
- `GET /api/events/{eventId}/seats` — list seats by event
- `GET /api/events/{eventId}/seats/available` — list available seats
- `POST /api/bookings` — create booking
- `POST /api/bookings/{bookingId}/cancel` — cancel booking
- `GET /api/bookings/user/{userId}` — user booking history
- `GET /api/bookings/event/{eventId}` — event booking history

## Postman
Import `postman_TicketBooking_collection.json` into Postman to test the APIs.

## Notes
- The `application-postman.properties` profile uses H2 and auto-creates schema.
- For production, update `src/main/resources/application.properties` with MySQL settings.
