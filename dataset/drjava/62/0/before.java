class PlaceHold {
  public void testConstructDoclessErrors() {
    getter = new TestDocGetter();
    errors =
        new CompilerError[] {
          new CompilerError("Test error without File", false),
          new CompilerError("Test warning without File", true),
          new CompilerError("Test error without File", false)
        };
    CompilerError[] copy = new CompilerError[errors.length];
    for (int i = 0; i < errors.length; i++) {
      copy[i] = errors[i];
    }
    model = new CompilerErrorModel<CompilerError>(copy, getter);
    assertEquals("Should have 3 compiler errors.", 3, model.getNumErrors());
    assertEquals("Errors should be sorted.", errors[1], model.getError(2));
  }
}
