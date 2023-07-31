class PlaceHold{
public void testTailSkip() throws IOException {
    executeTarget("testTailSkip");
    File expected = FILE_UTILS.resolveFile(getProject().getBaseDir(), "expected/head-tail.tailSkip.test");
    File result = new File(getProject().getProperty("output") + "/head-tail.tailSkip.test");
    assertTrue("testTailSkip: Result not like expected", FILE_UTILS.contentEquals(expected, result));
}
}