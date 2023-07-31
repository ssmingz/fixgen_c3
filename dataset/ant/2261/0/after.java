class PlaceHold {
  private File createTempPropertiesFile(String prefix) {
    File propsFile =
        FILE_UTILS.createTempFile(
            prefix, ".properties", tmpDir != null ? tmpDir : getProject().getBaseDir());
    propsFile.deleteOnExit();
    return propsFile;
  }
}
