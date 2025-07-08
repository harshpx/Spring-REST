## API Reference
#### For fetching list of all employees:
```
GET: /api/employees
```
#### For fetching an employee by its Id:
```
GET: /api/employees/{employeeId}
```
#### For creating a new employee:
```
POST: /api/employees
PAYLOAD:
{
  "firstName": "...", (Required)
  "lastName": "...", (Required)
  "email": "..." (optional)
}
```
#### For updating an employee
```
PUT: /api/employee
PAYLOAD:
{
  "id": 10, (required)
  "firstName": "...", (Required)
  "lastName": "...", (Required)
  "email": "..." (optional)
}
```
#### For deleting an employee using Id
```
DELETE: /api/employee/{employeeId}
```
