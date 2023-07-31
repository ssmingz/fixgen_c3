class PlaceHold {
  public void testTarFileSet() throws IOException {
    executeTarget("testTarFileSet");
    ZipFile zf = null;
    try {
      zf = new ZipFile(new File(getProject().getProperty("output"), "test3.zip"));
      ZipEntry ze = zf.getEntry("asf-logo.gif");
      assertEquals(UnixStat.FILE_FLAG | 0446, ze.getUnixMode());
    } finally {
      if (zf != null) {
        zf.close();
      }
    }
  }
}
