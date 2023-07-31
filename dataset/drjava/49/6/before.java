class PlaceHold {
  public void testClassDeclerationAbstract() {
    String classLine = javaClass.createClassNameDecleration("abstract", "", "abcd", "", "");
    assertEquals("abstract class Abcd", classLine);
  }
}
