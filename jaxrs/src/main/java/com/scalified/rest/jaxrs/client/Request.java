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
	 * Returns request URL
	 *
	 * @return request URL
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * Returns request path parameters
	 *
	 * @return request path parameters
	 */
	public Collection<String> getPathParams() {
		return pathParams;
	}

	/**
	 * Returns request query parameters
	 *
	 * @return request query parameters
	 */
	public Map<String, Collection<?>> getQueryParams() {
		return queryParams;
	}

	/**
	 * Returns request headers
	 *
	 * @return request headers
	 */
	public Map<String, Object> getHeaders() {
		return headers;
	}

	/**
	 * Returns request accepted media types
	 *
	 * @return request accepted media types
	 */
	public Set<MediaType> getMediaTypes() {
		return mediaTypes;
	}

	/**
	 * Returns request entity
	 *
	 * @return request entity
	 */
	public Entity<?> getEntity() {
		return entity;
	}

	/**
	 * Returns response consumer for successful <b>HTTP</b> response
	 *
	 * @return response consumer for successful <b>HTTP</b> response
	 */
	public Consumer<Response> getSuccessConsumer() {
		return successConsumer;
	}

	/**
	 * Returns response consumer for <b>HTTP</b> response having {@link Response.Status#NOT_FOUND} status
	 *
	 * @return response consumer for <b>HTTP</b> response having {@link Response.Status#NOT_FOUND} status
	 */
	public Consumer<Response> getNotFoundConsumer() {
		return notFoundConsumer;
	}

	/**
	 * Returns response consumer for <b>HTTP</b> response having unsuccessful response
	 *
	 * @return response consumer for <b>HTTP</b> response having unsuccessful response
	 */
	public Consumer<Response> getUnsuccessfulResponseConsumer() {
		return unsuccessfulResponseConsumer;
	}

	/**
	 * Returns response consumer for exceptional case
	 *
	 * @return response consumer for exceptional case
	 */
	public Consumer<Throwable> getFailureConsumer() {
		return failureConsumer;
	}

	/**
	 * Indicates whether some other object is "equal to" this one.
	 * <p>
	 * The {@code equals} method implements an equivalence relation
	 * on non-null object references:
	 * <ul>
	 * <li>It is <i>reflexive</i>: for any non-null reference value
	 *     {@code x}, {@code x.equals(x)} should return
	 *     {@code true}.
	 * <li>It is <i>symmetric</i>: for any non-null reference values
	 *     {@code x} and {@code y}, {@code x.equals(y)}
	 *     should return {@code true} if and only if
	 *     {@code y.equals(x)} returns {@code true}.
	 * <li>It is <i>transitive</i>: for any non-null reference values
	 *     {@code x}, {@code y}, and {@code z}, if
	 *     {@code x.equals(y)} returns {@code true} and
	 *     {@code y.equals(z)} returns {@code true}, then
	 *     {@code x.equals(z)} should return {@code true}.
	 * <li>It is <i>consistent</i>: for any non-null reference values
	 *     {@code x} and {@code y}, multiple invocations of
	 *     {@code x.equals(y)} consistently return {@code true}
	 *     or consistently return {@code false}, provided no
	 *     information used in {@code equals} comparisons on the
	 *     objects is modified.
	 * <li>For any non-null reference value {@code x},
	 *     {@code x.equals(null)} should return {@code false}.
	 * </ul>
	 * <p>
	 * The {@code equals} method for class {@code Object} implements
	 * the most discriminating possible equivalence relation on objects;
	 * that is, for any non-null reference values {@code x} and
	 * {@code y}, this method returns {@code true} if and only
	 * if {@code x} and {@code y} refer to the same object
	 * ({@code x == y} has the value {@code true}).
	 * <p>
	 * Note that it is generally necessary to override the {@code hashCode}
	 * method whenever this method is overridden, so as to maintain the
	 * general contract for the {@code hashCode} method, which states
	 * that equal objects must have equal hash codes.
	 *
	 * @param obj the reference object with which to compare.
	 * @return {@code true} if this object is the same as the obj
	 * argument; {@code false} otherwise.
	 * @see #hashCode()
	 * @see java.util.HashMap
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null || getClass() != obj.getClass()) return false;
		Request request = (Request) obj;
		return Objects.equals(url, request.url) &&
				Objects.equals(pathParams, request.pathParams) &&
				Objects.equals(queryParams, request.queryParams) &&
				Objects.equals(headers, request.headers) &&
				Objects.equals(mediaTypes, request.mediaTypes) &&
				Objects.equals(entity, request.entity) &&
				Objects.equals(successConsumer, request.successConsumer) &&
				Objects.equals(notFoundConsumer, request.notFoundConsumer) &&
				Objects.equals(unsuccessfulResponseConsumer, request.unsuccessfulResponseConsumer) &&
				Objects.equals(failureConsumer, request.failureConsumer);
	}

	/**
	 * Returns a hash code value for the object. This method is
	 * supported for the benefit of hash tables such as those provided by
	 * {@link java.util.HashMap}.
	 * <p>
	 * The general contract of {@code hashCode} is:
	 * <ul>
	 * <li>Whenever it is invoked on the same object more than once during
	 *     an execution of a Java application, the {@code hashCode} method
	 *     must consistently return the same integer, provided no information
	 *     used in {@code equals} comparisons on the object is modified.
	 *     This integer need not remain consistent from one execution of an
	 *     application to another execution of the same application.
	 * <li>If two objects are equal according to the {@code equals(Object)}
	 *     method, then calling the {@code hashCode} method on each of
	 *     the two objects must produce the same integer result.
	 * <li>It is <em>not</em> required that if two objects are unequal
	 *     according to the {@link java.lang.Object#equals(java.lang.Object)}
	 *     method, then calling the {@code hashCode} method on each of the
	 *     two objects must produce distinct integer results.  However, the
	 *     programmer should be aware that producing distinct integer results
	 *     for unequal objects may improve the performance of hash tables.
	 * </ul>
	 * <p>
	 * As much as is reasonably practical, the hashCode method defined by
	 * class {@code Object} does return distinct integers for distinct
	 * objects. (This is typically implemented by converting the internal
	 * address of the object into an integer, but this implementation
	 * technique is not required by the
	 * Java&trade; programming language.)
	 *
	 * @return a hash code value for this object.
	 * @see java.lang.Object#equals(java.lang.Object)
	 * @see java.lang.System#identityHashCode
	 */
	@Override
	public int hashCode() {
		return Objects.hash(
				url,
				pathParams,
				queryParams,
				headers,
				mediaTypes,
				entity,
				successConsumer,
				notFoundConsumer,
				unsuccessfulResponseConsumer,
				failureConsumer
		);
	}

	/**
	 * Returns a string representation of the object. In general, the
	 * {@code toString} method returns a string that
	 * "textually represents" this object. The result should
	 * be a concise but informative representation that is easy for a
	 * person to read.
	 * It is recommended that all subclasses override this method.
	 * <p>
	 * The {@code toString} method for class {@code Object}
	 * returns a string consisting of the name of the class of which the
	 * object is an instance, the at-sign character `{@code @}', and
	 * the unsigned hexadecimal representation of the hash code of the
	 * object. In other words, this method returns a string equal to the
	 * value of:
	 * <blockquote>
	 * <pre>
	 * getClass().getName() + '@' + Integer.toHexString(hashCode())
	 * </pre></blockquote>
	 *
	 * @return a string representation of the object.
	 */
	@Override
	public String toString() {
		return "Request{" +
				"url='" + url + '\'' +
				", pathParams=" + pathParams +
				", queryParams=" + queryParams +
				", headers=" + headers +
				", mediaTypes=" + mediaTypes +
				", entity=" + entity +
				", successConsumer=" + successConsumer +
				", notFoundConsumer=" + notFoundConsumer +
				", unsuccessfulResponseConsumer=" + unsuccessfulResponseConsumer +
				", failureConsumer=" + failureConsumer +
				'}';
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
		private final Collection<String> pathParams = new LinkedList<>();

		/**
		 * Request query parameters
		 */
		private final Map<String, Collection<?>> queryParams = new HashMap<>();

		/**
		 * Request headers
		 */
		private final Map<String, Object> headers = new HashMap<>();

		/**
		 * Request accepted media types
		 */
		private final Set<MediaType> mediaTypes = new HashSet<>();

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
