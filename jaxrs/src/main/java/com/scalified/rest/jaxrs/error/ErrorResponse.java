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

package com.scalified.rest.jaxrs.error;

import com.scalified.rest.jaxrs.extension.ExtendedStatus;

import javax.ws.rs.core.Response;
import java.io.Serializable;
import java.util.Objects;

/**
 * A convenient DTO for responding with HTTP error status codes
 *
 * @author shell
 * @version 1.0.0
 * @since 1.0.0
 */
public class ErrorResponse implements Serializable {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Response status code
	 */
	private Integer code;

	/**
	 * Response status reason phrase
	 */
	private String reasonPhrase;

	/**
	 * Response message
	 */
	private String message;

	/**
	 * Response payload
	 */
	private Serializable payload;

	/**
	 * Default constructor
	 */
	public ErrorResponse() {
	}

	/**
	 * Creates {@link ErrorResponse} instance
	 *
	 * @param response response
	 */
	public ErrorResponse(Response response) {
		this.code = response.getStatusInfo().getStatusCode();
		this.reasonPhrase = response.getStatusInfo().getReasonPhrase();
	}

	/**
	 * Creates {@link ErrorResponse} instance
	 *
	 * @param response response
	 * @param message  response message
	 */
	public ErrorResponse(Response response, String message) {
		this.code = response.getStatusInfo().getStatusCode();
		this.reasonPhrase = response.getStatusInfo().getReasonPhrase();
		this.message = message;
	}

	/**
	 * Creates {@link ErrorResponse} instance
	 *
	 * @param response response
	 * @param message  response message
	 * @param payload  response payload
	 */
	public ErrorResponse(Response response, String message, Serializable payload) {
		this.code = response.getStatusInfo().getStatusCode();
		this.reasonPhrase = response.getStatusInfo().getReasonPhrase();
		this.message = message;
		this.payload = payload;
	}

	/**
	 * Creates {@link ErrorResponse} instance
	 *
	 * @param status response status
	 */
	public ErrorResponse(Response.Status status) {
		this.code = status.getStatusCode();
		this.reasonPhrase = status.getReasonPhrase();
	}

	/**
	 * Creates {@link ErrorResponse} instance
	 *
	 * @param status  response status
	 * @param message response message
	 */
	public ErrorResponse(Response.Status status, String message) {
		this.code = status.getStatusCode();
		this.reasonPhrase = status.getReasonPhrase();
		this.message = message;
	}

	/**
	 * Creates {@link ErrorResponse} instance
	 *
	 * @param status  response status
	 * @param message response message
	 * @param payload response payload
	 */
	public ErrorResponse(Response.Status status, String message, Serializable payload) {
		this.code = status.getStatusCode();
		this.reasonPhrase = status.getReasonPhrase();
		this.message = message;
		this.payload = payload;
	}

	/**
	 * Creates {@link ErrorResponse} instance
	 *
	 * @param status response status
	 */
	public ErrorResponse(ExtendedStatus status) {
		this.code = status.getStatusCode();
		this.reasonPhrase = status.getReasonPhrase();
	}

	/**
	 * Creates {@link ErrorResponse} instance
	 *
	 * @param status  response status
	 * @param message response message
	 */
	public ErrorResponse(ExtendedStatus status, String message) {
		this.code = status.getStatusCode();
		this.reasonPhrase = status.getReasonPhrase();
		this.message = message;
	}

	/**
	 * Creates {@link ErrorResponse} instance
	 *
	 * @param status  response status
	 * @param message response message
	 * @param payload response payload
	 */
	public ErrorResponse(ExtendedStatus status, String message, Serializable payload) {
		this.code = status.getStatusCode();
		this.reasonPhrase = status.getReasonPhrase();
		this.message = message;
		this.payload = payload;
	}

	/**
	 * Returns response status code
	 *
	 * @return response status code
	 */
	public Integer getCode() {
		return code;
	}

	/**
	 * Sets response status code
	 *
	 * @param code response status code
	 */
	public void setCode(Integer code) {
		this.code = code;
	}

