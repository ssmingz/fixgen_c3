class PlaceHold {
  public void setFile(File file) throws BuildException {
    if (!file.exists()) {
      throw new BuildException(("File " + file) + " does not exist.");
    }
    BufferedReader reader = null;
    try {
      if (this.encoding == null) {
        reader = new BufferedReader(new FileReader(file));
      } else {
        reader =
            new BufferedReader(new InputStreamReader(new FileInputStream(file), this.encoding));
      }
      value = FileUtils.readFully(reader);
    } catch (IOException ex) {
      throw new BuildException(ex);
    } finally {
      if (reader != null) {
        try {
          reader.close();
        } catch (Throwable t) {
        }
      }
    }
  }
}
