# pedestal-rest
A project build on clojure ecosystem. The aim to compose different libraries to build a single page app backend.

## To DO
1. Json request/response
2. Authentication
3. Authorization
4. Logging

## Getting Started

1. Start the application: `lein run-dev` \*
2. Go to [localhost:8080](http://localhost:8080/) to see: `Hello World!`
3. Read your app's source code at src/pedestal_rest/service.clj. Explore the docs of functions
   that define routes and responses.
4. Run your app's tests with `lein test`. Read the tests at test/pedestal_rest/service_test.clj.
5. Learn more! See the [Links section below](#links).

\* `lein run-dev` automatically detects code changes. Alternatively, you can run in production mode
with `lein run`.

## Configuration

To configure logging see config/logback.xml. By default, the app logs to stdout and logs/.
To learn more about configuring Logback, read its [documentation](http://logback.qos.ch/documentation.html).

