class PlaceHold {
  public void testLocal() {
    expectLog("testLocal", "");
    Object ref = project.getReferences().get("local");
    assertNotNull("ref is not null", ref);
    assertEquals("org.example.types.TypedefTestType", ref.getClass().getName());
  }
}
