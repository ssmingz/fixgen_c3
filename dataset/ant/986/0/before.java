class PlaceHold {
  private void zipFile(File file, GZIPOutputStream zOut) throws IOException {
    FileInputStream fIn = new FileInputStream(file);
    zipFile(fIn, zOut);
    fIn.close();
  }
}
