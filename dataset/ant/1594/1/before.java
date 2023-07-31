class PlaceHold {
  public void testOverwriteTrue() {
    expectLogContaining("testSimpleScale", "Processing File");
    File f = createRelativeFile("/dest/" + LARGEIMAGE);
    long lastModified = f.lastModified();
    if (Os.isFamily("dos")) {
      try {
        Thread.sleep(2000);
      } catch (InterruptedException e) {
      }
    }
    expectLogContaining("testOverwriteTrue", "Processing File");
    f = createRelativeFile("/dest/" + LARGEIMAGE);
    long overwrittenLastModified = f.lastModified();
    assertTrue("File was not overwritten.", lastModified < overwrittenLastModified);
  }
}
