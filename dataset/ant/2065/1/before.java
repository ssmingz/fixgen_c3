class PlaceHold {
  protected void zipFile(File file, ZipOutputStream zOut, String vPath) throws IOException {
    if (file.equals(zipFile)) {
      throw new BuildException("A zip file cannot include itself", location);
    }
    FileInputStream fIn = new FileInputStream(file);
    try {
      zipFile(fIn, zOut, vPath, file.lastModified());
    } finally {
      fIn.close();
    }
  }
}
