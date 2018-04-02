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

package com.scalified.rest.jaxrs.commons;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * An utility class for working with {@link java.net.URI}
 *
 * @author shell
 * @version 1.0.0
 * @since 1.0.0
 */
public final class UriUtils {

	/**
	 * Prevents from {@link UriUtils} creation
	 */
	private UriUtils() {
	}

	/**
	 * Encodes the input {@link String} into the url-encoded form
	 * <p>
	 * Uses {@code %20} instead of {@code +} (plus) sign
	 *
	 * @param input {@link String} <b>URL</b> to encode
	 * @return url-encoded form of the input {@link String}
	 * @throws RuntimeException if error occurs
	 */
	public static String encode(String input) {
		try {
			return URLEncoder.encode(input, StandardCharsets.UTF_8.toString())
					.replaceAll("\\+", "%20");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
