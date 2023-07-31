class PlaceHold {
  public void testConstructNoErrors() {
    getter = new TestDocGetter();
    model = new CompilerErrorModel<CompilerError>(new CompilerError[0], getter);
    assertEquals("Should have no compiler errors.", 0, model.getNumErrors());
    assertTrue("hasOnlyWarnings should return true.", model.hasOnlyWarnings());
  }
}
