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

import org.apache.commons.lang3.Range;
import org.apache.commons.lang3.StringUtils;

import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Rest client interface
 *
 * @author shell
 * @version 1.0.0
 * @since 1.0.0
 */
public interface RestClient {

	/**
	 * Returns <b>true</b> if the specified {@link Response} has successful status code (e.g. 2xx),
	 * otherwise returns <b>false</b>
	 *
	 * @param response response the check the status code of
	 * @return <b>true</b> if the specified {@link Response} has successful status code (e.g. 2xx),
	 * otherwise returns <b>false</b>
	 */
	static boolean isSuccessful(Response response) {
		return Range.between(200, 299).contains(response.getStatus());
	}

	/**
	 * Creates the new {@link Response} object from the given {@link Response}
	 * <p>
	 * The original <b>HTTP</b> response status, entity and headers retained
	 *
	 * @param response a {@link Response} object to create the new one from
	 * @return new {@link Response} object from the given {@link Response}
	 */
	static Response from(Response response) {
		try {
			Response.ResponseBuilder builder = Response.status(response.getStatus())
					.entity(response.readEntity(byte[].class));
			Map<String, List<Object>> headers = response.getHeaders();
			for (Map.Entry<String, List<Object>> header : headers.entrySet()) {
				builder = builder.header(
						header.getKey(), StringUtils.join(header.getValue().iterator(), ';')
				);
			}
			return builder.build();
		} finally {
			response.close();
		}
	}

	/**
	 * Performs <b>HTTP GET</b> request and returns response
	 *
	 * @param request request
	 * @return response
	 */
	Response get(Request request);

	/**
	 * Performs <b>HTTP GET</b> request and returns optional response entity
	 *
	 * @param request             request
	 * @param responseEntityClass response entity class
	 * @param <T>                 type of response entity class
	 * @return optional response entity
	 */
	<T> Optional<T> get(Request request, Class<T> responseEntityClass);

	/**
	 * Performs <b>HTTP GET</b> request and returns optional response entity
	 *
	 * @param request            request
	 * @param responseEntityType generic response entity type
	 * @param <T>                type of response entity
	 * @return optional response entity
	 */
	<T> Optional<T> get(Request request, GenericType<T> responseEntityType);

	/**
	 * Performs <b>HTTP POST</b> request and returns response
	 *
	 * @param request request
	 * @return response
	 */
	Response post(Request request);

	/**
	 * Performs <b>HTTP POST</b> request and returns optional response entity
	 *
	 * @param request             request
	 * @param responseEntityClass response entity class
	 * @param <T>                 type of response entity class
	 * @return optional response entity
	 */
	<T> Optional<T> post(Request request, Class<T> responseEntityClass);

	/**
	 * Performs <b>HTTP POST</b> request and returns optional response entity
	 *
	 * @param request            request
	 * @param responseEntityType generic response entity type
	 * @param <T>                type of response entity
	 * @return optional response entity
	 */
	<T> Optional<T> post(Request request, GenericType<T> responseEntityType);

	/**
	 * Performs <b>HTTP PUT</b> request and returns response
	 *
	 * @param request request
	 * @return response
	 */
	Response put(Request request);

	/**
	 * Performs <b>HTTP PUT</b> request and returns optional response entity
	 *
	 * @param request             request
	 * @param responseEntityClass response entity class
	 * @param <T>                 type of response entity
	 * @return optional response entity
	 */
	<T> Optional<T> put(Request request, Class<T> responseEntityClass);

	/**
	 * Performs <b>HTTP PUT</b> request and returns optional response entity
	 *
	 * @param request            request
	 * @param responseEntityType response entity class
	 * @param <T>                type of response entity
	 * @return optional response entity
	 */
	<T> Optional<T> put(Request request, GenericType<T> responseEntityType);

	/**
	 * Performs <b>HTTP DELETE</b> request and returns response
	 *
	 * @param request request
	 * @return response
	 */
	Response delete(Request request);

	/**
	 * Performs <b>HTTP DELETE</b> request
	 *
	 * @param request             request
	 * @param responseEntityClass response entity class
	 * @param <T>                 type of response entity
	 * @return optional response entity
	 */
	<T> Optional<T> delete(Request request, Class<T> responseEntityClass);

	/**
	 * Performs <b>HTTP DELETE</b> request
	 *
	 * @param request            request
	 * @param responseEntityType response entity class
	 * @param <T>                type of response entity
	 * @return optional response entity
	 */
	<T> Optional<T> delete(Request request, GenericType<T> responseEntityType);

}
