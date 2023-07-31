class PlaceHold{
@Test
public void testNoLang() {
    try {
        buildRule.executeTarget("nolang");
        fail("Absence of language attribute not detected");
    } catch (BuildException ex) {
        AntAssert.assertContains("requires a language attribute", ex.getMessage());
    }
}
}