package lindar.acolyte;

import lindar.acolyte.util.UrlAcolyte;
import lindar.acolyte.vo.PageableVO;
import lindar.acolyte.vo.Pair;
import lindar.acolyte.vo.SortDirection;
import lindar.acolyte.vo.SortVO;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class UrlAcolyteTest {

    @Test
    public void testSingleConcatMethod(){
        String testUrl = "http://www.testsite.com/";
        String testUrl2 = "http://www.testsite.com";
        String testSuffix1 = "pages/new";
        String testSuffix2 = "/pages/old";

        String result1 = UrlAcolyte.safeConcat(testUrl, testSuffix1);
        String result2 = UrlAcolyte.safeConcat(testUrl, testSuffix2);
        String result3 = UrlAcolyte.safeConcat(testUrl2, testSuffix1);
        String result4 = UrlAcolyte.safeConcat(testUrl2, testSuffix2);

        assertEquals("http://www.testsite.com/pages/new", result1);
        assertEquals("http://www.testsite.com/pages/old", result2);
        assertEquals("http://www.testsite.com/pages/new", result3);
        assertEquals("http://www.testsite.com/pages/old", result4);
    }

    @Test
    public void testMultipleConcatMethod(){
        String testUrl = "http://www.testsite.com/";
        String testUrl2 = "http://www.testsite.com";
        String testSuffix1 = "pages/new";
        String testSuffix2 = "/pages/old";
        String testSuffix3 = "/create";
        String testSuffix4 = "edit";

        String[] suffixes1 = new String[3];
        suffixes1[0] = testSuffix1;
        suffixes1[1] = testSuffix3;
        suffixes1[2] = testSuffix4;

        String result1 = UrlAcolyte.safeConcat(testUrl, suffixes1);

        suffixes1[0] = testSuffix2;

        String result2 = UrlAcolyte.safeConcat(testUrl2, suffixes1);

        assertEquals("http://www.testsite.com/pages/new/create/edit", result1);
        assertEquals("http://www.testsite.com/pages/old/create/edit", result2);
    }

    @Test
    public void testPaginationMethod(){
        String testUrl = "http://www.testsite.com/";

        List<SortVO> sortElements = new ArrayList<SortVO>();
        sortElements.add(new SortVO("name", SortDirection.DESC));

        PageableVO pageAble1 = new PageableVO(1, 5, sortElements);
        String result1 = UrlAcolyte.addPaginationParams(testUrl, pageAble1);

        sortElements.add(new SortVO("user", SortDirection.ASC));

        PageableVO pageAble2 = new PageableVO(2, 4, sortElements);
        PageableVO pageAble3 = new PageableVO(4, 2, null);

        String result2 = UrlAcolyte.addPaginationParams(testUrl, pageAble2);
        String result3 = UrlAcolyte.addPaginationParams(testUrl, pageAble3);

        assertEquals("http://www.testsite.com?page=1&size=5&sort=name,DESC",result1);
        assertEquals("http://www.testsite.com?page=2&size=4&sort=name,DESC&sort=user,ASC",result2);
        assertEquals("http://www.testsite.com?page=4&size=2",result3);
    }

    @Test
    public void testSortParamMethod(){
        String testUrl = "http://www.testsite.com/";
        SortVO sortParam = new SortVO("name", SortDirection.DESC);
        SortVO sortParam2 = new SortVO("name", SortDirection.ASC);

        String result1 = UrlAcolyte.addSortParam(testUrl, sortParam);
        String result2 = UrlAcolyte.addSortParam(testUrl, sortParam2);

        assertEquals("http://www.testsite.com?sort=name,DESC", result1);
        assertEquals("http://www.testsite.com?sort=name,ASC", result2);
    }

    @Test
    public void testAddMapParamsMethods(){
        String testUrl = "http://www.testsite.com/";

        Map<String, String> params1 = new HashMap<String, String>();
        params1.put("user", "testUser");
        params1.put("page", "2");

        Map<String, String> params2 = new HashMap<String, String>();
        params2.put("user", "testUser2");

        Map<String, String> params3 = new HashMap<String, String>();
        params3.put("user", "testUser3");
        params3.put("blanktest", null);

        String result1 = UrlAcolyte.addParams(testUrl, params1);
        String result2 = UrlAcolyte.addParams(testUrl, params2);
        String result3 = UrlAcolyte.addParams(testUrl, params3);

        assertEquals("http://www.testsite.com?page=2&user=testUser", result1);
        assertEquals("http://www.testsite.com?user=testUser2", result2);
        assertEquals("http://www.testsite.com?blanktest=null&user=testUser3", result3);
    }

    @Test
    public void testAddPairParamsMethod(){
        String testUrl = "http://www.testsite.com/";

        Pair<String, String>[] params1 = new Pair[2];
        params1[0] = Pair.of("name", "testname");
        params1[1] = Pair.of("page", "2");

        Pair<String, String>[] params2 = new Pair[2];
        params2[0] = Pair.of("name", "testname2");
        params2[1] = Pair.of("page", null);

        Pair<String, String>[] params3 = new Pair[1];
        params3[0] = Pair.of("name", "testname3");

        Pair<String, String> singleParam = Pair.of("name", "testname4");

        String result1 = UrlAcolyte.addParams(testUrl, params1);
        String result2 = UrlAcolyte.addParams(testUrl, params2);
        String result3 = UrlAcolyte.addParams(testUrl, params3);
        String result4 = UrlAcolyte.addParams(testUrl, singleParam);

        assertEquals("http://www.testsite.com?name=testname&page=2",result1);
        assertEquals("http://www.testsite.com?name=testname2&page=null",result2);
        assertEquals("http://www.testsite.com?name=testname3",result3);
        assertEquals("http://www.testsite.com?name=testname4",result4);
    }

    @Test
    public void testAddParamsIfNotBlankMethods(){
        String testUrl = "http://www.testsite.com/";

        Pair<String, String>[] params1 = new Pair[2];
        params1[0] = Pair.of("name", "testname");
        params1[1] = Pair.of("page", "2");

        Pair<String, String>[] params2 = new Pair[2];
        params2[0] = Pair.of("name", "testname2");
        params2[1] = Pair.of("page", null);

        Map<String, String> mapParams2 = new HashMap<String, String>();
        mapParams2.put("user", "testUser3");
        mapParams2.put("blanktest", null);

        Map<String, String> mapParams1 = new HashMap<String, String>();
        mapParams1.put("user", "testUser");
        mapParams1.put("page", "2");

        String result1 = UrlAcolyte.addParamsIfNotBlank(testUrl, params1);
        String result2 = UrlAcolyte.addParamsIfNotBlank(testUrl, params2);
        String result3 = UrlAcolyte.addParamsIfNotBlank(testUrl, mapParams1);
        String result4 = UrlAcolyte.addParamsIfNotBlank(testUrl, mapParams2);

        assertEquals("http://www.testsite.com?name=testname&page=2", result1);
        assertEquals("http://www.testsite.com?name=testname2", result2);
        assertEquals("http://www.testsite.com?page=2&user=testUser", result3);
        assertEquals("http://www.testsite.com?user=testUser3", result4);
    }

    @Test
    public void testAddPathParamsMethods(){
        String testUrl = "http://www.testsite.com/user/{}/page/{}/name/{}";
        List<String> params1 = new ArrayList<String>();
        params1.add("testuser");
        params1.add("2");
        params1.add("testname");

        List<String> params2 = new ArrayList<String>();
        params2.add("testuser2");
        params2.add("5");
        params2.add("testname2");

        List<String> params3 = new ArrayList<String>();
        params3.add("testuser3");

        String[] params4 = new String[3];
        params4[0] = "testuser4";
        params4[1] = "3";
        params4[2] = "testname4";

        String[] params5 = new String[3];
        params5[0] = "testuser5";
        params5[1] = "4";
        params5[2] = "testname5";

        String params6 = "testuser6";

        String result1 = UrlAcolyte.addPathParams(testUrl, params1);
        String result2 = UrlAcolyte.addPathParams(testUrl, params2);
        String result3 = UrlAcolyte.addPathParams(testUrl, params3);
        String result4 = UrlAcolyte.addPathParams(testUrl, params4);
        String result5 = UrlAcolyte.addPathParams(testUrl, params5);
        String result6 = UrlAcolyte.addPathParams(testUrl, params6);

        assertEquals("http://www.testsite.com/user/testuser/page/2/name/testname", result1);
        assertEquals("http://www.testsite.com/user/testuser2/page/5/name/testname2", result2);
        assertEquals("http://www.testsite.com/user/testuser3/page/{}/name/{}", result3);
        assertEquals("http://www.testsite.com/user/testuser4/page/3/name/testname4", result4);
        assertEquals("http://www.testsite.com/user/testuser5/page/4/name/testname5", result5);
        assertEquals("http://www.testsite.com/user/testuser6/page/{}/name/{}", result6);
    }

    @Test
    public void testReplacePathParamsMethods(){
        String testUrl = "http://www.testsite.com/user/{user}/page/{page}/name/{name}";
        Map<String, String> params1 = new HashMap<String, String>();
        params1.put("user", "testuser");
        params1.put("page", "2");
        params1.put("name", "testname");

        Map<String, String> params2 = new HashMap<String, String>();
        params2.put("user", "testuser2");
        params2.put("page", "5");
        params2.put("name", "testname2");

        Map<String, String> params3 = new HashMap<String, String>();
        params3.put("user", "testuser3");

        Pair<String, String>[] params4 = new Pair[3];
        params4[0] = Pair.of("user", "testuser4");
        params4[1] = Pair.of("page", "3");
        params4[2] = Pair.of("name", "testname4");

        Pair<String, String>[] params5 = new Pair[3];
        params5[0] = Pair.of("user", "testuser5");
        params5[2] = Pair.of("page", "4");
        params5[1] = Pair.of("name", "testname5");

        Pair<String, String> params6 = Pair.of("user", "testuser6");

        String result1 = UrlAcolyte.replacePathParamsByName(testUrl, params1);
        String result2 = UrlAcolyte.replacePathParamsByName(testUrl, params2);
        String result3 = UrlAcolyte.replacePathParamsByName(testUrl, params3);
        String result4 = UrlAcolyte.replacePathParamsByName(testUrl, params4);
        String result5 = UrlAcolyte.replacePathParamsByName(testUrl, params5);
        String result6 = UrlAcolyte.replacePathParamsByName(testUrl, params6);

        assertEquals("http://www.testsite.com/user/testuser/page/2/name/testname", result1);
        assertEquals("http://www.testsite.com/user/testuser2/page/5/name/testname2", result2);
        assertEquals("http://www.testsite.com/user/testuser3/page/{page}/name/{name}", result3);
        assertEquals("http://www.testsite.com/user/testuser4/page/3/name/testname4", result4);
        assertEquals("http://www.testsite.com/user/testuser5/page/4/name/testname5", result5);
        assertEquals("http://www.testsite.com/user/testuser6/page/{page}/name/{name}", result6);
    }

}
