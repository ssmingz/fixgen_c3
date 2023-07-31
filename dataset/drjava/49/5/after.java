class PlaceHold {
  public void testClassDeclerationDefaultFinal() {
    String classLine = NewJavaClassDialog.createClassNameDecleration("", "final", "abcd", "", "");
    assertEquals("final class Abcd", classLine);
  }
}
