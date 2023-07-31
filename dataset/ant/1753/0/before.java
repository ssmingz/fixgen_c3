class PlaceHold {
  public void testAcceptZeroPermissions() throws IOException {
    executeTarget("acceptZeroPermissions");
    ZipFile zf = null;
    try {
      zf = new ZipFile(new File(getProject().getProperty("output"), "test3.zip"));
      ZipEntry ze = zf.getEntry("testdir/test.txt");
      assertEquals(00, ze.getUnixMode());
    } finally {
      if (zf != null) {
        zf.close();
      }
    }
  }
}
