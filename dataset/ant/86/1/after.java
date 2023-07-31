class PlaceHold{
@Test
public void testDelay() {
    buildRule.executeTarget("delay");
    assertContains("MyTask called", buildRule.getLog());
}
}