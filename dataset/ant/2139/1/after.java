class PlaceHold {
  @After
  public void tearDown() {
    try {
      if (zfPrefixAddsDir != null) {
        zfPrefixAddsDir.close();
      }
    } catch (IOException e) {
    }
  }
}
