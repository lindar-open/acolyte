package lindar.acolyte;

import lindar.acolyte.util.ListsAcolyte;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ListAcolyteTest {

    @Test
    public void testContainIgnoreCaseMethod(){
        List<String> testData = new ArrayList<String>();
        testData.add("String1");
        testData.add("String2");
        testData.add("ignoreString");
        testData.add("String3");

        List<String> testData2 = new ArrayList<String>();
        testData2.add("String1");
        testData2.add("String2");
        testData2.add("String3");
        testData2.add("String4");

        List<String> testData3 = new ArrayList<String>();
        testData3.add("String1");
        testData3.add("String2");
        testData3.add("IgNoReStRiNg");
        testData3.add("String3");

        assertTrue(ListsAcolyte.containsIgnoreCase(testData, "ignoreString"));
        assertTrue(ListsAcolyte.containsIgnoreCase(testData3, "ignoreString"));
        assertFalse(ListsAcolyte.containsIgnoreCase(testData2, "ignoreString"));
    }

    @Test
    public void testContainAllIgnoreCaseMethod(){
        String itemToFind = "testString";
        String itemToFind2 = "secondString";
        String[] itemsToFind = new String[2];
        itemsToFind[0] = itemToFind;
        itemsToFind[1] = itemToFind2;

        List<String> testData = new ArrayList<String>();
        testData.add("String1");
        testData.add("String2");
        testData.add("String3");
        testData.add("String4");

        List<String> testData2 = new ArrayList<String>();
        testData2.add("String1");
        testData2.add("String2");
        testData2.add("String3");
        testData2.add(itemToFind);
        testData2.add("String4");

        List<String> testData3 = new ArrayList<String>();
        testData3.add("String1");
        testData3.add(itemToFind2);
        testData3.add("String2");
        testData3.add("String3");
        testData3.add("String4");

        List<String> testData4 = new ArrayList<String>();
        testData4.add("String1");
        testData4.add("String2");
        testData4.add(itemToFind);
        testData4.add(itemToFind2);
        testData4.add("String3");
        testData4.add("String4");

        List<String> testData5 = new ArrayList<String>();
        testData5.add("String1");
        testData5.add("String2");
        testData5.add("TeStStRiNg");
        testData5.add("String3");
        testData5.add("SeCoNdStRiNg");
        testData5.add("String4");


        assertFalse(ListsAcolyte.containsAllIgnoreCase(testData, itemsToFind));
        assertFalse(ListsAcolyte.containsAllIgnoreCase(testData2, itemsToFind));
        assertFalse(ListsAcolyte.containsAllIgnoreCase(testData3, itemsToFind));
        assertTrue(ListsAcolyte.containsAllIgnoreCase(testData4, itemsToFind));
        assertTrue(ListsAcolyte.containsAllIgnoreCase(testData5, itemsToFind));
    }

    @Test
    public void testContainAnyIgnoreCaseMethod(){
        List<String> testData = new ArrayList<String>();
        testData.add("String1");
        testData.add("String2");
        testData.add("ignoreString");
        testData.add("ignoreString2");
        testData.add("String3");

        List<String> testData2 = new ArrayList<String>();
        testData2.add("String1");
        testData2.add("String2");
        testData2.add("String3");
        testData2.add("String4");

        List<String> testData3 = new ArrayList<String>();
        testData3.add("String1");
        testData3.add("String2");
        testData3.add("ignoreString");
        testData3.add("String4");

        List<String> testData4 = new ArrayList<String>();
        testData4.add("String1");
        testData4.add("String2");
        testData4.add("IgnOreString");
        testData4.add("String4");

        List<String> testData5 = new ArrayList<String>();
        testData5.add("String1");
        testData5.add("String2");
        testData5.add("IgnOreString2");
        testData5.add("String4");

        String[] ignoreCases = new String[2];
        ignoreCases[0] = "ignoreString";
        ignoreCases[1] = "ignoreString2";

        assertTrue(ListsAcolyte.containsAnyIgnoreCase(testData, ignoreCases));
        assertFalse(ListsAcolyte.containsAnyIgnoreCase(testData2, ignoreCases));
        assertTrue(ListsAcolyte.containsAnyIgnoreCase(testData3, ignoreCases));
        assertTrue(ListsAcolyte.containsAnyIgnoreCase(testData3, ignoreCases));
        assertTrue(ListsAcolyte.containsAnyIgnoreCase(testData3, ignoreCases));
    }

    @Test
    public void testEmptyMethods(){
        List<String> testData = new ArrayList<String>();
        testData.add("String1");
        testData.add("String2");
        testData.add("ignoreString");
        testData.add("ignoreString2");
        testData.add("String3");

        assertTrue(ListsAcolyte.isNotEmpty(testData));
        assertFalse(ListsAcolyte.isEmpty(testData));
    }

    @Test
    public void testGetItemMethods(){
        List<String> testData = new ArrayList<String>();
        testData.add(null);
        testData.add("String");
        testData.add("String2");
        testData.add("String3");
        testData.add(null);

        assertEquals("String", ListsAcolyte.getFirstNonNullItemIfExists(testData).get());
        assertEquals("String3", ListsAcolyte.getLastNonNullItemIfExists(testData).get());
        assertEquals(Optional.empty(), ListsAcolyte.getFirstItemIfExists(testData));
        assertEquals(Optional.empty(), ListsAcolyte.getLastItemIfExists(testData));
    }

    @Test
    public void testHasOneEntryMethods(){
        List<String> testData = new ArrayList<String>();
        testData.add("String1");

        List<String> testData2 = new ArrayList<String>();
        testData2.add(null);
        testData2.add("String2");
        testData2.add(null);
        testData2.add(null);

        List<String> testData3 = new ArrayList<String>();
        testData3.add("String1");
        testData3.add("String2");
        testData3.add("ignoreString");
        testData3.add("ignoreString2");
        testData3.add("String3");

        assertTrue(ListsAcolyte.hasOnlyOneItem(testData));
        assertFalse(ListsAcolyte.hasOnlyOneItem(testData2));
        assertTrue(ListsAcolyte.hasOnlyOneNonNullItem(testData2));
        assertTrue(ListsAcolyte.hasOnlyOneNonNullItem(testData));
        assertFalse(ListsAcolyte.hasOnlyOneItem(testData3));
        assertFalse(ListsAcolyte.hasOnlyOneNonNullItem(testData3));
    }

    @Test
    public void testDefaultMethods(){
        List<String> testData = new ArrayList<String>();
        testData.add("String1");
        testData.add("String2");
        testData.add("String3");

        List<String> emptyData = new ArrayList<String>();

        List<String> nullData = null;

        List<String> testData2 = new ArrayList<String>();
        testData2.add("testString1");
        testData2.add("testString2");

        List<String> result1 = ListsAcolyte.defaultIfEmpty(emptyData, testData);
        List<String> result2 = ListsAcolyte.defaultIfNull(nullData, testData);
        List<String> result3 = ListsAcolyte.defaultIfNull(nullData);
        List<String> result4 = ListsAcolyte.defaultIfNull(testData, testData2);
        List<String> result5 = ListsAcolyte.defaultIfNull(testData);
        List<String> result6 = ListsAcolyte.defaultIfEmpty(testData, testData2);

        assertEquals(testData, result1);
        assertEquals(testData, result2);
        assertEquals(emptyData, result3);
        assertEquals(testData, result4);
        assertEquals(testData, result5);
        assertEquals(testData, result6);
    }

    @Test
    public void testListOfMethods(){
        String[] dataArray = new String[3];
        dataArray[0] = "Entry1";
        dataArray[1] = "Entry2";
        dataArray[2] = "Entry3";

        List<String> result1 = ListsAcolyte.listOf(dataArray);
        List<String> result2 = ListsAcolyte.arrayListOf(dataArray);

        List<String> dataList = new ArrayList<String>();
        dataList.add("Entry1");
        dataList.add("Entry2");
        dataList.add("Entry3");

        assertEquals(dataList, result1);
        assertEquals(dataList, result2);
    }

}
