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

package com.scalified.rest.jaxrs.cors;

import com.scalified.rest.jaxrs.extension.ExtendedHttpHeaders;
import org.apache.commons.lang3.StringUtils;

import javax.ws.rs.ForbiddenException;
import javax.ws.rs.HttpMethod;
import javax.ws.rs.container.*;
import javax.ws.rs.core.Response;
import java.util.Objects;

/**
 * A filter, used to filter <b>CORS</b> requests and responses
 * <p>
 * This filter must be preconfigured with the {@link javax.ws.rs.core.Feature}
 * <pre>
 *    import javax.ws.rs.core.Configuration;
 *    import javax.ws.rs.core.Feature;
 *    import javax.ws.rs.core.FeatureContext;
 *    import javax.ws.rs.ext.Provider;
 *
 *   {@code @Provider}
 *    public class CorsFeature implements Feature {
 *
 *       {@code @Override}
 *        public boolean configure(FeatureContext context) {
 *            CorsFilter filter = new CorsFilter("*");
 *            context.register(filter);
 *            return true;
 *        }
 *
 *    }
 * </pre>
 *
 * @author shell
 * @version 1.0.0
 * @since 1.0.0
 */
@PreMatching
public class CorsFilter implements ContainerRequestFilter, ContainerResponseFilter {

	/**
	 * All allowed origins
	 */
	private static final String ALL_ALLOWED_ORIGINS = "*";

	/**
	 * CORS failure property key
	 */
	private static final String CORS_FAILURE_PROPERTY_KEY = "cors.failure";

	/**
	 * Allowed origins
	 */
	private final String allowedOrigins;

	/**
	 * Creates {@link CorsFilter} instance
	 *
	 * @param allowedOrigins allowed origins
	 */
	public CorsFilter(String allowedOrigins) {
		this.allowedOrigins = allowedOrigins;
	}

	/**
	 * Filter method called before a request has been dispatched to a resource.
	 * <p>
	 * Filters in the filter chain are ordered according to their {@code javax.annotation.Priority}
	 * class-level annotation value.
	 * If a request filter produces a response by calling {@link ContainerRequestContext#abortWith}
	 * method, the execution of the (either pre-match or post-match) request filter
	 * chain is stopped and the response is passed to the corresponding response
	 * filter chain (either pre-match or post-match). For example, a pre-match
	 * caching filter may produce a response in this way, which would effectively
	 * skip any post-match request filters as well as post-match response filters.
	 * Note however that a responses produced in this manner would still be processed
	 * by the pre-match response filter chain.
	 * </p>
	 *
	 * @param requestContext request context.
	 * @see PreMatching
	 */
	@Override
	public void filter(ContainerRequestContext requestContext) {
		String origin = requestContext.getHeaderString(ExtendedHttpHeaders.ORIGIN);
		if (StringUtils.isBlank(origin)) {
			return;
		}
		if (requestContext.getMethod().equalsIgnoreCase(HttpMethod.OPTIONS)) {
			preFlight(origin, requestContext);
		} else {
			checkOrigin(requestContext, origin);
		}
	}

	/**
	 * Filter method called after a response has been provided for a request
	 * (either by a {@link ContainerRequestFilter request filter} or by a
	 * matched resource method.
	 * <p>
	 * Filters in the filter chain are ordered according to their {@code javax.annotation.Priority}
	 * class-level annotation value.
	 * </p>
	 *
	 * @param requestContext  request context.
	 * @param responseContext response context.
	 */
	@Override
	public void filter(ContainerRequestContext requestContext,
	                   ContainerResponseContext responseContext) {
		String origin = requestContext.getHeaderString(ExtendedHttpHeaders.ORIGIN);
		if (StringUtils.isBlank(origin)
				|| requestContext.getMethod().equalsIgnoreCase(HttpMethod.OPTIONS)
				|| Objects.nonNull(requestContext.getProperty(CORS_FAILURE_PROPERTY_KEY))) {
			return;
		}
		responseContext.getHeaders().putSingle(ExtendedHttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, origin);
		responseContext.getHeaders()
				.putSingle(ExtendedHttpHeaders.ACCESS_CONTROL_ALLOW_CREDENTIALS, Boolean.TRUE.toString());
	}

	/**
	 * Adds the required headers to response before a request has been dispatched to a resource
	 *
	 * @param origin         an {@link ExtendedHttpHeaders#ORIGIN} header value
	 * @param requestContext request context
	 */
	private void preFlight(String origin, ContainerRequestContext requestContext) {
		checkOrigin(requestContext, origin);

		Response.ResponseBuilder builder = Response.ok();
		builder.header(ExtendedHttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, origin);
		builder.header(ExtendedHttpHeaders.ACCESS_CONTROL_ALLOW_CREDENTIALS, Boolean.TRUE.toString());

		String requestMethods = requestContext.getHeaderString(ExtendedHttpHeaders.ACCESS_CONTROL_REQUEST_METHOD);
		if (Objects.nonNull(requestMethods)) {
			builder.header(ExtendedHttpHeaders.ACCESS_CONTROL_ALLOW_METHODS, requestMethods);
		}

		String allowHeaders = requestContext.getHeaderString(ExtendedHttpHeaders.ACCESS_CONTROL_REQUEST_HEADERS);
		if (Objects.nonNull(allowHeaders)) {
			builder.header(ExtendedHttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS, allowHeaders);
		}

		requestContext.abortWith(builder.build());
	}

	/**
	 * Checks whether request context contains the allowed origin
	 *
	 * @param requestContext request context
	 * @param origin         an {@link ExtendedHttpHeaders#ORIGIN} header value
	 */
	private void checkOrigin(ContainerRequestContext requestContext, String origin) {
		if (!StringUtils.containsAny(allowedOrigins, ALL_ALLOWED_ORIGINS, origin)) {
			requestContext.setProperty(CORS_FAILURE_PROPERTY_KEY, true);
			throw new ForbiddenException("Origin not allowed: " + origin);
		}
	}

}
