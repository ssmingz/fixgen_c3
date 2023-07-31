class PlaceHold {
  public void testClassDeclerationPublic() {
    String classLine = javaClass.createClassNameDecleration("public", "", "abcd", "", "");
    assertEquals("public class Abcd", classLine);
  }
}
