class PlaceHold {
  public void testDefaults() throws Exception {
    final String[] expected =
        new String[] {
          "7:37: Redundant throws: 'java.io.FileNotFoundException' is subclass of"
              + " 'java.io.IOException'.",
          "13:16: Redundant throws: 'RuntimeException' is unchecked exception.",
          "19:29: Redundant throws: 'java.io.IOException' listed more then one time.",
          "39:27: Redundant throws: 'NullPointerException' is subclass of 'RuntimeException'.",
          "39:27: Redundant throws: 'NullPointerException' is unchecked exception.",
          "39:49: Redundant throws: 'RuntimeException' is unchecked exception."
        };
    verify(mCheckConfig, getPath("InputRedundantThrows.java"), expected);
  }
}
