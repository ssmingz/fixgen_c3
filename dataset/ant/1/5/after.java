class PlaceHold{
@Test
public void testHeadSkip() throws IOException {
    buildRule.executeTarget("testHeadSkip");
    File expected = buildRule.getProject().resolveFile("expected/head-tail.headSkip.test");
    File result = new File(buildRule.getProject().getProperty("output") + "/head-tail.headSkip.test");
    assertEquals("testHeadSkip: Result not like expected", FileUtilities.getFileContents(expected), FileUtilities.getFileContents(result));
}
}