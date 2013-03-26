package rozprochy.common.hermes.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import rozprochy.common.hermes.ValueParser;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
public @interface Param {
    
    String[] names();
    Class<?> parser() default ValueParser.class;

}
