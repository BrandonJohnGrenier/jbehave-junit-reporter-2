package com.moralesce.jbehave;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.jbehave.scenario.Configuration;

@Retention(RetentionPolicy.RUNTIME)
@Target({ java.lang.annotation.ElementType.TYPE })
public @interface UseConfiguration {
	public abstract Class<? extends Configuration> value();
}
