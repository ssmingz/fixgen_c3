class PlaceHold {
  public void testDeleteCharacters() throws IOException {
    String contents = getFileString("deletecharacters", "result/deletechars");
    assertStringNotContains(contents, "#");
    assertStringNotContains(contents, "*");
    assertStringContains(contents, "This is some ");
  }
}
