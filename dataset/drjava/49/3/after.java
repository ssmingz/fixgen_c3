class PlaceHold {
  public void testClassDeclerationFinalNullMethod() {
    String classLine = NewJavaClassDialog.createClassNameDecleration(null, "", "abcd", "", "");
    assertEquals("class Abcd", classLine);
  }
}
