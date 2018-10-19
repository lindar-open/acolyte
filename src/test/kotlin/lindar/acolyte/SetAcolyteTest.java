package lindar.acolyte;

import lindar.acolyte.util.SetsAcolyte;
import org.junit.Test;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class SetAcolyteTest {

    @Test
    public void testSetOfMethods(){
        String[] dataArray = new String[3];
        dataArray[0] = "Entry1";
        dataArray[1] = "Entry2";
        dataArray[2] = "Entry3";

        Set<String> dataSet = new HashSet<String>();
        dataSet.add(dataArray[0]);
        dataSet.add(dataArray[1]);
        dataSet.add(dataArray[2]);

        Set<String> dataSet2 = new LinkedHashSet<String>();
        dataSet2.add(dataArray[0]);
        dataSet2.add(dataArray[1]);
        dataSet2.add(dataArray[2]);

        Set<String> result1 = SetsAcolyte.setOf(dataArray);
        Set<String> result2 = SetsAcolyte.hashSetOf(dataArray);
        Set<String> result3 = SetsAcolyte.linkedHashSetOf(dataArray);

        assertEquals(dataSet, result1);
        assertEquals(dataSet, result2);
        assertEquals(dataSet2, result3);
    }

    @Test
    public void testIgnoreCaseMethod(){
        String ignoreCase = "ignoreString";

        Set<String> dataSet = new HashSet<String>();
        dataSet.add("Entry1");
        dataSet.add("Entry2");
        dataSet.add("Entry3");

        Set<String> dataSet2 = new HashSet<String>();
        dataSet2.add("Entry1");
        dataSet2.add("Entry2");
        dataSet2.add(ignoreCase);
        dataSet2.add("Entry3");

        assertTrue(SetsAcolyte.containsIgnoreCase(dataSet2, ignoreCase));
        assertFalse(SetsAcolyte.containsIgnoreCase(dataSet, ignoreCase));
    }

    @Test
    public void testEmptyMethods(){
        Set<String> dataSet = new HashSet<String>();
        dataSet.add("Entry1");
        dataSet.add("Entry2");
        dataSet.add("Entry3");

        Set<String> dataSet2 = new HashSet<String>();

        assertTrue(SetsAcolyte.isEmpty(dataSet2));
        assertTrue(SetsAcolyte.isNotEmpty(dataSet));
        assertFalse(SetsAcolyte.isEmpty(dataSet));
        assertFalse(SetsAcolyte.isNotEmpty(dataSet2));
    }

    @Test
    public void testDefaultMethods(){
        Set<String> dataSet = new HashSet<String>();
        dataSet.add("Entry1");
        dataSet.add("Entry2");
        dataSet.add("Entry3");

        Set<String> dataSet2 = new HashSet<String>();
        dataSet2.add("testEntry1");
        dataSet2.add("testEntry2");
        dataSet2.add("testEntry3");
        dataSet2.add("testEntry4");

        Set<String> dataSet3 = new HashSet<String>();
        Set<String> dataSet4 = null;

        Set<String> result1 = SetsAcolyte.defaultIfNull(dataSet4);
        Set<String> result2 = SetsAcolyte.defaultIfNull(dataSet);
        Set<String> result3 = SetsAcolyte.defaultIfNull(dataSet4, dataSet2);
        Set<String> result4 = SetsAcolyte.defaultIfNull(dataSet, dataSet2);
        Set<String> result5 = SetsAcolyte.defaultIfEmpty(dataSet3, dataSet2);
        Set<String> result6 = SetsAcolyte.defaultIfEmpty(dataSet, dataSet2);

        assertEquals(dataSet3, result1);
        assertEquals(dataSet, result2);
        assertEquals(dataSet2, result3);
        assertEquals(dataSet, result4);
        assertEquals(dataSet2, result5);
        assertEquals(dataSet, result6);
    }

    @Test
    public void testHasItemMethods(){
        Set<String> dataSet = new HashSet<String>();
        dataSet.add("Entry1");
        dataSet.add("Entry2");
        dataSet.add("Entry3");

        Set<String> dataSet2 = new HashSet<String>();
        dataSet2.add("testEntry1");

        Set<String> dataSet3 = new HashSet<String>();

        Set<String> dataSet4 = new HashSet<String>();
        dataSet4.add(null);

        assertTrue(SetsAcolyte.hasOnlyOneItem(dataSet2));
        assertTrue(SetsAcolyte.hasOnlyOneItem(dataSet4));
        assertFalse(SetsAcolyte.hasOnlyOneItem(dataSet));
        assertFalse(SetsAcolyte.hasOnlyOneItem(dataSet3));

        assertTrue(SetsAcolyte.hasOnlyOneNonNullItem(dataSet2));
        assertFalse(SetsAcolyte.hasOnlyOneNonNullItem(dataSet4));
        assertFalse(SetsAcolyte.hasOnlyOneNonNullItem(dataSet3));
        assertFalse(SetsAcolyte.hasOnlyOneNonNullItem(dataSet));
    }

}
