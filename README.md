<h1 align="center">
    <br>Replay<br/>
    Kotlin | Micronaut | Coroutines
</h1>

<p align="center">
  <a href="https://app.getpostman.com/run-collection/60e00c7d4143fc4bcb1b" target="_blank"><img src="https://run.pstmn.io/button.svg" alt="Run in Postman"></a>
</p>
<p align="center">
  <a href="#about">About</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
  <a href="#stack">Stack</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
  <a href="#how-to-run">How to run</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
  <a href="#how-to-run">Which APIs it has?</a>
</p>

## About

Replay is an application responsible to refinance auto loans, helping people to get te best taxes and submit their offer instantly.

This project was built in order to present as a tech test to WithClutch for the Software Engineer hole

## Stack

-  [Kotlin](https://kotlinlang.org/)
-  [Micronaut](https://micronaut.io/)
-  [Coroutines](https://kotlinlang.org/docs/coroutines-overview.html)
-  [R2DBC](https://r2dbc.io/)
-  [JUnit](https://junit.org/junit5/)
-  [Mockk](https://mockk.io/)

## How to Run

- ### **Requirements**

    - Make sure you have **[Docker](https://docs.docker.com/engine/install/)** and **[Docker Compose](https://docs.docker.com/compose/install/)** installed into your machine
    - You must have connection internet to download all libraries (but I'm sure you have because you are reading this on GitHub)

1. Clone this repo:

```sh
  $ git clone https://github.com/amazingustav/replay-micronaut.git
```

2. Prepare application:

```sh
  $ cd path/to/replay-micronaut
  
  # Build docker compose to use database
  $ cd platform/docker && docker-compose up && cd ../../
  
  # Build application
  $ ./gradlew clean build

  # Execute database migrations (to provide some data to all tables)
  $ ./gradlew flywayBaseline flywayMigrate
```

3. Run the application (it will be available on http://localhost:8087)
```sh
  $ ./gradlew run
```

4. (Optional) If you wish to execute only test, run:
```sh
  $ ./gradlew clean test
```
