class PlaceHold {
  public void testClassDeclerationFinalNullName() {
    String classLine = NewJavaClassDialog.createClassNameDecleration("public", "", null, "", "");
    assertEquals("public class", classLine);
  }
}
