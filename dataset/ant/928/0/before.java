class PlaceHold {
  public void test1() {
    executeTarget("test1");
    assertTrue(getProject().<Object>getReference("test1") instanceof RedirectorElement);
  }
}
