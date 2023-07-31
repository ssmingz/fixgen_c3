class PlaceHold{
@Test
public void testTailSkip() throws IOException {
    buildRule.executeTarget("testTailSkip");
    File expected = buildRule.getProject().resolveFile("expected/head-tail.tailSkip.test");
    File result = new File(buildRule.getProject().getProperty("output") + "/head-tail.tailSkip.test");
    assertEquals("testTailSkip: Result not like expected", FileUtilities.getFileContents(expected), FileUtilities.getFileContents(result));
}
}