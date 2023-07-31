class PlaceHold {
  @Test
  public void testLegalAbstractClassNames() throws Exception {
    checkConfig.addAttribute("legalAbstractClassNames", "AbstractClass");
    String[] expected =
        new String[] {
          "9:13: "
              + getCheckMessage(
                  MSG_KEY, "com.puppycrawl.tools.checkstyle.coding.InputIllegalType.AbstractClass"),
          "16:13: " + getCheckMessage(MSG_KEY, "java.util.TreeSet"),
          "17:13: " + getCheckMessage(MSG_KEY, "TreeSet")
        };
    verify(checkConfig, getPath(("coding" + File.separator) + "InputIllegalType.java"), expected);
  }
}
