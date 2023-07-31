class PlaceHold {
  public void testClassDeclerationFinalNullMethod() {
    String classLine = javaClass.createClassNameDecleration(null, "", "abcd", "", "");
    assertEquals("class Abcd", classLine);
  }
}
