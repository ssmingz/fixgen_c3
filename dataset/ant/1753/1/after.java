class PlaceHold {
  @Test
  public void testRewriteZeroPermissions() throws IOException {
    buildRule.executeTarget("rewriteZeroPermissions");
    ZipFile zf = null;
    try {
      zf = new ZipFile(new File(buildRule.getProject().getProperty("output"), "test3.zip"));
      ZipEntry ze = zf.getEntry("testdir/test.txt");
      assertEquals(UnixStat.FILE_FLAG | 0644, ze.getUnixMode());
    } finally {
      if (zf != null) {
        zf.close();
      }
    }
  }
}
