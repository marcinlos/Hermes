package rozprochy.common.hermes.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import rozprochy.common.hermes.parsing.ValueParser;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
public @interface ParseWith {
    Class<? extends ValueParser<?>> value();
}
