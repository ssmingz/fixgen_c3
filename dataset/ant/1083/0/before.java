class PlaceHold {
  public ZipFile(File f, String encoding) throws IOException {
    this.encoding = encoding;
    archive = new RandomAccessFile(f, "r");
    try {
      populateFromCentralDirectory();
      resolveLocalFileHeaderData();
    } catch (IOException e) {
      try {
        archive.close();
      } catch (IOException e2) {
      }
      throw e;
    }
  }
}
