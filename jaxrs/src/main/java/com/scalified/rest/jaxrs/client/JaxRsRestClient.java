/*
 * MIT License
 *
 * Copyright (c) 2018 Scalified
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.scalified.rest.jaxrs.client;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.SocketTimeoutException;
import java.util.*;
import java.util.function.Function;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

/**
 * A <b>JAX-RS</b> {@link RestClient} implementation
 *
 * @author shell
 * @version 1.0.0
 * @since 1.0.0
 */
public class JaxRsRestClient implements RestClient {

	/**
	 * An underlying <b>JAX-RS</b> client
	 */
	private final Client client;

	/**
	 * Creates {@link JaxRsRestClient} instance
	 *
	 * @param client an underlying <b>JAX-RS</b> client
	 */
	public JaxRsRestClient(Client client) {
		this.client = client;
	}

	/**
	 * Performs <b>HTTP GET</b> request and returns response
	 *
	 * @param request request
	 * @return response
	 */
	@Override
	public Response get(Request request) {
		Invocation invocation = createInvocationBuilder(request).buildGet();
		return invoke(invocation, request)
				.orElseThrow(NoSuchElementException::new);
	}

	/**
	 * Performs <b>HTTP GET</b> request and returns optional response entity
	 *
	 * @param request             request
	 * @param responseEntityClass response entity class
	 * @param <T>                 type of response entity class
	 * @return optional response entity
	 */
	@Override
	public <T> Optional<T> get(Request request, Class<T> responseEntityClass) {
		Invocation invocation = createInvocationBuilder(request).buildGet();
		return invoke(invocation, request)
				.flatMap(response -> readResponseEntity(request, response, responseEntityClass));
	}

	/**
	 * Performs <b>HTTP GET</b> request and returns optional response entity
	 *
	 * @param request            request
	 * @param responseEntityType generic response entity type
	 * @param <T>                type of response entity
	 * @return optional response entity
	 */
	@Override
	public <T> Optional<T> get(Request request, GenericType<T> responseEntityType) {
		Invocation invocation = createInvocationBuilder(request).buildGet();
		return invoke(invocation, request)
				.flatMap(response -> readResponseEntity(request, response, responseEntityType));
	}

	/**
	 * Performs <b>HTTP POST</b> request and returns response
	 *
	 * @param request request
	 * @return response
	 */
	@Override
	public Response post(Request request) {
		Invocation invocation = createInvocationBuilder(request).buildPost(request.entity);
		return invoke(invocation, request)
				.orElseThrow(NoSuchElementException::new);
	}

	/**
	 * Performs <b>HTTP POST</b> request and returns optional response entity
	 *
	 * @param request             request
	 * @param responseEntityClass response entity class
	 * @param <T>                 type of response entity class
	 * @return optional response entity
	 */
	@Override
	public <T> Optional<T> post(Request request, Class<T> responseEntityClass) {
		Invocation invocation = createInvocationBuilder(request).buildPost(request.entity);
		return invoke(invocation, request)
				.flatMap(response -> readResponseEntity(request, response, responseEntityClass));
	}

	/**
	 * Performs <b>HTTP POST</b> request and returns optional response entity
	 *
	 * @param request            request
	 * @param responseEntityType generic response entity type
	 * @param <T>                type of response entity
	 * @return optional response entity
	 */
	@Override
	public <T> Optional<T> post(Request request, GenericType<T> responseEntityType) {
		Invocation invocation = createInvocationBuilder(request).buildPost(request.entity);
		return invoke(invocation, request)
				.flatMap(response -> readResponseEntity(request, response, responseEntityType));
	}

	/**
	 * Performs <b>HTTP PUT</b> request and returns response
	 *
	 * @param request request
	 * @return response
	 */
	@Override
	public Response put(Request request) {
		Invocation invocation = createInvocationBuilder(request).buildPut(request.entity);
		return invoke(invocation, request)
				.orElseThrow(NoSuchElementException::new);
	}

	/**
	 * Performs <b>HTTP PUT</b> request and returns optional response entity
	 *
	 * @param request             request
	 * @param responseEntityClass response entity class
	 * @param <T>                 type of response entity
	 * @return optional response entity
	 */
	@Override
	public <T> Optional<T> put(Request request, Class<T> responseEntityClass) {
		Invocation invocation = createInvocationBuilder(request).buildPut(request.entity);
		return invoke(invocation, request)
				.flatMap(response -> readResponseEntity(request, response, responseEntityClass));
	}

	/**
	 * Performs <b>HTTP PUT</b> request and returns optional response entity
	 *
	 * @param request            request
	 * @param responseEntityType response entity class
	 * @param <T>                type of response entity
	 * @return optional response entity
	 */
	@Override
	public <T> Optional<T> put(Request request, GenericType<T> responseEntityType) {
		Invocation invocation = createInvocationBuilder(request).buildPut(request.entity);
		return invoke(invocation, request)
				.flatMap(response -> readResponseEntity(request, response, responseEntityType));
	}

	/**
	 * Performs <b>HTTP DELETE</b> request and returns response
	 *
	 * @param request request
	 * @return response
	 */
	@Override
	public Response delete(Request request) {
		Invocation invocation = createInvocationBuilder(request).buildDelete();
		return invoke(invocation, request)
				.orElseThrow(NoSuchElementException::new);
	}

