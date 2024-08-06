Location System
Overview
The Location System is a web application designed for managing user locations and sharing them with other users. It provides functionalities for registering users, creating and sharing locations, and accessing shared locations.

Features
User Management: Register users and retrieve user details.
Location Management: Register and manage locations.
Location Sharing: Share locations with other users and manage access types.
Location Access: View shared locations and manage shared access.
Technologies
Backend: Java 8, Spring Boot 2.6.4
Database: In-memory database
Testing: Spock Framework 1.3-groovy-2.4
Build Management: Gradle 6.8.3
Setup
Prerequisites
Java 8 (OpenJDK)
Gradle 6.8.3
Groovy 4.0.22
Installation
Clone the Repository:

bash
Копировать код
git clone https://github.com/your-username/locationsystem.git
cd locationsystem
Build the Project:

bash
Копировать код
./gradlew build
Run the Application:

bash
Копировать код
./gradlew bootRun
The application will start on http://localhost:8080.

Usage
API Endpoints
User Registration:

Endpoint: POST /users
Request Body: UserDto
Response: User
Get User Locations:

Endpoint: GET /users/{id}/locations
Response: List of Location
Register Location:

Endpoint: POST /locations
Request Body: Location
Response: Location
Share Location:

Endpoint: POST /locations/share
Request Body: ShareLocationDto
Response: ShareLocation
Get Shared Locations:

Endpoint: GET /sharedLocation/all
Response: List of ShareLocation
Get Shared Users:

Endpoint: GET /sharedLocation/{id}/friends
Response: List of User
Testing
To run the tests, use:

bash
Копировать код
./gradlew test
Contributing
Fork the repository.
Create a feature branch (git checkout -b feature/YourFeature).
Commit your changes (git commit -am 'Add new feature').
Push to the branch (git push origin feature/YourFeature).
Create a new Pull Request.
