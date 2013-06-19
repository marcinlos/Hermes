package rozprochy.common.hermes.reflection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Map;

import mlos.hermes.reflection.TypeToken;

import org.junit.Before;
import org.junit.Test;

public class TypeTokenTest {
    
    private TypeToken<List<String>> listString;
    private TypeToken<List<Object>> listObject;
    private TypeToken<Map<String, List<String>>> mapStringListString;
    private TypeToken<Map<String, List<Integer>>> mapStringListInteger;
    
    @Before
    public void create() {
        listString = new TypeToken<List<String>>() { };
        listObject = new TypeToken<List<Object>>() { };
        mapStringListString = new TypeToken<Map<String, List<String>>>() { };
        mapStringListInteger = new TypeToken<Map<String, List<Integer>>>() { };
    }
    
    @Test
    public void equals_SameClasses_ReturnsTrue() {
        assertEquals(listString, new TypeToken<List<String>>() { });
        assertEquals(mapStringListString, 
                new TypeToken<Map<String, List<String>>>() { });
    }
    
    @Test
    public void equals_DifferentClasses_ReturnsFalse() {
        assertTrue(! listString.equals(listObject));
        assertTrue(! mapStringListInteger.equals(mapStringListString));
    }

}
