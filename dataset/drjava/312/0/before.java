class PlaceHold {
  public void testClassDeclerationInheritanceAndInterfaceNotWellStructured() {
    String classLine =
        javaClass.createClassNameDecleration(
            "public", "abstract", "abcd", "superClass ", " interface1 ,inter , inm");
    assertEquals(
        "public abstract class Abcd extends SuperClass implements Interface1, Inter, Inm",
        classLine);
  }
}
