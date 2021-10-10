package lindar.acolyte;

import lindar.acolyte.util.ObjectsAcolyte;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ObjectAcolyteTest {

    @Test
    public void testSmallToLargeObjectCopy(){
        BaseDataClass baseEntry = new BaseDataClass("testName", "testBody", 1);

        FullDataClass fullEntry = new FullDataClass("placeHeldName", "placeHeldBody", 2, "placeHeldTitle", 2);

        FullDataClass resultEntry = ObjectsAcolyte.copy(baseEntry, fullEntry);

        assertEquals("placeHeldTitle", resultEntry.getTitle());
        assertEquals("testBody", resultEntry.getBody());
        assertEquals(1, resultEntry.getId());
        assertEquals(2, resultEntry.getUserId());
        assertEquals("testName", resultEntry.getName());
    }

    @Test
    public void testLargeToSmallObjectCopy(){
        BaseDataClass baseEntry = new BaseDataClass("testName", "testBody", 1);

        FullDataClass fullEntry = new FullDataClass("placeHeldName", "placeHeldBody", 2, "placeHeldTitle", 2);

        BaseDataClass resultEntry = ObjectsAcolyte.copy(fullEntry, baseEntry);

        assertEquals("placeHeldBody", resultEntry.getBody());
        assertEquals(2, resultEntry.getId());
        assertEquals("placeHeldName", resultEntry.getName());
    }

    @Test
    public void testNonOverrideObjectCopy(){
        BaseDataClass baseEntry = new BaseDataClass("testName", "testBody", 1);

        FullDataClass fullEntry = new FullDataClass(null, "placeHeldBody", 2, "placeHeldTitle", 2);

        FullDataClass resultEntry = ObjectsAcolyte.copy(baseEntry, fullEntry, false);

        assertEquals("placeHeldTitle", resultEntry.getTitle());
        assertEquals("placeHeldBody", resultEntry.getBody());
        assertEquals(2, resultEntry.getId());
        assertEquals(2, resultEntry.getUserId());
        assertEquals("testName", resultEntry.getName());
    }

    @Test
    public void testExcludedObjectCopy(){
        BaseDataClass baseEntry = new BaseDataClass("testName", "testBody", 1);

        FullDataClass fullEntry = new FullDataClass("placeHeldName", "placeHeldBody", 2, "placeHeldTitle", 2);

        FullDataClass resultEntry = ObjectsAcolyte.copy(baseEntry, fullEntry, true, Collections.singletonList("name"));

        assertEquals("placeHeldTitle", resultEntry.getTitle());
        assertEquals("testBody", resultEntry.getBody());
        assertEquals(1, resultEntry.getId());
        assertEquals(2, resultEntry.getUserId());
        assertEquals("placeHeldName", resultEntry.getName());
    }

    @Test
    public void testVariableGetterMethod(){
        FullDataClass fullEntry = new FullDataClass("placeHeldName", "placeHeldBody", 2, "placeHeldTitle", 2);

        List<String> fields = ObjectsAcolyte.listAllVariablesWithGetters(fullEntry);

        assertTrue(fields.contains("name"));
        assertTrue(fields.contains("body"));
        assertTrue(fields.contains("title"));
        assertTrue(fields.contains("id"));
        assertFalse(fields.contains("coreMsg"));
        assertTrue(fields.contains("class"));
    }

    @Test
    public void testClasslessVariableGetterMethod(){
        FullDataClass fullEntry = new FullDataClass("placeHeldName", "placeHeldBody", 2, "placeHeldTitle", 2);

        List<String> fields = ObjectsAcolyte.listAllVariablesWithGettersIgnoreGetClass(fullEntry);

        assertTrue(fields.contains("name"));
        assertTrue(fields.contains("body"));
        assertTrue(fields.contains("title"));
        assertTrue(fields.contains("id"));
        assertFalse(fields.contains("coreMsg"));
        assertFalse(fields.contains("class"));
    }

    @Test
    public void testExcludedVariableGetterMethod(){
        FullDataClass fullEntry = new FullDataClass("placeHeldName", "placeHeldBody", 2, "placeHeldTitle", 2);

        List<String> fields = ObjectsAcolyte.listAllVariablesWithGetters(fullEntry, Collections.singletonList("body"));

        assertTrue(fields.contains("name"));
        assertFalse(fields.contains("body"));
        assertTrue(fields.contains("title"));
        assertTrue(fields.contains("id"));
        assertFalse(fields.contains("coreMsg"));
    }

    @Test
    public void testNullOrEmptyMethod(){
        String test1 = "Not Null";
        String test2 = null;
        List<String> test3 = new ArrayList<String>();
        List<String> test4 = new ArrayList<String>();
        test4.add(test1);

        assertFalse(ObjectsAcolyte.objectNullOrEmpty(test1));
        assertTrue(ObjectsAcolyte.objectNullOrEmpty(test2));
        assertTrue(ObjectsAcolyte.objectNullOrEmpty(test3));
        assertFalse(ObjectsAcolyte.objectNullOrEmpty(test4));
    }

}
