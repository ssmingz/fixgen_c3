class PlaceHold {
  @Test
  public void testMemberModifiers() throws Exception {
    checkConfig.addAttribute("validateAbstractClassNames", "true");
    checkConfig.addAttribute(
        "memberModifiers", "LITERAL_PRIVATE, LITERAL_PROTECTED," + " LITERAL_STATIC");
    String[] expected =
        new String[] {
          "6:13: " + getCheckMessage(MSG_KEY, "AbstractClass"),
          "9:13: "
              + getCheckMessage(
                  MSG_KEY,
                  "com.puppycrawl.tools.checkstyle.coding.InputIllegalTypeMemberModifiers.AbstractClass"),
          "16:13: " + getCheckMessage(MSG_KEY, "java.util.TreeSet"),
          "17:13: " + getCheckMessage(MSG_KEY, "TreeSet"),
          "23:15: "
              + getCheckMessage(
                  MSG_KEY,
                  "com.puppycrawl.tools.checkstyle.coding.InputIllegalTypeMemberModifiers.AbstractClass"),
          "25:25: " + getCheckMessage(MSG_KEY, "java.util.TreeSet"),
          "33:15: " + getCheckMessage(MSG_KEY, "AbstractClass")
        };
    verify(
        checkConfig,
        getPath(("coding" + File.separator) + "InputIllegalTypeMemberModifiers.java"),
        expected);
  }
}
