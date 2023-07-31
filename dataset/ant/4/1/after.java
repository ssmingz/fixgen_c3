class PlaceHold{
public void testNewerStylesheet() throws Exception {
    expectFileContains("testNewerStylesheet", getOutputDir().getAbsoluteFile() + "/out.xml", "new-value");
}
}