	/**
	 * Returns response status reason phrase
	 *
	 * @return response status reason phrase
	 */
	public String getReasonPhrase() {
		return reasonPhrase;
	}

	/**
	 * Sets response status reason phrase
	 *
	 * @param reasonPhrase response status reason phrase
	 */
	public void setReasonPhrase(String reasonPhrase) {
		this.reasonPhrase = reasonPhrase;
	}

	/**
	 * Returns response message
	 *
	 * @return response message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Sets response message
	 *
	 * @param message response message
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * Returns response payload
	 *
	 * @return response payload
	 */
	public Serializable getPayload() {
		return payload;
	}

	/**
	 * Sets response payload
	 *
	 * @param payload response payload
	 */
	public void setPayload(Serializable payload) {
		this.payload = payload;
	}

	/**
	 * Indicates whether some other object is "equal to" this one.
	 * <p>
	 * The {@code equals} method implements an equivalence relation
	 * on non-null object references:
	 * <ul>
	 * <li>It is <i>reflexive</i>: for any non-null reference value
	 * {@code x}, {@code x.equals(x)} should return
	 * {@code true}.
	 * <li>It is <i>symmetric</i>: for any non-null reference values
	 * {@code x} and {@code y}, {@code x.equals(y)}
	 * should return {@code true} if and only if
	 * {@code y.equals(x)} returns {@code true}.
	 * <li>It is <i>transitive</i>: for any non-null reference values
	 * {@code x}, {@code y}, and {@code z}, if
	 * {@code x.equals(y)} returns {@code true} and
	 * {@code y.equals(z)} returns {@code true}, then
	 * {@code x.equals(z)} should return {@code true}.
	 * <li>It is <i>consistent</i>: for any non-null reference values
	 * {@code x} and {@code y}, multiple invocations of
	 * {@code x.equals(y)} consistently return {@code true}
	 * or consistently return {@code false}, provided no
	 * information used in {@code equals} comparisons on the
	 * objects is modified.
	 * <li>For any non-null reference value {@code x},
	 * {@code x.equals(null)} should return {@code false}.
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
	 * @param o the reference object with which to compare.
	 * @return {@code true} if this object is the same as the obj
	 * argument; {@code false} otherwise.
	 * @see #hashCode()
	 * @see java.util.HashMap
	 */
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		ErrorResponse that = (ErrorResponse) o;
		return Objects.equals(code, that.code) &&
				Objects.equals(reasonPhrase, that.reasonPhrase) &&
				Objects.equals(message, that.message) &&
				Objects.equals(payload, that.payload);
	}

	/**
	 * Returns a hash code value for the object. This method is
	 * supported for the benefit of hash tables such as those provided by
	 * {@link java.util.HashMap}.
	 * <p>
	 * The general contract of {@code hashCode} is:
	 * <ul>
	 * <li>Whenever it is invoked on the same object more than once during
	 * an execution of a Java application, the {@code hashCode} method
	 * must consistently return the same integer, provided no information
	 * used in {@code equals} comparisons on the object is modified.
	 * This integer need not remain consistent from one execution of an
	 * application to another execution of the same application.
	 * <li>If two objects are equal according to the {@code equals(Object)}
	 * method, then calling the {@code hashCode} method on each of
	 * the two objects must produce the same integer result.
	 * <li>It is <em>not</em> required that if two objects are unequal
	 * according to the {@link java.lang.Object#equals(java.lang.Object)}
	 * method, then calling the {@code hashCode} method on each of the
	 * two objects must produce distinct integer results.  However, the
	 * programmer should be aware that producing distinct integer results
	 * for unequal objects may improve the performance of hash tables.
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
		return Objects.hash(code, reasonPhrase, message, payload);
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
		return "ErrorResponse{" +
				"code=" + code +
				", reasonPhrase='" + reasonPhrase + '\'' +
				", message='" + message + '\'' +
				", payload=" + payload +
				'}';
	}

}
