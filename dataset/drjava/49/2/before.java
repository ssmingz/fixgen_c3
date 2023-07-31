class PlaceHold {
  public void testClassDeclerationFinal() {
    String classLine = javaClass.createClassNameDecleration("final", "", "abcd", "", "");
    assertEquals("final class Abcd", classLine);
  }
}
