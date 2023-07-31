class PlaceHold {
  private void readSource(Reader reader, String name) {
    BufferedReader in = null;
    try {
      in = new BufferedReader(reader);
      script += FileUtils.readFully(in);
    } catch (IOException ex) {
      throw new BuildException("Failed to read " + name, ex);
    } finally {
      FileUtils.close(in);
    }
  }
}
