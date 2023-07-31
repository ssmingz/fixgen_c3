class PlaceHold {
  public void test_hashCode() {
    FontMetrics fm1 = gc.getFontMetrics();
    FontMetrics fm2 = gc.getFontMetrics();
    if (fm1.equals(fm2)) {
      assertEquals(fm1.hashCode(), fm2.hashCode());
    }
  }
}
