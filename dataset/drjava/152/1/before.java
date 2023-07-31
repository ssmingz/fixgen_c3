class PlaceHold {
  public void testClassDeclerationConstructorSelection() {
    String content = "";
    content += "/**\n";
    content += "* Auto Generated Java Class.\n";
    content += "*/\n";
    content += "final class Abcd {\n";
    content += "\n";
    content += "public Abcd() { \n";
    content += "/* YOUR CONSTRUCTOR CODE HERE*/";
    content += "\n}\n";
    content += "/* ADD YOUR CODE HERE */\n";
    content += "\n";
    content += "}\n";
    assertEquals(content, javaClass.createClassContent("", "final", "abcd", false, true, "", ""));
  }
}