	/**
	 * Performs <b>HTTP DELETE</b> request
	 *
	 * @param request             request
	 * @param responseEntityClass response entity class
	 * @param <T>                 type of response entity
	 * @return optional response entity
	 */
	@Override
	public <T> Optional<T> delete(Request request, Class<T> responseEntityClass) {
		Invocation invocation = createInvocationBuilder(request).buildDelete();
		return invoke(invocation, request)
				.flatMap(response -> readResponseEntity(request, response, responseEntityClass));
	}

	/**
	 * Performs <b>HTTP DELETE</b> request
	 *
	 * @param request            request
	 * @param responseEntityType response entity class
	 * @param <T>                type of response entity
	 * @return optional response entity
	 */
	@Override
	public <T> Optional<T> delete(Request request, GenericType<T> responseEntityType) {
		Invocation invocation = createInvocationBuilder(request).buildDelete();
		return invoke(invocation, request)
				.flatMap(response -> readResponseEntity(request, response, responseEntityType));
	}

	/**
	 * Creates {@link Invocation.Builder} from the given <b>HTTP</b> {@link Request}
	 *
	 * @param request <b>HTTP</b> {@link Request}
	 * @return {@link Invocation.Builder} object
	 */
	private Invocation.Builder createInvocationBuilder(Request request) {
		WebTarget target = client.target(request.url);
		for (String path : request.pathParams) {
			target = target.path(path);
		}
		for (Map.Entry<String, Collection<?>> queryParam : request.queryParams.entrySet()) {
			target = target.queryParam(queryParam.getKey(), queryParam.getValue().toArray());
		}
		Invocation.Builder builder = target.request();
		builder = builder.accept(request.mediaTypes.toArray(new MediaType[0]));
		for (Map.Entry<String, Object> header : request.headers.entrySet()) {
			builder = builder.header(header.getKey(), header.getValue());
		}
		return builder;
	}

	/**
	 * Invokes the given {@link Invocation} supplied with the given {@link Request}
	 * <p>
	 * Returns {@link Optional} <b>HTTP</b> {@link Response}
	 *
	 * @param invocation {@link Invocation} to invoke
	 * @param request    <b>HTTP</b> {@link Request} to supply invocation with
	 * @return {@link Optional} <b>HTTP</b> {@link Response}
	 */
	private Optional<Response> invoke(Invocation invocation, Request request) {
		RuntimeException thrown = null;
		try {
			Response response = invocation.invoke();
			if (RestClient.isSuccessful(response)) {
				try {
					request.successConsumer.accept(response);
				} catch (RuntimeException e) {
					thrown = e;
					throw e;
				}
			} else {
				final Response.Status status = Response.Status.fromStatusCode(response.getStatus());
				try {
					if (nonNull(status)) {
						switch (status) {
							case NOT_FOUND:
								request.notFoundConsumer.accept(response);
								break;
						}
					}
					request.unsuccessfulResponseConsumer.accept(response);
				} catch (RuntimeException e) {
					thrown = e;
					throw e;
				}
			}
			return Optional.of(response);
		} catch (Exception e) {
			if (nonNull(thrown) && Objects.equals(thrown, e)) {
				throw thrown;
			}
			request.failureConsumer.accept(e);
			if (e.getCause() instanceof SocketTimeoutException) {
				return Optional.of(Response.status(Response.Status.REQUEST_TIMEOUT).build());
			}
		}
		return Optional.empty();
	}

	/**
	 * Reads and returns <b>HTTP</b> {@link Response} entity
	 *
	 * @param request     <b>HTTP</b> {@link Request}
	 * @param response    <b>HTTP</b> {@link Response}
	 * @param entityClass <b>HTTP</b> {@link Response} entity class
	 * @param <T>         type of <b>HTTP</b> {@link Response} entity
	 * @return <b>HTTP</b> {@link Response} entity
	 */
	private <T> Optional<T> readResponseEntity(Request request, Response response, Class<T> entityClass) {
		return readResponseEntity(request, response, res -> res.readEntity(entityClass));
	}

	/**
	 * Reads and returns <b>HTTP</b> {@link Response} entity
	 *
	 * @param request     <b>HTTP</b> {@link Request}
	 * @param response    <b>HTTP</b> {@link Response}
	 * @param genericType <b>HTTP</b> {@link Response} entity generic type
	 * @param <T>         type of <b>HTTP</b> {@link Response} entity
	 * @return <b>HTTP</b> {@link Response} entity
	 */
	private <T> Optional<T> readResponseEntity(Request request, Response response, GenericType<T> genericType) {
		return readResponseEntity(request, response, res -> res.readEntity(genericType));
	}

	/**
	 * Reads and returns <b>HTTP</b> {@link Response} entity
	 *
	 * @param request  <b>HTTP</b> {@link Request}
	 * @param response <b>HTTP</b> {@link Response}
	 * @param function {@link Function} to read <b>HTTP</b> {@link Response} entity
	 * @param <T>      type of <b>HTTP</b> {@link Response} entity
	 * @return <b>HTTP</b> {@link Response} entity
	 */
	private <T> Optional<T> readResponseEntity(Request request, Response response, Function<Response, T> function) {
		if (isNull(response)) {
			return Optional.empty();
		}
		try {
			return Optional.ofNullable(function.apply(response));
		} catch (Exception e) {
			request.failureConsumer.accept(e);
		} finally {
			try {
				response.close();
			} catch (Exception ignored) {
			}
		}
		return Optional.empty();
	}

}
