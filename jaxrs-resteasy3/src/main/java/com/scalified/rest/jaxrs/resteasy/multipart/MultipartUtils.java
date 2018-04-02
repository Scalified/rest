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

package com.scalified.rest.jaxrs.resteasy.multipart;

import com.scalified.rest.jaxrs.extension.ExtendedMediaType;
import org.apache.commons.lang3.StringUtils;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.HttpHeaders;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * An utility class for working with {@link ExtendedMediaType#MULTIPART_FORM_DATA}
 *
 * @author shell
 * @version 1.0.0
 * @since 1.0.0
 */
public final class MultipartUtils {

	/**
	 * File part key
	 */
	private static final String FILE_PART_KEY = "file";

	/**
	 * File name constant
	 */
	private static final String FILENAME = "filename";

	/**
	 * File name regex
	 */
	private static final String FILENAME_REGEX = "[=\"]";

	/**
	 * Prevents {@link MultipartUtils} creation
	 */
	private MultipartUtils() {
	}

	/**
	 * Extracts {@link String} part from the given {@link MultipartFormDataInput}
	 * having the given key
	 *
	 * @param input {@link MultipartFormDataInput} to extract from
	 * @param key   a key to extract by
	 * @return extracted {@link String} part
	 * @throws RuntimeException if extraction was unsuccessful
	 */
	public static String extractPart(MultipartFormDataInput input, String key) {
		try {
			return input.getFormDataPart(key, new GenericType<String>() {
			});
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Extracts files from the given {@link MultipartFormDataInput}
	 * <p>
	 * Returns map containing file name to file content entries
	 *
	 * @param input an input to extract files from
	 * @return map containing extracted file name to file content entries
	 * @throws RuntimeException if extraction was unsuccessful
	 */
	public static Map<String, byte[]> extractFiles(MultipartFormDataInput input) {
		Collection<InputPart> parts = input.getFormDataMap().get(FILE_PART_KEY);

		Map<String, byte[]> files = new LinkedHashMap<>();

		parts.forEach(part -> part.getHeaders()
				.entrySet()
				.stream()
				.filter(h -> HttpHeaders.CONTENT_DISPOSITION.equals(h.getKey()))
				.flatMap(h -> h.getValue().stream())
				.filter(v -> v.contains(FILENAME))
				.findFirst()
				.map(MultipartUtils::parseFileName)
				.ifPresent(name -> {
					byte[] content;
					try {
						content = part.getBody(new GenericType<byte[]>() {
						});
					} catch (IOException e) {
						throw new RuntimeException(e);
					}
					files.put(name, content);
				}));

		return files;
	}

	/**
	 * Parses file name from the given input. The given input expected to be the value of
	 * {@link HttpHeaders#CONTENT_DISPOSITION} header
	 *
	 * @param input {@link HttpHeaders#CONTENT_DISPOSITION} header value
	 * @return parsed file name
	 * @throws RuntimeException if parsing was unsuccessful
	 */
	private static String parseFileName(String input) {
		String parsed = StringUtils.removePattern(StringUtils.substringAfter(input, FILENAME), FILENAME_REGEX);
		try {
			return URLDecoder.decode(parsed, StandardCharsets.UTF_8.displayName());
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}

}