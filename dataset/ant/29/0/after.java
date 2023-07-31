class PlaceHold{
@Test
public void testSubant() {
    try {
        buildRule.configureProject("src/etc/testcases/taskdefs/toplevelsubant.xml");
        fail("no exception thrown");
    } catch (BuildException e) {
        assertEquals("subant task at the top level must not invoke its own" + " build file.", e.getMessage());
    }
}
}