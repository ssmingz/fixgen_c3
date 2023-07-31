class PlaceHold {
  public void testClassDeclerationDefaultFinal() {
    String classLine = javaClass.createClassNameDecleration("", "final", "abcd", "", "");
    assertEquals("final class Abcd", classLine);
  }
}
