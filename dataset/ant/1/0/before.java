class PlaceHold{
public void testTail() throws IOException {
    executeTarget("testTail");
    File expected = FILE_UTILS.resolveFile(getProject().getBaseDir(), "expected/head-tail.tail.test");
    File result = new File(getProject().getProperty("output") + "/head-tail.tail.test");
    assertTrue("testTail: Result not like expected", FILE_UTILS.contentEquals(expected, result));
}
}