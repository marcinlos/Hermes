package rozprochy.common.hermes.visitor;

import rozprochy.common.hermes.util.poset.Compare;
import rozprochy.common.hermes.util.poset.PosetOrder;

/**
 * Class poset comparator. "Less" (earlier in the poset graph) has 
 * "is supertype of" semantics ("less specific"). For example, 
 * <pre>
 * compare(List.class, Collection.class) == Compare.GT
 * </pre>
 * <p>
 * For the sake of precision, "is supertype of" is implemented by simple
 * {@linkplain Class#isAssignableFrom(Class)} call.
 * 
 * @author los
 */
public class ClassPosetOrder implements PosetOrder<Class<?>> {
    
    public static final ClassPosetOrder INSTANCE = new ClassPosetOrder();

    @Override
    public Compare compare(Class<?> t1, Class<?> t2) {
        if (t1.equals(t2)) {
            return Compare.EQ;
        } else if (t1.isAssignableFrom(t2)) {
            return Compare.LT;
        } else if (t2.isAssignableFrom(t1)) {
            return Compare.GT;
        } else {
            return Compare.NON_CMP;
        }
    }

}
