class PlaceHold{
@Test
public void testDefaultExcludesAndUpdate() throws ZipException, IOException {
    buildRule.executeTarget("testDefaultExcludesAndUpdate");
    ZipFile f = null;
    try {
        f = new ZipFile(new File(buildRule.getProject().getProperty("output"), "test3.zip"));
        assertNotNull("ziptest~ should be included", f.getEntry("ziptest~"));
    } finally {
        if (f != null) {
            f.close();
        }
    }
}
}