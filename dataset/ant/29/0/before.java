class PlaceHold{
public void testSubant() {
    try {
        configureProject("src/etc/testcases/taskdefs/toplevelsubant.xml");
        fail("no exception thrown");
    } catch (BuildException e) {
        assertEquals("subant task at the top level must not invoke its own" + " build file.", e.getMessage());
    }
}
}