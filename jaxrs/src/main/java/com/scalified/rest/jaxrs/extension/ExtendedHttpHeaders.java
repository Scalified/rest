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

import javax.ws.rs.core.HttpHeaders;

/**
 * Additional custom list of {@link javax.ws.rs.core.HttpHeaders}
 *
 * @author shell
 * @version 1.0.0
 * @since 1.0.0
 */
public interface ExtendedHttpHeaders extends HttpHeaders {

	/**
	 * Origin header
	 */
	String ORIGIN = "Origin";

	/**
	 * Access Control Allow Origin header
	 */
	String ACCESS_CONTROL_ALLOW_ORIGIN = "Access-Control-Allow-Origin";

	/**
	 * Access Control Allow Credentials header
	 */
	String ACCESS_CONTROL_ALLOW_CREDENTIALS = "Access-Control-Allow-Credentials";

	/**
	 * Access Control Allow Methods header
	 */
	String ACCESS_CONTROL_ALLOW_METHODS = "Access-Control-Allow-Methods";

	/**
	 * Access Control Allow Headers header
	 */
	String ACCESS_CONTROL_ALLOW_HEADERS = "Access-Control-Allow-Headers";

	/**
	 * Access Control Expose Headers
	 */
	String ACCESS_CONTROL_EXPOSE_HEADERS = "Access-Control-Expose-Headers";

	/**
	 * Access Control Request Method
	 */
	String ACCESS_CONTROL_REQUEST_METHOD = "Access-Control-Request-Method";

	/**
	 * Access Control Request Headers header
	 */
	String ACCESS_CONTROL_REQUEST_HEADERS = "Access-Control-Request-Headers";

	/**
	 * Content Disposition Attachment Filename header
	 */
	String CONTENT_DISPOSITION_ATTACHMENT_FILENAME = "attachment; filename=";

	/**
	 * Pragma header
	 */
	String PRAGMA = "Pragma";

}
