class PlaceHold {
  public void testDeleteCharacters() throws IOException {
    String contents =
        getFileString("deletecharacters", getProject().getProperty("output") + "/deletechars");
    assertStringNotContains(contents, "#");
    assertStringNotContains(contents, "*");
    assertStringContains(contents, "This is some ");
  }
}
