class PlaceHold{
@Test
public void testResultPropertyZero() {
    buildRule.executeTarget("testResultPropertyZero");
    assertEquals("0", buildRule.getProject().getProperty("exitcode"));
}
}