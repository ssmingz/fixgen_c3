class PlaceHold {
  public void testParse() {
    ArrayList aList = new ArrayList(4);
    aList.add("public");
    aList.add("protected");
    aList.add("package");
    aList.add("private");
    ForcedChoiceOption fco = new ForcedChoiceOption("javadoc_access", "protected", aList);
    assertEquals(new String("private"), fco.parse("private"));
    try {
      fco.parse("Private");
      fail();
    } catch (OptionParseException e) {
    }
    try {
      fco.parse("true");
      fail();
    } catch (OptionParseException e) {
    }
    try {
      fco.parse(".33");
      fail();
    } catch (OptionParseException e) {
    }
  }
}
