# Location System

## Overview

The Location System is a web application designed for managing user locations and sharing them with other users. It provides functionalities for registering users, creating and sharing locations, and accessing shared locations.

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
      "name": "John Doe",
      "email": "john.doe@example.com"
    }
    ```
  - **Response:**
    - **HTTP Status 201 Created**
    - **Body:**
      ```json
      {
        "id": 1,
        "name": "John Doe",
        "email": "john.doe@example.com"
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
          "name": "Office",
          "address": "456 Elm St"
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
          "name": "John Doe",
          "email": "john.doe@example.com"
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
      "name": "Office",
      "address": "456 Elm St"
    }
    ```
  - **Response:**
    - **HTTP Status 201 Created**
    - **Body:**
      ```json
      {
        "id": 1,
        "name": "Office",
        "address": "456 Elm St"
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
          "name": "Office",
          "address": "456 Elm St"
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
          "name": "Jane Smith",
          "email": "jane.smith@example.com"
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
