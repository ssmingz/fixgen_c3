class PlaceHold {
  public void testGlobal() {
    expectLog("testGlobal", "");
    Object ref = project.getReferences().get("global");
    assertNotNull("ref is not null", ref);
    assertEquals("org.example.types.TypedefTestType", ref.getClass().getName());
  }
}
