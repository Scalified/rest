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

import com.scalified.rest.jaxrs.extension.ExtendedMediaType;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.*;
import java.util.function.Consumer;

/**
 * An <b>HTTP</b> Request containing all necessary attributes
 *
 * @author shell
 * @version 1.0.0
 * @since 1.0.0
 */
public class Request {

	/**
	 * Request URL
	 */
	String url;

	/**
	 * Request path parameters
	 */
	Collection<String> pathParams;

	/**
	 * Request query parameters
	 */
	Map<String, Collection<?>> queryParams;

	/**
	 * Request headers
	 */
	Map<String, Object> headers;

	/**
	 * Request accepted media types
	 */
	Set<MediaType> mediaTypes;

	/**
	 * Request entity
	 */
	Entity<?> entity;

	/**
	 * Response consumer for successful <b>HTTP</b> response
	 */
	Consumer<Response> successConsumer;

	/**
	 * Response consumer for <b>HTTP</b> response having {@link Response.Status#NOT_FOUND} status
	 */
	Consumer<Response> notFoundConsumer;

	/**
	 * Response consumer for <b>HTTP</b> response having unsuccessful response
	 */
	Consumer<Response> unsuccessfulResponseConsumer;

	/**
	 * Response consumer for exceptional case
	 */
	Consumer<Throwable> failureConsumer;

	/**
	 * Returns the <b>HTTP</b> {@link Request} builder
	 *
	 * @param url request URL
	 * @return <b>HTTP</b> {@link Request} builder
	 */
	public static Builder builder(String url) {
		return new Builder(url);
	}

	/**
	 * <b>HTTP</b> {@link Request} builder
	 */
	public static class Builder {

		/**
		 * Request URL
		 */
		private final String url;

		/**
		 * Request path parameters;
		 */
		private Collection<String> pathParams = new LinkedList<>();

		/**
		 * Request query parameters
		 */
		private Map<String, Collection<?>> queryParams = new HashMap<>();

		/**
		 * Request headers
		 */
		private Map<String, Object> headers = new HashMap<>();

		/**
		 * Request accepted media types
		 */
		private Set<MediaType> mediaTypes = new HashSet<>();

		/**
		 * Request entity
		 */
		private Entity<?> entity;

		/**
		 * Response consumer for successful <b>HTTP</b> response
		 */
		private Consumer<Response> successConsumer = response -> {
		};

		/**
		 * Response consumer for <b>HTTP</b> response having {@link Response.Status#NOT_FOUND} status
		 */
		private Consumer<Response> notFoundConsumer = response -> {
		};

		/**
		 * Response consumer for <b>HTTP</b> response having unsuccessful response
		 */
		private Consumer<Response> unsuccessfulResponseConsumer = response -> {
		};

		/**
		 * Response consumer for exceptional case
		 */
		private Consumer<Throwable> failureConsumer = throwable -> {
		};

		/**
		 * Creates {@link Builder} instance
		 *
		 * @param url request URL
		 */
		private Builder(String url) {
			this.url = url;
		}

		/**
		 * Adds <b>HTTP</b> path parameter to {@link Request} URL
		 *
		 * @param path <b>HTTP</b> path parameter
		 * @return <b>HTTP</b> {@link Request} builder instance
		 */
		public Builder path(String path) {
			this.pathParams.add(path);
			return this;
		}

		/**
		 * Adds <b>HTTP</b> query parameters to {@link Request}
		 *
		 * @param params <b>HTTP</b> query parameters
		 * @return <b>HTTP</b> {@link Request} builder instance
		 */
		public Builder queryParams(Map<String, ? extends Collection<?>> params) {
			queryParams.putAll(params);
			return this;
		}

		/**
		 * Adds single <b>HTTP</b> query parameter to {@link Request}
		 *
		 * @param key   <b>HTTP</b> query parameter key
		 * @param value <b>HTTP</b> query parameter value
		 * @return <b>HTTP</b> {@link Request} builder instance
		 */
		public Builder queryParam(String key, Object value) {
			queryParams.put(key, Collections.singleton(value));
			return this;
		}

