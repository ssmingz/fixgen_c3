class PlaceHold {
  @Test
  public void testImproperFileExtension() throws Exception {
    final DefaultConfiguration checkConfig = createCheckConfig(ConstantNameCheck.class);
    final File file = temporaryFolder.newFile("file.pdf");
    final String content = "public class Main { public static final int k = 5 + 4; }";
    final BufferedWriter writer =
        new BufferedWriter(
            new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8));
    writer.write(content);
    writer.close();
    final String[] expected = ArrayUtils.EMPTY_STRING_ARRAY;
    verify(checkConfig, file.getPath(), expected);
  }
}
