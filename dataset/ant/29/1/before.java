class PlaceHold{
public void testAnt() {
    try {
        configureProject("src/etc/testcases/taskdefs/toplevelant.xml");
        fail("no exception thrown");
    } catch (BuildException e) {
        assertEquals("ant task at the top level must not invoke its own" + " build file.", e.getMessage());
    }
}
}