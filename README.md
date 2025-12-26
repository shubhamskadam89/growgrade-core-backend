# API Testing Guide (Postman)

This guide provides the API endpoints to test the system using the dummy data seeded by `DataSeeder.java`.

## Headers
For all requests, include the `X-User-Id` header to simulate the logged-in user (since authentication might be bypassed or handled via JWT, but our controllers explicitly check this header for `me` endpoints).

- **Teacher ID**: `11111111-1111-1111-1111-111111111111`
- **Student ID**: `22222222-2222-2222-2222-222222222222`

---

## 1. User Profiles

### Get Teacher Profile
**GET** `http://localhost:8080/api/v1/users/me`
- **Headers**: `X-User-Id: 11111111-1111-1111-1111-111111111111`

### Get Student Profile
**GET** `http://localhost:8080/api/v1/users/me`
- **Headers**: `X-User-Id: 22222222-2222-2222-2222-222222222222`

---

## 2. Quizzes

### Get My Quizzes (Teacher)
**GET** `http://localhost:8080/api/v1/quizzes`
- **Headers**: `X-User-Id: 11111111-1111-1111-1111-111111111111`
- **Response**: List of quizzes. Copy the `id` of "General Knowledge Quiz" for the next steps.

### Get Playable Quiz (Student)
**GET** `http://localhost:8080/api/v1/quizzes/{quizId}/play`
- **Headers**: `X-User-Id: 22222222-2222-2222-2222-222222222222`
- **Path Variables**: Replace `{quizId}` with the ID fetched above.

---

## 3. Rooms

### Create Room (Teacher)
*Optional, since a room with PIN `123456` is already seeded.*
**POST** `http://localhost:8080/api/rooms`
- **Headers**: `X-User-Id: 11111111-1111-1111-1111-111111111111`
- **Body**:
```json
{
  "quizId": "INSERT_QUIZ_ID_HERE"
}
```

---

## 4. Quiz Attempts

### Start Attempt (Student)
**POST** `http://localhost:8080/api/v1/attempts/start`
- **Headers**: `X-User-Id: 22222222-2222-2222-2222-222222222222`
- **Body**:
```json
{
  "quizId": "INSERT_QUIZ_ID_HERE"
}
```

### Submit Attempt (Student)
**POST** `http://localhost:8080/api/v1/attempts/submit`
- **Headers**: `X-User-Id: 22222222-2222-2222-2222-222222222222`
- **Body**:
```json
{
  "quizId": "INSERT_QUIZ_ID_HERE",
  "answers": [
    {
      "questionId": "INSERT_QUESTION_ID_HERE",
      "selectedOptionId": "INSERT_OPTION_ID_HERE"
    }
  ]
}
```
*Note: You can get `questionId` and `optionId` from the "Get Playable Quiz" response.*

### Get My Attempts (Student)
**GET** `http://localhost:8080/api/v1/attempts/me`
- **Headers**: `X-User-Id: 22222222-2222-2222-2222-222222222222`

---

## 5. Analytics

### Get Teacher Analytics
**GET** `http://localhost:8080/api/v1/analytics/teacher`
- **Headers**: `X-User-Id: 11111111-1111-1111-1111-111111111111`

### Get Quiz Analytics (Teacher)
**GET** `http://localhost:8080/api/v1/analytics/quiz/{quizId}`
- **Headers**: `X-User-Id: 11111111-1111-1111-1111-111111111111`

### Get Student Analytics
**GET** `http://localhost:8080/api/v1/analytics/student`
- **Headers**: `X-User-Id: 22222222-2222-2222-2222-222222222222`

---

## 6. WebSocket (Room Join)

- **URL**: `ws://localhost:8080/ws` (or your configured WebSocket endpoint)
- **Topic**: `/rooms/join` (Client -> Server)
- **Headers**: `X-User-Id` (if applicable in handshake)
- **Payload**:
```json
{
  "roomPin": "123456"
}
```
