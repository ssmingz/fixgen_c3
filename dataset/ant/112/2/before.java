class PlaceHold {
  private String getFileString(String filename) throws IOException {
    Reader r = null;
    try {
      r = new FileReader(getProject().resolveFile(filename));
      return FileUtils.readFully(r);
    } finally {
      FileUtils.close(r);
    }
  }
}
