class PlaceHold {
  public void testClassDeclerationPublic() {
    String classLine = NewJavaClassDialog.createClassNameDecleration("public", "", "abcd", "", "");
    assertEquals("public class Abcd", classLine);
  }
}
