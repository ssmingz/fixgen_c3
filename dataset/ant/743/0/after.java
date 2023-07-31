class PlaceHold {
  @Test
  public void testWrongElement() throws Exception {
    buildRule.executeTarget("testWrongElement");
    assertIndexCreated();
    assertContains(
        "Required text not found in log", WARNING_INVALID_ROOT_ELEMENT, buildRule.getLog());
  }
}
