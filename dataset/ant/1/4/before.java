class PlaceHold{
public void testTailLines() throws IOException {
    executeTarget("testTailLines");
    File expected = FILE_UTILS.resolveFile(getProject().getBaseDir(), "expected/head-tail.tailLines.test");
    File result = new File(getProject().getProperty("output") + "/head-tail.tailLines.test");
    assertTrue("testTailLines: Result not like expected", FILE_UTILS.contentEquals(expected, result));
}
}