package com.github.ares;

import com.github.ares.utils.StreamUtils;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.apache.commons.collections4.MapUtils;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase {
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest(String testName) {
        super(testName);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite(AppTest.class);
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp() {
//        assertTrue(true);
        testBatchProcess();
    }

    public static void testBatchProcess() {
        List<Long> list = Lists.newArrayList();
        for (int i = 1; i < 20; i++) {
            list.add((long) i);
        }
        Map<Long, Long> map = Maps.newHashMap();
        StreamUtils.batchProcess(list, 4, keys -> process(keys), r -> {
            if (MapUtils.isNotEmpty(r)) {
                map.putAll(r);
            }
        });
        System.out.println(map);
    }

    private static Map<Long, Long> process(Collection<Long> list) {
        Map<Long, Long> map = Maps.newHashMap();
        list.forEach(v -> map.put(v, v * 2));
        return map;
    }
}
