package rozprochy.common.hermes.reflection;

import java.lang.annotation.Annotation;

public interface AnnotationVisitor<T extends Annotation, R> {

    R visit(T annotation);
    
}
