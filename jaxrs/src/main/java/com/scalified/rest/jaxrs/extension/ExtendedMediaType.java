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

import javax.ws.rs.core.MediaType;

/**
 * Additional list of {@link MediaType}
 *
 * @author shell
 * @version 1.0.0
 * @since 1.0.0
 */
public class ExtendedMediaType extends MediaType {

	/**
	 * An addition to {@link MediaType}, which sets both {@link MediaType#APPLICATION_JSON}
	 * and {@code UTF-8} charset
	 */
	public static final String APPLICATION_JSON_UTF_8 = "application/json;charset=utf-8";

	/**
	 * An addition to {@link MediaType}, which sets both {@link MediaType#APPLICATION_JSON_TYPE}
	 * and {@code UTF-8} charset
	 */
	public static final MediaType APPLICATION_JSON_UTF_8_TYPE = new MediaType(
			"application", "json", "utf-8"
	);

	/**
	 * {@link MediaType} for PDF document
	 */
	public static final String APPLICATION_PDF = "application/pdf";

	/**
	 * {@link MediaType} for PDF document
	 */
	public static final MediaType APPLICATION_PDF_TYPE = new MediaType("application", "pdf");

	protected ExtendedMediaType() {
	}

}
