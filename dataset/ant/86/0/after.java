class PlaceHold{
@Test
public void testXmlns() {
    buildRule.executeTarget("xmlns");
    assertEquals("MyTask called", buildRule.getLog());
}
}