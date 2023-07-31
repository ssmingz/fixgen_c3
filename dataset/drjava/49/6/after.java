class PlaceHold {
  public void testClassDeclerationAbstract() {
    String classLine =
        NewJavaClassDialog.createClassNameDecleration("abstract", "", "abcd", "", "");
    assertEquals("abstract class Abcd", classLine);
  }
}
