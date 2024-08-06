# Location System

## Overview
It’s required to implement a web-server that will provide functionality for users and his locations. 
Person can have multiple locations (zero or one are possible), for example home, parent's home, office, etc. Owner of the location can share location with friends (users from the system). Owner can share location to friends either with read only access or with admin access. Admin access allows friend user to add other friends to the owner's location. One user can have his own locations and be added to different shared locations. 

## Features

- **User Management:** Register users and retrieve user details.
- **Location Management:** Register and manage locations.
- **Location Sharing:** Share locations with other users and manage access types.
- **Location Access:** View shared locations and manage shared access.

## Technologies
- **Backend:** Java 8, Spring Boot 2.6.4
- **Database:** In-memory database
- **Testing:** Spock Framework 1.3-groovy-2.4
- **Build Management:** Gradle 6.8.3
- **Mapping:**  MapStruct 1.4.2.Final 
## Setup

### Prerequisites

- Java 8 (OpenJDK)
- Gradle 6.8.3
- Groovy 4.0.22

### Installation

1. **Clone the Repository:**

   ```bash
   git clone https://github.com/your-username/locationsystem.git
   cd locationsystem
## Usage

### API Endpoints

#### User Endpoints

- **Register User:**
  - **Endpoint:** `POST /users`
  - **Description:** Registers a new user.
  - **Request Body:**
    ```json
    {
      "name": "Aman Kalabay",
      "email": "aman@example.com"
    }
    ```
  - **Response:**
    - **HTTP Status 201 Created**
    - **Body:**
      ```json
      {
        "id": 1,
        "name": "Aman Kalabay",
        "email": "aman@example.com"
      }
      ```

- **Get User Locations:**
  - **Endpoint:** `GET /users/{id}/locations`
  - **Description:** Retrieves locations associated with a specific user.
  - **Response:**
    - **HTTP Status 200 OK**
    - **Body:**
      ```json
      [
        {
          "id": 1,
          "name": "Home",
          "address": "97 Estemesove st"
        }
      ]
      ```

- **Get All Users:**
  - **Endpoint:** `GET /users/all`
  - **Description:** Retrieves a list of all users.
  - **Response:**
    - **HTTP Status 200 OK**
    - **Body:**
      ```json
      [
        {
          "name": "Aman Kalabay",
          "email": "aman@example.com"
        }
      ]
      ```

#### Location Endpoints

- **Register Location:**
  - **Endpoint:** `POST /locations`
  - **Description:** Registers a new location.
  - **Request Body:**
    ```json
    {
       "name": "Home",
       "address": "97 Estemesove st",
       "owner":{
          "id":1
       }
    }
    ```
  - **Response:**
    - **HTTP Status 201 Created**
    - **Body:**
      ```json
      {
        "id": 1,
        "name": "Home",
        "address": "97 Estemesove st"
      }
      ```

- **Share Location:**
  - **Endpoint:** `POST /locations/share`
  - **Description:** Shares a location with another user.
  - **Request Body:**
    ```json
    {
      "ownerId": 1,
      "friendId": 2,
      "accessType": "READ_ONLY"
    }
    ```
  - **Response:**
    - **HTTP Status 200 OK**
    - **Body:**
      ```json
      {
        "id": 1,
        "ownerId": 1,
        "friendId": 2,
        "accessType": "READ_ONLY"
      }
      ```

- **Get Locations by User ID:**
  - **Endpoint:** `GET /locations/{id}`
  - **Description:** Retrieves locations associated with a specific user ID.
  - **Response:**
    - **HTTP Status 200 OK**
    - **Body:**
      ```json
      [
        {
          "id": 1,
          "name": "Home",
          "address": "97 Estemesove st"
        }
      ]
      ```

#### Shared Location Endpoints

- **Get All Shared Locations:**
  - **Endpoint:** `GET /sharedLocation/all`
  - **Description:** Retrieves a list of all shared locations.
  - **Response:**
    - **HTTP Status 200 OK**
    - **Body:**
      ```json
      [
        {
          "id": 1,
          "ownerId": 1,
          "friendId": 2,
          "accessType": "READ_ONLY"
        }
      ]
      ```

- **Get Shared Users by Location ID:**
  - **Endpoint:** `GET /sharedLocation/{id}/friends`
  - **Description:** Retrieves users who have access to a specific location.
  - **Response:**
    - **HTTP Status 200 OK**
    - **Body:**
      ```json
      [
        {
          "id": 2,
          "name": "Ol",
          "email": "ol@example.com"
        }
      ]
      ```

- **Get Shared Locations by User ID:**
  - **Endpoint:** `GET /sharedLocation/{id}/sharedFriends`
  - **Description:** Retrieves shared locations for a specific user ID.
  - **Response:**
    - **HTTP Status 200 OK**
    - **Body:**
      ```json
      [
        {
          "id": 1,
          "ownerId": 1,
          "friendId": 2,
          "accessType": "READ_ONLY"
        }
      ]
      ```

- **Get Share Friends Location:**
  - **Endpoint:** `POST /sharedLocation/shareFriendsLocation`
  - **Description:** Retrieves shared location information based on the provided IDs.
  - **Request Body:**
    ```json
    {
      "ownerId": 1,
      "friendId": 2,
      "toId": 3
    }
    ```
  - **Response:**
    - **HTTP Status 200 OK**
    - **Body:**
      ```json
      {
        "id": 1,
        "ownerId": 1,
        "friendId": 2,
        "accessType": "READ_ONLY"
      }
      ```
