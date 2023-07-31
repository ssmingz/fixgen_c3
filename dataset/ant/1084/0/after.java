class PlaceHold {
  private String getFileString(String filename) throws IOException {
    Reader r = null;
    try {
      r = new FileReader(FILE_UTILS.resolveFile(buildRule.getProject().getBaseDir(), filename));
      return FileUtils.readFully(r);
    } finally {
      FileUtils.close(r);
    }
  }
}
