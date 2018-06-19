
package com.dish.model;

import java.io.Serializable;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

public final class EmailAddress implements Serializable {

	private static final long serialVersionUID = -2990839949384592331L;

	private final String value;

	public EmailAddress(String emailAddress) {
		this.value = emailAddress;
	}

	public String getValue() {
		return value;
	}

	@Override
	public String toString() {
		return value;
	}

	@Override
	public boolean equals(Object obj) {

		if (this == obj) {
			return true;
		}

		if (!(obj instanceof EmailAddress)) {
			return false;
		}

		EmailAddress that = (EmailAddress) obj;
		return this.value.equals(that.value);
	}

	@Override
	public int hashCode() {
		return value.hashCode();
	}

	@Component
	static class EmailAddressToStringConverter implements Converter<EmailAddress, String> {

		@Override
		public String convert(EmailAddress source) {
			return source == null ? null : source.value;
		}
	}

	@Component
	static class StringToEmailAddressConverter implements Converter<String, EmailAddress> {

		public EmailAddress convert(String source) {
			return StringUtils.hasText(source) ? new EmailAddress(source) : null;
		}
	}
}
