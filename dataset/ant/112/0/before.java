class PlaceHold {
  protected String read(String filename) {
    String content = null;
    try {
      File file = getProject().resolveFile(filename);
      FileReader rdr = new FileReader(file);
      content = FileUtils.readFully(rdr);
      rdr.close();
      rdr = null;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return content;
  }
}
