class PlaceHold {
  public void testClassDeclerationInheritance() {
    String classLine =
        NewJavaClassDialog.createClassNameDecleration("public", "", "abcd", "parentClass", "");
    assertEquals("public class Abcd extends ParentClass", classLine);
  }
}
