class PlaceHold {
  public ZipFile(File f, String encoding) throws IOException {
    this.encoding = encoding;
    archive = new RandomAccessFile(f, "r");
    boolean success = false;
    try {
      populateFromCentralDirectory();
      resolveLocalFileHeaderData();
      success = true;
    } finally {
      if (!success) {
        try {
          archive.close();
        } catch (IOException e2) {
        }
      }
    }
  }
}
