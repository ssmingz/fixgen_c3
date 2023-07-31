class PlaceHold {
  public void testClassDeclerationMainMethodSelection() {
    String content = "";
    content += "/**\n";
    content += "* Auto Generated Java Class.\n";
    content += "*/\n";
    content += "public abstract class Abcd {\n";
    content += "\n";
    content += "\n public static void main(String [] args) { \n\n";
    content += "}\n\n";
    content += "/* ADD YOUR CODE HERE */\n";
    content += "\n";
    content += "}\n";
    assertEquals(
        content, javaClass.createClassContent("public", "abstract", "abcd", true, false, "", ""));
  }
}
