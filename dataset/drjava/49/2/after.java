class PlaceHold {
  public void testClassDeclerationFinal() {
    String classLine = NewJavaClassDialog.createClassNameDecleration("final", "", "abcd", "", "");
    assertEquals("final class Abcd", classLine);
  }
}
