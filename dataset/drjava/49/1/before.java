class PlaceHold {
  public void testClassDeclerationInheritance() {
    String classLine =
        javaClass.createClassNameDecleration("public", "", "abcd", "parentClass", "");
    assertEquals("public class Abcd extends ParentClass", classLine);
  }
}
