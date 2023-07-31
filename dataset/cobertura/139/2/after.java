class PlaceHold {
  public void testEquals() {
    assertFalse(a.equals(null));
    assertFalse(a.equals(Integer.valueOf(4)));
    assertTrue(a.equals(a));
    assertFalse(a.equals(b));
    SwitchData aPrime = new SwitchData(0, new int[] {0, 1, 2, 3}, Integer.MAX_VALUE);
    assertTrue(a.equals(aPrime));
  }
}
