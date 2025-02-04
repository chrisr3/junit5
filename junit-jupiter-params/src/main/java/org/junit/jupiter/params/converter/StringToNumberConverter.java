/*
 * Copyright 2015-2023 the original author or authors.
 *
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v2.0 which
 * accompanies this distribution and is available at
 *
 * https://www.eclipse.org/legal/epl-v20.html
 */

package org.junit.jupiter.params.converter;

import static java.util.Collections.unmodifiableMap;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

class StringToNumberConverter implements StringToObjectConverter {

	private static final Map<Class<?>, Function<String, ?>> CONVERTERS;
	static {
		Map<Class<?>, Function<String, ?>> converters = new HashMap<>();
		converters.put(Byte.class, Byte::decode);
		converters.put(Short.class, Short::decode);
		converters.put(Integer.class, Integer::decode);
		converters.put(Long.class, Long::decode);
		converters.put(Float.class, Float::valueOf);
		converters.put(Double.class, Double::valueOf);
		CONVERTERS = unmodifiableMap(converters);
	}

	@Override
	public boolean canConvert(Class<?> targetType) {
		return CONVERTERS.containsKey(targetType);
	}

	@Override
	public Object convert(String source, Class<?> targetType) {
		return CONVERTERS.get(targetType).apply(source.replace("_", ""));
	}

}
