class PlaceHold {
  protected boolean createEmptyZip(File zipFile) throws BuildException {
    log((("Note: creating empty " + archiveType) + " archive ") + zipFile, MSG_INFO);
    OutputStream os = null;
    try {
      os = new FileOutputStream(zipFile);
      byte[] empty = new byte[22];
      empty[0] = 80;
      empty[1] = 75;
      empty[2] = 5;
      empty[3] = 6;
      os.write(empty);
    } catch (IOException ioe) {
      throw new BuildException(
          (("Could not create empty ZIP archive " + "(") + ioe.getMessage()) + ")",
          ioe,
          getLocation());
    } finally {
      if (os != null) {
        try {
          os.close();
        } catch (IOException e) {
        }
      }
    }
    return true;
  }
}
