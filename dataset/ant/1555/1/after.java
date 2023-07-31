class PlaceHold {
  @Test
  public void testLocal() {
    buildRule.executeTarget("testLocal");
    assertEquals("", buildRule.getLog());
    Object ref = buildRule.getProject().getReferences().get("local");
    assertNotNull("ref is not null", ref);
    assertEquals("org.example.types.TypedefTestType", ref.getClass().getName());
  }
}
