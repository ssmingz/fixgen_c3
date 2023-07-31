class PlaceHold {
  protected void zipFile(File file, ZipOutputStream zOut, String vPath) throws IOException {
    FileInputStream fIn = new FileInputStream(file);
    try {
      zipFile(fIn, zOut, vPath, file.lastModified());
    } finally {
      fIn.close();
    }
  }
}
