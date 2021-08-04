# Cookie POC

## Motivation
In a given use case the config for an Apache webserver was adapted to
detect a cookie in a request and -using mod_headers- instructed to
remove the cookie in the response by adding a header to the response:

```
SetEnvIfNoCase Cookie cxrsProcessed=([^;]+) IS_CXRS_COOKIE_SET=yes

<If "env('IS_CXRS_COOKIE_SET') == 'yes'" >
Header set Set-Cookie "cxrsProcessed=true; Path=/; Expires=Thu, 01 Jan 1970 00:00:00 GMT; HttpOnly"
</If>
```

After deploying (in production no less) we saw a number of cookies from an application
missing and suspected the `header set Set-Cookie...` to be the cause of this. This POC
was created to investigate this issue.

## Build

```shell
$ ./mvnw clean verify
```

## Run

```shell
$ mvn spring-boot:run
```

## Docs

UI (currently not enabled)

```
http://serverName:9090/actuator/swaggerui
```

JSON (currently not enabled)

```
http://serverName:9090/actuator/openapi
```

## Docker

### Build and push the docker image

To build and push the image to docker perform the following steps

```shell
# build and verify your application
$ ./mvn clean verify
#
# build and install the application in the local repository
$ ./mvnw install
#
# build the docker image
$ docker build -t cookie-poc:0.0.1-SNAPSHOT
#
# login to Docker
$ docker login -u "<username>" -p "<password>"
#
# push the image
$ docker image push cookie-poc:0.0.1-SNAPSHOT
```