		/**
		 * Adds <b>HTTP</b> accepted media types to {@link Request}
		 *
		 * @param mediaTypes <b>HTTP</b> accepted media types
		 * @return <b>HTTP</b> {@link Request} builder instance
		 */
		public Builder accepting(String... mediaTypes) {
			Arrays.stream(mediaTypes).forEach(mediaType -> this.mediaTypes.add(ExtendedMediaType.valueOf(mediaType)));
			return this;
		}

		/**
		 * Adds <b>HTTP</b> accepted media types to {@link Request}
		 *
		 * @param mediaTypes <b>HTTP</b> accepted media types
		 * @return <b>HTTP</b> {@link Request} builder instance
		 */
		public Builder accepting(MediaType... mediaTypes) {
			this.mediaTypes.addAll(Arrays.asList(mediaTypes));
			return this;
		}

		/**
		 * Sets <b>HTTP</b> entity to {@link Request}
		 *
		 * @param entity <b>HTTP</b> entity
		 * @return <b>HTTP</b> {@link Request} builder instance
		 */
		public Builder entity(Entity<?> entity) {
			this.entity = entity;
			return this;
		}

		/**
		 * Adds <b>HTTP</b> headers to {@link Request}
		 *
		 * @param headers <b>HTTP</b> headers
		 * @return <b>HTTP</b> {@link Request} builder instance
		 */
		public Builder headers(Map<String, Object> headers) {
			this.headers.putAll(headers);
			return this;
		}

		/**
		 * Adds single <b>HTTP</b> header to {@link Request}
		 *
		 * @param key   <b>HTTP</b> header key
		 * @param value <b>HTTP</b> header value
		 * @return <b>HTTP</b> {@link Request} builder instance
		 */
		public Builder header(String key, Object value) {
			this.headers.put(key, value);
			return this;
		}

		/**
		 * Sets <b>HTTP</b> {@link Response} consumer for successful <b>HTTP</b> {@link Response}
		 *
		 * @param consumer <b>HTTP</b> {@link Response} consumer
		 * @return <b>HTTP</b> {@link Request} builder instance
		 */
		public Builder onSuccess(Consumer<Response> consumer) {
			this.successConsumer = consumer;
			return this;
		}

		/**
		 * Sets <b>HTTP</b> {@link Response} consumer for <b>HTTP</b> {@link Response} having
		 * {@link Response.Status#NOT_FOUND} status
		 *
		 * @param consumer <b>HTTP</b> {@link Response} consumer
		 * @return <b>HTTP</b> {@link Request} builder instance
		 */
		public Builder onNotFound(Consumer<Response> consumer) {
			this.notFoundConsumer = consumer;
			return this;
		}

		/**
		 * Sets <b>HTTP</b> {@link Response} consumer for unsuccessful <b>HTTP</b> {@link Response}
		 *
		 * @param consumer <b>HTTP</b> {@link Response} consumer
		 * @return <b>HTTP</b> {@link Request} builder instance
		 */
		public Builder onUnsuccessfulResponse(Consumer<Response> consumer) {
			this.unsuccessfulResponseConsumer = consumer;
			return this;
		}

		/**
		 * Sets <b>HTTP</b> {@link Response} consumer for exceptional case
		 *
		 * @param consumer <b>HTTP</b> {@link Response} consumer
		 * @return <b>HTTP</b> {@link Request} builder instance
		 */
		public Builder onFailure(Consumer<Throwable> consumer) {
			this.failureConsumer = consumer;
			return this;
		}

		/**
		 * Builds and returns <b>HTTP</b> {@link Request}
		 *
		 * @return <b>HTTP</b> {@link Request}
		 */
		public Request build() {
			Request request = new Request();
			request.url = this.url;
			request.pathParams = this.pathParams;
			request.queryParams = this.queryParams;
			request.headers = this.headers;
			request.mediaTypes = this.mediaTypes;
			request.entity = this.entity;
			request.successConsumer = this.successConsumer;
			request.notFoundConsumer = this.notFoundConsumer;
			request.unsuccessfulResponseConsumer = this.unsuccessfulResponseConsumer;
			request.failureConsumer = this.failureConsumer;
			return request;
		}

	}

}
