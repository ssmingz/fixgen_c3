class PlaceHold{
@Test
public void testPrefixAddsDir() throws IOException {
    buildRule.executeTarget("testPrefixAddsDir");
    File archive = new File(buildRule.getProject().getProperty("output"), "test3.zip");
    zfPrefixAddsDir = new ZipFile(archive);
    ZipEntry ze = zfPrefixAddsDir.getEntry("test/");
    assertNotNull("test/ has been added", ze);
}
}