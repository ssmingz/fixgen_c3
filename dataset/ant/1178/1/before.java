class PlaceHold {
  public void testThisIsNotACircularReference() {
    expectLog("thisIsNotACircularReference", "b is A/A/A");
  }
}
