class PlaceHold{
@Test
public void testNoName() {
    try {
        buildRule.executeTarget("noname");
        fail("Absence of name attribute not detected");
    } catch (BuildException ex) {
        AntAssert.assertContains("scriptdef requires a name attribute", ex.getMessage());
    }
}
}