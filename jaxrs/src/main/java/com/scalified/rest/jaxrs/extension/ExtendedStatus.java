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

package com.scalified.rest.jaxrs.extension;

import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.Objects;

import static javax.ws.rs.core.Response.Status.Family.*;

/**
 * A list of additional {@link javax.ws.rs.core.Response.StatusType} not covered by
 * {@code jax-rs}
 *
 * @author shell
 * @version 1.0.0
 * @see <a href="http://www.webdav.org/specs/rfc4918.html#status.code.extensions.to.http11">
 * Chapter 11 "Status Code Extensions to HTTP/1.1" of RFC 4918
 * "HTTP Extensions for Web Distributed Authoring and Versioning (WebDAV)"</a>
 * @since 1.0.0
 */
public enum ExtendedStatus implements Response.StatusType {

	/**
	 * 207 Multi-Status
	 *
	 * @see <a href="http://www.webdav.org/specs/rfc4918.html#STATUS_207">
	 * Chapter 11.1 "207 Multi-Status" of RFC 4918
	 * "HTTP Extensions for Web Distributed Authoring and Versioning (WebDAV)"</a>
	 */
	MULTI_STATUS(207, "Multi-Status"),

	/**
	 * 422 Unprocessable Entity
	 *
	 * @see <a href="http://www.webdav.org/specs/rfc4918.html#STATUS_422">
	 * Chapter 11.2 "422 Unprocessable Entity" of RFC 4918
	 * "HTTP Extensions for Web Distributed Authoring and Versioning (WebDAV)"</a>
	 */
	UNPROCESSABLE_ENTITY(422, "Unprocessable Entity"),

	/**
	 * 423 Locked
	 *
	 * @see <a href="http://www.webdav.org/specs/rfc4918.html#STATUS_423">
	 * Chapter 11.3 "423 Locked" of RFC 4918
	 * "HTTP Extensions for Web Distributed Authoring and Versioning (WebDAV)"</a>
	 */
	LOCKED(423, "Locked"),

	/**
	 * 424 Failed Dependency
	 *
	 * @see <a href="http://www.webdav.org/specs/rfc4918.html#STATUS_424">
	 * Chapter 11.4 "424 Failed Dependency" of RFC 4918
	 * "HTTP Extensions for Web Distributed Authoring and Versioning (WebDAV)"</a>
	 */
	FAILED_DEPENDENCY(424, "Failed Dependency"),

	/**
	 * 507 Insufficient Storage
	 *
	 * @see <a href="http://www.webdav.org/specs/rfc4918.html#STATUS_507">
	 * Chapter 11.5 "507 Insufficient Storage" of RFC 4918
	 * "HTTP Extensions for Web Distributed Authoring and Versioning (WebDAV)"</a>
	 */
	INSUFFICIENT_STORAGE(507, "Insufficient Storage");

	/**
	 * Response status code
	 */
	private final int statusCode;

	/**
	 * Response reason phrase
	 */
	private final String reasonPhrase;

	/**
	 * Creates {@link ExtendedStatus} instance
	 *
	 * @param statusCode   response status code
	 * @param reasonPhrase response reason phrase
	 */
	ExtendedStatus(final int statusCode, final String reasonPhrase) {
		this.statusCode = statusCode;
		this.reasonPhrase = reasonPhrase;
	}

	/**
	 * Searches for an appropriate {@link javax.ws.rs.core.Response.StatusType} object
	 * by the specified statusCode.
	 * <p>
	 * Returns a newly created {@link javax.ws.rs.core.Response.StatusType}
	 * with unknown reason phrase and the specified status code, if there is no
	 * {@link javax.ws.rs.core.Response.StatusType} with the status code specified
	 *
	 * @param statusCode HTTP status code to search {@link javax.ws.rs.core.Response.StatusType} by
	 * @return appropriate {@link javax.ws.rs.core.Response.StatusType} if there is such
	 * {@link javax.ws.rs.core.Response.StatusType} with status code specified, otherwise returns
	 * custom {@link javax.ws.rs.core.Response.StatusType}
	 */
	public static Response.StatusType from(final int statusCode) {
		Response.StatusType status = Response.Status.fromStatusCode(statusCode);
		return Objects.nonNull(status) ? status : Arrays.<Response.StatusType>stream(values())
				.filter(s -> s.getStatusCode() == statusCode)
				.findFirst()
				.orElse(new Response.StatusType() {
					@Override
					public int getStatusCode() {
						return statusCode;
					}

					@Override
					public Response.Status.Family getFamily() {
						return OTHER;
					}

					@Override
					public String getReasonPhrase() {
						return "Unknown HTTP Status Code";
					}
				});
	}

	/**
	 * Get the associated status code.
	 *
	 * @return the status code.
	 */
	@Override
	public final int getStatusCode() {
		return this.statusCode;
	}

	/**
	 * Get the class of status code.
	 *
	 * @return the class of status code.
	 */
	@Override
	public final Response.Status.Family getFamily() {
		switch (this.statusCode / 100) {
			case 1:
				return INFORMATIONAL;
			case 2:
				return SUCCESSFUL;
			case 3:
				return REDIRECTION;
			case 4:
				return CLIENT_ERROR;
			case 5:
				return SERVER_ERROR;
			default:
				return OTHER;
		}
	}

	/**
	 * Get the reason phrase.
	 *
	 * @return the reason phrase.
	 */
	@Override
	public final String getReasonPhrase() {
		return this.reasonPhrase;
	}

}
