<a href="README.md">Back to README</a>

## GET /users

<details>
  <summary>Click to expand!</summary>

Return a list of users, following the format below:
```json
[
  {
    "id": "<UUID>",
    "name": "John Doe"
  }
]
```
</details>

## GET /vehicles

<details>
  <summary>Click to expand!</summary>

Return a list of vehicles, following the format below:
```json
[
  {
    "id": "<UUID>",
    "make": "Honda",
    "model": "Accord",
    "year": 2019,
    "milesAmount": 45000
  }
]
```
</details>

## GET /offers

<details>
  <summary>Click to expand!</summary>

Return a list of offers, following the format below:
```json
[
  {
    "id": "<UUID>",
    "monthlyPaymentAmount": 297.73,
    "annualPercentageRate": 7.1,
    "paymentAmount": 60
  }
]
```
</details>

## GET /loans

<details>
  <summary>Click to expand!</summary>

Return a list of loans, following the format below:
```json
[
  {
    "id": "<UUID>",
    "vehicle": {
      "id": "<UUID>",
      "make": "Honda",
      "model": "Accord",
      "year": 2019,
      "milesAmount": 45000
    },
    "user": {
      "id": "<UUID>",
      "name": "John Doe"
    },
    "offer": {
      "id": "<UUID>",
      "monthlyPaymentAmount": 297.73,
      "annualPercentageRate": 7.1,
      "paymentAmount": 60
    },
    "lenderName": "The Big Bank",
    "paidAmount": 54,
    "balance": 15000.00
  }
]
```
</details>

## GET /loans/users/{userId}

<details>
  <summary>Click to expand!</summary>

Return a list of loans given an `userId`. This should be the first API called for the frontend, once user is already logged into the app, and this API follows the format below:
```json
[
  {
    "id": "<UUID>",
    "vehicle": {
      "id": "<UUID>",
      "make": "Honda",
      "model": "Accord",
      "year": 2019,
      "milesAmount": 45000
    },
    "user": {
      "id": "<UUID>",
      "name": "John Doe"
    },
    "offer": {
      "id": "<UUID>",
      "monthlyPaymentAmount": 297.73,
      "annualPercentageRate": 7.1,
      "paymentAmount": 60
    },
    "lenderName": "The Big Bank",
    "paidAmount": 54,
    "balance": 15000.00
  }
]
```
</details>

## GET /offers/generate/{loanId}

<details>
  <summary>Click to expand!</summary>

Return a list of temporary offers given an `loanId`. This API is called when the user selects the desired loan to see all possible offers for it. It follows the format below:
```json
[
  {
    "annualPercentageRate": 3.55,
    "monthlyPayment": 273.21,
    "timeRemaining": 60
  }
]
```
</details>

## PUT /loans/submitOffer

<details>
  <summary>Click to expand!</summary>

Updates the loan's offer given a `body request`. This API is called when the user chooses the most attractive offer and submits it. It follows the format below:
```json
// REQUEST
{
  "loanId": "<UUID>",
  "monthlyPaymentAmount": 297.73, // returned from GET /offer/generate API
  "annualPercentageRate": 7.1, // returned from GET /offer/generate API
  "paymentAmount": 60 // returned from GET /offer/generate API
}

// RESPONSE
{
  "title": "Congratulations",
  "message": "Lorem ipsum dolor sit amet"
}
```
</details>
