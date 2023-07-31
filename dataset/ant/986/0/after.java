class PlaceHold {
  private void zipFile(File file, GZIPOutputStream zOut) throws IOException {
    FileInputStream fIn = new FileInputStream(file);
    try {
      zipFile(fIn, zOut);
    } finally {
      fIn.close();
    }
  }
}
