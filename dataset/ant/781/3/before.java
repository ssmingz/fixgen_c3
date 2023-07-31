class PlaceHold {
  public void tearDown() {
    if (r1 != null) {
      try {
        r1.close();
      } catch (IOException e) {
      }
    }
    if (r2 != null) {
      try {
        r2.close();
      } catch (IOException e) {
      }
    }
    executeTarget("cleanup");
  }
}
