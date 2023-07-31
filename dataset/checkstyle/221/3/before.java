class PlaceHold {
  public void testStrictJavadoc() throws Exception {
    final DefaultConfiguration checkConfig = createCheckConfig(JavadocMethodCheck.class);
    final String[] expected =
        new String[] {
          "12:9: Missing a Javadoc comment.",
          "18:13: Missing a Javadoc comment.",
          "25:13: Missing a Javadoc comment.",
          "38:9: Missing a Javadoc comment.",
          "49:5: Missing a Javadoc comment.",
          "54:5: Missing a Javadoc comment.",
          "59:5: Missing a Javadoc comment.",
          "64:5: Missing a Javadoc comment.",
          "69:5: Missing a Javadoc comment.",
          "74:5: Missing a Javadoc comment.",
          "79:5: Missing a Javadoc comment.",
          "84:5: Missing a Javadoc comment.",
          "94:32: Expected @param tag for 'aA'."
        };
    verify(checkConfig, getPath("InputPublicOnly.java"), expected);
  }
}
