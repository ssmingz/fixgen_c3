class PlaceHold {
  @Test
  public void testIgnoreMethodNames() throws Exception {
    checkConfig.addAttribute("ignoredMethodNames", "table2");
    String[] expected =
        new String[] {
          "6:13: " + getCheckMessage(MSG_KEY, "AbstractClass"),
          "9:13: "
              + getCheckMessage(
                  MSG_KEY, "com.puppycrawl.tools.checkstyle.coding.InputIllegalType.AbstractClass"),
          "16:13: " + getCheckMessage(MSG_KEY, "java.util.TreeSet")
        };
    verify(checkConfig, getPath(("coding" + File.separator) + "InputIllegalType.java"), expected);
  }
}
