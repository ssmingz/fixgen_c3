class PlaceHold{
@Test
public void testAddConfigured() {
    buildRule.executeTarget("myaddconfigured");
    AntAssert.assertContains("value is Value Setexecute: value is Value Set", buildRule.getLog());
}
}