package rozprochy.common.hermes.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import rozprochy.common.hermes.util.poset.Compare;
import rozprochy.common.hermes.util.poset.DAGPoset;
import rozprochy.common.hermes.util.poset.PosetOrder;

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
                
                if (s1.equals(s2)) {
                    return Compare.EQ;
                } else if (s1.containsAll(s2)) {
                    return Compare.GT;
                } else if (s2.containsAll(s1)) {
                    return Compare.LT;
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
    
    @Test
    public void contains_NonexistantElement_ReturnsFalse() {
        assertFalse(poset.contains("abcd"));
    }
    
    @Test
    public void glb_CDEF_ReturnsDEandDF() {
        System.out.println(poset);
        assertEquals(new HashSet<String>(Arrays.asList("de", "df")),
                poset.greatestLowerBound("ddefgh"));
    }

}
