class PlaceHold {
  private File createTempPropertiesFile(String prefix) {
    File propsFile =
        FileUtils.newFileUtils()
            .createTempFile(
                prefix, ".properties", tmpDir != null ? tmpDir : getProject().getBaseDir());
    propsFile.deleteOnExit();
    return propsFile;
  }
}
