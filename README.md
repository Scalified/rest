# REST Java Library

[![Build Status](https://travis-ci.org/Scalified/rest.svg)](https://travis-ci.org/Scalified/rest)
[![Maven Central](https://img.shields.io/maven-central/v/com.scalified/jaxrs.svg)](http://search.maven.org/#search%7Cga%7C1%7Ccom.scalified%20jaxrs)

## Description

This Library provides extensions for [**Java API for RESTful Web Services**](https://jcp.org/en/jsr/detail?id=339) and its implementations

The Library consists of several modules, which can be used separately

* **jaxrs** - [**Java API for RESTful Web Services**](https://jcp.org/en/jsr/detail?id=339) extensions
* **jaxrs-resteasy3** - [**Resteasy**](http://resteasy.jboss.org/) version 3.x extensions

## Requirements

* [Java SE Development Kit 8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) or higher
* JAX-RS API 2.1 implementation

## Gradle dependency

### jaxrs

```java
dependencies {
	compile "com.scalified:jaxrs:$version"
}
```

### jaxrs-resteasy3

```java
dependencies {
	compile "com.scalified:jaxrs-resteasy3:$version"

	compileOnly "org.jboss.resteasy:resteasy-multipart-provider:3.0.1.Final"
}
```

## Changelog

[Changelog](CHANGELOG.md)

## Usage

### jaxrs

#### Rest Client

```java
import com.scalified.rest.jaxrs.client.JaxRsRestClient;
import com.scalified.rest.jaxrs.client.Request;
import com.scalified.rest.jaxrs.client.RestClient;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Optional;
import java.util.Set;

// Creating Rest Client Instance
Client jaxRsClient;
// ... client initialization skipped
RestClient client = new JaxRsRestClient(jaxRsClient);

// Constructing Request Object
Request request = Request.builder("http://localhost:8080")
				.path("/application")
				.queryParam("role", "admin")
				.accepting(MediaType.APPLICATION_JSON)
				.entity(Entity.json("{\"message\": \"Hello\"}"))
				.header("Authorization", "Basic YWRtaW46YWRtaW4=")
				.onSuccess(response -> System.out.println("Success"))
				.onNotFound(response -> System.out.println("Not Found"))
				.onUnsuccessfulResponse(response -> System.out.println("Unsuccessful Response"))
				.onFailure(throwable -> System.out.println("Failure"))
				.build();

// Executing HTTP GET Requests
Response response = client.get(request);
Optional<String> result = client.get(request, String.class);
Optional<Set<String>> results = client.get(request, new GenericType<Set<String>>(){});

// Executing HTTP POST Requests
Response response = client.post(request);
Optional<String> result = client.post(request, String.class);
Optional<Set<String>> results = client.post(request, new GenericType<Set<String>>(){});

// Executing HTTP PUT Requests
Response response = client.put(request);
Optional<String> result = client.put(request, String.class);
Optional<Set<String>> results = client.put(request, new GenericType<Set<String>>(){});

// Executing HTTP DELETE Requests
Response response = client.delete(request);
Optional<String> result = client.delete(request, String.class);
Optional<Set<String>> results = client.delete(request, new GenericType<Set<String>>(){});

// Checking Response Is Successfull
Response response;
// ... response initialization skipped
boolean successful = RestClient.isSuccessful(response)

// Constructing New Response Object From The Given One
Response response;
// ... response initialization skipped
Response newResponse = RestClient.from(response); // HTTP response status code, entity and headers retained
```

#### Extensions

```java
import com.scalified.rest.jaxrs.extension.ExtendedMediaType;
import com.scalified.rest.jaxrs.extension.ExtendedHttpHeaders;
import com.scalified.rest.jaxrs.extension.ExtendedStatus;

import javax.ws.rs.core.Response;

// Using extended HTTP media types
String applicationPdfMediaType = ExtendedMediaType.APPLICATION_PDF;

// Using extended HTTP headers
String pragmaHeader = ExtendedHttpHeaders.PRAGMA;

// Using extended HTTP statuses
Response.StatusType unprocessableEntityStatusType = ExtendedStatus.UNPROCESSABLE_ENTITY;
```

#### CORS Feature

```java
import com.scalified.rest.jaxrs.cors.CorsFilter;

import javax.ws.rs.core.Feature;
import javax.ws.rs.core.FeatureContext;
import javax.ws.rs.ext.Provider;

@Provider
public class CorsFeature implements Feature {

	@Override
	public boolean configure(FeatureContext context) {
		CorsFilter filter = new CorsFilter("*");
		context.register(filter);
		return true;
	}

}
```

#### URI Utilities

```java
import com.scalified.rest.jaxrs.commons.UriUtils;

String url = UriUtils.encode("http://localhost:8080/a prämie");
assert url.equals("http%3A%2F%2Flocalhost%3A8080%2Fa%20pr%C3%A4mie");
```

#### Error Response Mapping

```java
import com.scalified.rest.jaxrs.error.ErrorResponse;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class DefaultExceptionMapper implements ExceptionMapper<Exception> {

	@Override
	public Response toResponse(Exception e) {
		ErrorResponse message = new ErrorResponse(Response.Status.INTERNAL_SERVER_ERROR, e.getMessage());
		return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
				.entity(message)
				.build();
	}

}
```

### jaxrs-resteasy3

#### Multipart

```java
import com.scalified.rest.jaxrs.extension.ExtendedMediaType;
import com.scalified.rest.jaxrs.resteasy.multipart.MultipartUtils;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.util.Map;

@Path("/")
public class Controller {

    @POST
    @Path("/files")
    @Consumes(ExtendedMediaType.MULTIPART_FORM_DATA)
    public Response uploadAttachment(MultipartFormDataInput input) {
    	
    	// Extracting generic types from MultipartFormDataInput
    	Long someValue = MultipartUtils.extractPart(input, "someValue", Long.class);

        // Extracting String parts from MultipartFormDataInput
        String someId = MultipartUtils.extractPart(input, "someId");

        // Extracting files from MultipartFormDataInput
        Map<String, byte[]> files = MultipartUtils.extractFiles(input);

        return Response.ok()
                .build();
    }

}
```

## License

```
MIT License

Copyright (c) 2018 Scalified

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```

## Scalified Links

* [Scalified](http://www.scalified.com)
* [Scalified Official Facebook Page](https://www.facebook.com/scalified)
* <a href="mailto:info@scalified.com?subject=[REST Java Library]: Proposals And Suggestions">Scalified Support</a>
