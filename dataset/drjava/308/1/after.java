class PlaceHold {
  private File _fillTempFile(String fname, String text) {
    File f = null;
    try {
      f = File.createTempFile(fname, null).getCanonicalFile();
      FileWriter fw = new FileWriter(f);
      fw.write(text, 0, text.length());
      fw.close();
    } catch (IOException e) {
      throw new RuntimeException("IOException thrown while writing to temp file");
    }
    return f;
  }
}
