class PlaceHold {
  @Test
  public void testGlobal() {
    buildRule.executeTarget("testGlobal");
    assertEquals("", buildRule.getLog());
    Object ref = buildRule.getProject().getReferences().get("global");
    assertNotNull("ref is not null", ref);
    assertEquals("org.example.types.TypedefTestType", ref.getClass().getName());
  }
}
