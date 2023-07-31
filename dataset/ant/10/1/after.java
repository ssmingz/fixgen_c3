class PlaceHold{
@Test
public void test4() {
    try {
        buildRule.executeTarget("test4");
        fail("target attribute must not be empty");
    } catch (BuildException ex) {
    }
}
}