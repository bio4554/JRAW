package net.dean.jraw.test;

import com.squareup.okhttp.MediaType;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static net.dean.jraw.JrawUtils.*;
import static org.testng.Assert.*;

/** Tests methods found in {@link net.dean.jraw.JrawUtils} */
public class UtilsTest extends RedditTest {
    @Test
    public void testMapOf() {
        Map<String, String> expected = new HashMap<>();
        expected.put("hello", "world");
        expected.put("key", "value");

        Map<String, String> generated = mapOf(
                "hello", "world",
                "key", "value"
        );

        assertEquals(expected, generated, "Expected and generated maps were not the same");
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void testArgsWithNullObject() {
        mapOf("hello", null);
    }

    @Test
    public void testFullName() {
        assertTrue(isFullName("t2_f25asl"));
        assertFalse(isFullName("t9_s9al4"));
        assertFalse(isFullName("t0_ula8k"));
        assertFalse(isFullName("jfdklsa"));
        assertFalse(isFullName("t7_fd01ll"));
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testFullNameEmpty() {
        isFullName("");
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testOddArgLength() {
        mapOf("only one element");
    }

    @Test
    public void testIsEqualBasic() {
        String type = "application/json";
        assertEqualMediaType(type, type);
    }

    @Test
    public void testIsEqualAnyType() {
        assertEqualMediaType("*/json", "application/json");
    }

    @Test
    public void testIsEqualAnySubtype() {
        assertEqualMediaType("application/*", "application/json");
    }

    @Test
    public void testIsEqualAny() {
        assertEqualMediaType("*/*", "application/json");
    }

    private void assertEqualMediaType(String t1, String t2) {
        assertTrue(isEqual(MediaType.parse(t1), MediaType.parse(t2)), t1 + " was not equal to " + t2);
    }

    @Test
    public void testJoin() {
        char sep = ',';
        assertEquals(join(), "");
        assertEquals(join(sep, "one"), "one");
        assertEquals(join(sep, "one", "two", "three"), "one,two,three");
    }
}
