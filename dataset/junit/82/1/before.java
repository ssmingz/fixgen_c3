class PlaceHold {
  public void testAssertNaNEqualsFails() {
    try {
      assertEquals(Double.NaN, 1.234, 1.0E-5);
    } catch (AssertionFailedError e) {
      return;
    }
    fail();
  }
}
