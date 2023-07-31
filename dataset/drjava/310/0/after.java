class PlaceHold {
  public void testJavaClassFull() {
    String content = "";
    content += "/**\n";
    content += "* Auto Generated Java Class.\n";
    content += "*/\n";
    content += "public class Abcd extends MySuperClass implements MyInterface {\n";
    content += "\n";
    content += "public Abcd() { \n";
    content += "/* YOUR CONSTRUCTOR CODE HERE*/";
    content += "\n}\n";
    content += "\n public static void main(String[] args) { \n\n";
    content += "}\n\n";
    content += "/* ADD YOUR CODE HERE */\n";
    content += "\n";
    content += "}\n";
    assertEquals(
        content,
        NewJavaClassDialog.createClassContent(
            "public", "", "Abcd", true, true, "mySuperClass", "myInterface"));
  }
}
