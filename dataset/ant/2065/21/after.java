class PlaceHold {
  protected boolean createEmptyZip(File zipFile) {
    log((("Note: creating empty " + archiveType) + " archive ") + zipFile, MSG_INFO);
    try {
      OutputStream os = new FileOutputStream(zipFile);
      try {
        byte[] empty = new byte[22];
        empty[0] = 80;
        empty[1] = 75;
        empty[2] = 5;
        empty[3] = 6;
        os.write(empty);
      } finally {
        os.close();
      }
    } catch (IOException ioe) {
      throw new BuildException("Could not create empty ZIP archive", ioe);
    }
    return true;
  }
}
