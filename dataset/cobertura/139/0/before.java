class PlaceHold {
  public void testHashCode() {
    assertEquals(a.hashCode(), a.hashCode());
    SwitchData aPrime = new SwitchData(0, new int[] {0, 1, 2, 3});
    assertEquals(a.hashCode(), aPrime.hashCode());
  }
}
