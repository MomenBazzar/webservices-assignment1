![ER Diagram](ERD.png "ER Diagram")  


---
### Given how to use the api when running
##### Note1: Please note that all urls start with (localhost:8080)
##### Note2: all data are fake and auto generated
---
## Patients API  
---


| HTTP Method | URL Path            | HTTP Status Code | Description            | Sample Request Body                                                                                                | Sample Response                                                                                                                                                        |
|-------------|---------------------|-----------------|------------------------|-------------------------------------------------------------------------------------------------------------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| GET         | /patients/{id}      | 200/404         | Get patient by ID       | -                                                                                                                 | 200 OK: `{ "id": 1, "firstName": "John", "lastName": "Doe", "birthDate": "1990-01-01", "gender": "MALE", "address": "123 Main St", "email": "johndoe@example.com" } |
| POST        | /patients           | 201/400         | Add a new patient       | `{ "firstName": "John", "lastName": "Doe", "birthDate": "1990-01-01", "gender": "MALE", "address": "123 Main St", "email": "johndoe@example.com" }                       | 201 Created: `{ "id": 1, "firstName": "John", "lastName": "Doe", "birthDate": "1990-01-01", "gender": "MALE", "address": "123 Main St", "email": "johndoe@example.com" } |
| PUT         | /patients/{id}      | 200/404         | Update a patient        | `{ "firstName": "John", "lastName": "Doe", "birthDate": "1990-01-01", "address": "456 Elm St", "email": "johndoe@example.com" }                                              | 200 OK: `{ "id": 1, "firstName": "John", "lastName": "Doe", "birthDate": "1990-01-01", "gender": "MALE", "address": "456 Elm St", "email": "johndoe@example.com" } |
| DELETE      | /patients/{id}      | 204/404         | Delete a patient        | -                                                                                                                 | 204 No Content                                                                                                                                                         |
| GET         | /patients           | 200             | Get all patients        | -                                                                                                                 | 200 OK: `[{ "id": 1, "firstName": "John", "lastName": "Doe", "birthDate": "1990-01-01", "gender": "MALE", "address": "123 Main St", "email": "johndoe@example.com" }] |


---
## Doctors API
---

| HTTP Method | URL Path            | HTTP Status Code | Description           | Sample Request Body                                                                                                                                                                | Sample Response                                                                                                                                                                |
|-------------|---------------------|-----------------|-----------------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| GET         | /doctors/{id}       | 200/404         | Get doctor by ID      | -                                                                                                                                                                                 | 200 OK: `{ "id": 1, "firstName": "John", "lastName": "Doe", "birthDate": "1990-01-01", "address": "123 Main St", "email": "johndoe@example.com", "gender": "MALE" }           |
| POST        | /doctors            | 201/400         | Add a new doctor      | `{ "firstName": "John", "lastName": "Doe", "birthDate": "1990-01-01", "gender": "MALE", "address": "123 Main St", "email": "johndoe@example.com", "specialty": "Cardiology" }       | 201 Created: `{ "id": 1, "firstName": "John", "lastName": "Doe", "birthDate": "1990-01-01", "address": "123 Main St", "email": "johndoe@example.com", "gender": "MALE" }         |
| PUT         | /doctors/{id}       | 200/404         | Update a doctor       | `{ "firstName": "John", "lastName": "Doe", "birthDate": "1990-01-01", "address": "456 Elm St", "email": "johndoe@example.com", "specialty": "Neurology" }                          | 200 OK: `{ "id": 1, "firstName": "John", "lastName": "Doe", "birthDate": "1990-01-01", "address": "456 Elm St", "email": "johndoe@example.com", "gender": "MALE" }            |
| DELETE      | /doctors/{id}       | 204/404         | Delete a doctor       | -                                                                                                                                                                                 | 204 No Content                                                                                                                                                                 |
| GET         | /doctors            | 200             | Get all doctors       | -                                                                                                                                                                                 | 200 OK: `[{ "id": 1, "firstName": "John", "lastName": "Doe", "birthDate": "1990-01-01", "address": "123 Main St", "email": "johndoe@example.com", "gender": "MALE" }]           |

---
## Medication API
---


| HTTP Method | URL Path               | HTTP Status Code | Description             | Sample Request Body                                                                                                                                                         | Sample Response                                                                                                                                                        |
|-------------|------------------------|-----------------|-------------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| GET         | /medications/{id}      | 200/404         | Get medication by ID    | -                                                                                                                                                                          | 200 OK: `{ "id": 1, "medicationName": "Medicine", "description": "Medication description", "dosage": "2 pills per day" }                                             |
| POST        | /medications           | 201/400         | Add a new medication    | `{ "medicationName": "Medicine", "description": "Medication description", "dosage": "2 pills per day" }                                                                   | 201 Created: `{ "id": 1, "medicationName": "Medicine", "description": "Medication description", "dosage": "2 pills per day" }                                           |
| PUT         | /medications/{id}      | 200/404         | Update a medication     | `{ "medicationName": "Medicine", "description": "Updated description", "dosage": "3 pills per day" }                                                                     | 200 OK: `{ "id": 1, "medicationName": "Medicine", "description": "Updated description", "dosage": "3 pills per day" }                                                   |
| DELETE      | /medications/{id}      | 204/404         | Delete a medication     | -                                                                                                                                                                          | 204 No Content                                                                                                                                                         |
| GET         | /medications           | 200             | Get all medications     | -                                                                                                                                                                          | 200 OK: `[{ "id": 1, "medicationName": "Medicine", "description": "Medication description", "dosage": "2 pills per day" }]                                             |



---
## Appointment API
---  



| HTTP Method | URL Path                 | HTTP Status Code | Description             | Sample Request Body                                                                                                                                                               | Sample Response                                                                                                                                                              |
|-------------|--------------------------|-----------------|-------------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| GET         | /appointments/{id}       | 200/404         | Get appointment by ID   | -                                                                                                                                                                                | 200 OK: `{ "id": 1, "appointmentDate": "2023-05-22T10:30:00", "doctor": { "id": 1, "firstName": "John", "lastName": "Doe", ... }, "patient": { "id": 1, "firstName": "Jane", ... }, "medication": { "id": 1, "medicationName": "Medicine", ... } } |
| POST        | /appointments            | 201/400         | Add a new appointment    | `{ "appointmentDate": "2023-05-22T10:30:00", "doctorId": 1, "patientId": 1, "medicationId": 1 }                                                                                    | 201 Created: `{ "id": 1, "appointmentDate": "2023-05-22T10:30:00", "doctor": { "id": 1, "firstName": "John", "lastName": "Doe", ... }, "patient": { "id": 1, "firstName": "Jane", ... }, "medication": { "id": 1, "medicationName": "Medicine", ... } } |
| DELETE      | /appointments/{id}       | 204/404         | Delete an appointment    | -                                                                                                                                                                                | 204 No Content                                                                                                                                                               |
| GET         | /appointments            | 200             | Get all appointments     | -                                                                                                                                                                                | 200 OK: `[{ "id": 1, "appointmentDate": "2023-05-22T10:30:00", "doctor": { "id": 1, "firstName": "John", "lastName": "Doe", ... }, "patient": { "id": 1, "firstName": "Jane", ... }, "medication": { "id": 1, "medicationName": "Medicine", ... } }] |
