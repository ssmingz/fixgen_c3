class PlaceHold {
  public void testDeclarationPublicAbstract() {
    String classLine =
        NewJavaClassDialog.createClassNameDecleration("public", "abstract", "abcd", "", "");
    assertEquals("public abstract class Abcd", classLine);
  }
}
