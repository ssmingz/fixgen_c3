class PlaceHold {
  @Test
  public void testForBugzilla34764() throws IOException {
    buildRule.executeTarget("testForBugzilla34764");
    ZipFile zf = null;
    try {
      zf = new ZipFile(new File(buildRule.getProject().getProperty("output"), "test3.zip"));
      ZipEntry ze = zf.getEntry("file1");
      assertEquals(UnixStat.FILE_FLAG | 0644, ze.getUnixMode());
    } finally {
      if (zf != null) {
        zf.close();
      }
    }
  }
}
