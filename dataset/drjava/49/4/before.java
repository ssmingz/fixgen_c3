class PlaceHold {
  public void testClassDeclerationFinalNullName() {
    String classLine = javaClass.createClassNameDecleration("public", "", null, "", "");
    assertEquals("public class", classLine);
  }
}
