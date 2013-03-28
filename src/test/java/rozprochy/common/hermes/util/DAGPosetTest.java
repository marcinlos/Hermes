package rozprochy.common.hermes.util;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class DAGPosetTest {
    
    private static PosetOrder<String> order;
    private DAGPoset<String> poset;
    private List<String> strings;
    
    @BeforeClass
    public static void createOrder() {
        order = new PosetOrder<String>() {
            
            private Set<Character> toSet(String s) {
                Set<Character> chars = new HashSet<Character>();
                for (char c: s.toCharArray()) {
                    chars.add(c);
                }
                return chars;
            }
            
            @Override
            public Compare compare(String t1, String t2) {
                Set<Character> s1 = toSet(t1);
                Set<Character> s2 = toSet(t2);
                
                if (s1.containsAll(s2)) {
                    return Compare.LT;
                } else if (s2.containsAll(s1)) {
                    return Compare.GT;
                } else {
                    return Compare.NON_CMP;
                }
            }
        };
    }
    
    @Before
    public void createPoset() {
        strings = Arrays.asList("a", "bc", "abc", "d", "de", "df", "cdef");
        poset = new DAGPoset<String>(order);
        for (String s: strings) {
            poset.add(s);
        }
    }

    
    @Test
    public void contains_ExistingElement_ReturnsTrue() {
        for (String s: strings) {
            assertTrue("'" + s + "' not there", poset.contains(s));
        }
    }

}
