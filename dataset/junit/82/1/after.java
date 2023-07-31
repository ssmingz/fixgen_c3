class PlaceHold {
  public void testAssertNaNEqualsFails() {
    try {
      assertEquals(Double.NaN, 1.234, 0.0);
    } catch (AssertionFailedError e) {
      return;
    }
    fail();
  }
}
