package lindar.acolyte;

import lindar.acolyte.util.MapsAcolyte;
import lindar.acolyte.vo.Pair;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MapAcolyteTest {

    @Test
    public void testMapOfMethods(){
        Pair<String, String> entry1 = Pair.of("Key1", "Value1");
        Pair<String, String> entry2 = Pair.of("Key2", "Value2");
        Pair<String, String> entry3 = Pair.of("Key3", "Value3");

        Pair<String,String>[] pairData = new Pair[3];
        pairData[0] = entry1;
        pairData[1] = entry2;
        pairData[2] = entry3;

        Map<String, String> dataMap = MapsAcolyte.mapOf(pairData);
        Map<String, String> dataHashMap = MapsAcolyte.hashMapOf(pairData);

        assertTrue(dataMap.containsKey("Key1"));
        assertEquals("Value1", dataMap.get("Key1"));
        assertTrue(dataMap.containsKey("Key2"));
        assertEquals("Value2", dataMap.get("Key2"));
        assertTrue(dataMap.containsKey("Key3"));
        assertEquals("Value3", dataMap.get("Key3"));

        assertTrue(dataHashMap.containsKey("Key1"));
        assertEquals("Value1", dataHashMap.get("Key1"));
        assertTrue(dataHashMap.containsKey("Key2"));
        assertEquals("Value2", dataHashMap.get("Key2"));
        assertTrue(dataHashMap.containsKey("Key3"));
        assertEquals("Value3", dataHashMap.get("Key3"));
    }

    @Test
    public void testEmptyMethods(){
        Pair<String, String> entry1 = Pair.of("Key1", "Value1");
        Pair<String, String> entry2 = Pair.of("Key2", "Value2");
        Pair<String, String> entry3 = Pair.of("Key3", "Value3");

        Pair<String,String>[] pairData = new Pair[3];
        pairData[0] = entry1;
        pairData[1] = entry2;
        pairData[2] = entry3;

        Map<String, String> dataMap = MapsAcolyte.mapOf(pairData);
        Map<String, String> emptyMap = new HashMap<String, String>();

        assertTrue(MapsAcolyte.isEmpty(emptyMap));
        assertFalse(MapsAcolyte.isNotEmpty(emptyMap));

        assertTrue(MapsAcolyte.isNotEmpty(dataMap));
        assertFalse(MapsAcolyte.isEmpty(dataMap));
    }

    @Test
    public void testDefaultMethods(){
        Map<String, String> nullMap = null;
        Map<String, String> emptyMap = new HashMap<String, String>();

        Pair<String, String> entry1 = Pair.of("Key1", "Value1");
        Pair<String, String> entry2 = Pair.of("Key2", "Value2");
        Pair<String, String> entry3 = Pair.of("Key3", "Value3");

        Pair<String,String>[] pairData = new Pair[3];
        pairData[0] = entry1;
        pairData[1] = entry2;
        pairData[2] = entry3;

        Map<String, String> dataMap = MapsAcolyte.mapOf(pairData);

        pairData[0] = Pair.of("DefaultKey1", "DefaultValue1");
        pairData[1] = Pair.of("DefaultKey2", "DefaultValue2");
        pairData[2] = Pair.of("DefaultKey3", "DefaultValue3");

        Map<String, String> defaultMap = MapsAcolyte.mapOf(pairData);

        Map<String, String> result1 = MapsAcolyte.defaultIfNull(dataMap);
        Map<String, String> result2 = MapsAcolyte.defaultIfNull(nullMap);
        Map<String, String> result3 = MapsAcolyte.defaultIfNull(dataMap, defaultMap);
        Map<String, String> result4 = MapsAcolyte.defaultIfNull(nullMap, defaultMap);
        Map<String, String> result5 = MapsAcolyte.defaultIfEmpty(dataMap, defaultMap);
        Map<String, String> result6 = MapsAcolyte.defaultIfEmpty(emptyMap, defaultMap);

        assertEquals(dataMap, result1);
        assertEquals(emptyMap, result2);
        assertEquals(dataMap, result3);
        assertEquals(defaultMap, result4);
        assertEquals(dataMap, result5);
        assertEquals(defaultMap, result6);
    }

}
