class PlaceHold {
  public void testDefaultExcludesAndUpdate() throws ZipException, IOException {
    executeTarget("testDefaultExcludesAndUpdate");
    ZipFile f = null;
    try {
      f = new ZipFile(new File(getProject().getProperty("output"), "test3.zip"));
      assertNotNull("ziptest~ should be included", f.getEntry("ziptest~"));
    } finally {
      if (f != null) {
        f.close();
      }
    }
  }
}
