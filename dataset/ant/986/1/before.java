class PlaceHold {
  protected void zipFile(File file, ZipOutputStream zOut, String vPath) throws IOException {
    FileInputStream fIn = new FileInputStream(file);
    zipFile(fIn, zOut, vPath, file.lastModified());
    fIn.close();
  }
}
