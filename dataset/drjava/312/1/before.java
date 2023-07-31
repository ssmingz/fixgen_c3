class PlaceHold {
  public void testDeclarationPublicAbstract() {
    String classLine = javaClass.createClassNameDecleration("public", "abstract", "abcd", "", "");
    assertEquals("public abstract class Abcd", classLine);
  }
}
