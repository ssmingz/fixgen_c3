class PlaceHold{
@Test
public void testTailLinesSkip() throws IOException {
    buildRule.executeTarget("testTailLinesSkip");
    File expected = buildRule.getProject().resolveFile("expected/head-tail.tailLinesSkip.test");
    File result = new File(buildRule.getProject().getProperty("output") + "/head-tail.tailLinesSkip.test");
    assertEquals("testTailLinesSkip: Result not like expected", FileUtilities.getFileContents(expected), FileUtilities.getFileContents(result));
}
}