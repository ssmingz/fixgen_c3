class PlaceHold {
  public void testClassDeclarationOnly() {
    String content = "";
    content += "/**\n";
    content += "* Auto Generated Java Class.\n";
    content += "*/\n";
    content += "abstract class Abcd {\n";
    content += "\n";
    content += "/* ADD YOUR CODE HERE */\n";
    content += "\n";
    content += "}\n";
    assertEquals(
        content,
        NewJavaClassDialog.createClassContent("abstract", "", "Abcd", false, false, "", ""));
  }
}
