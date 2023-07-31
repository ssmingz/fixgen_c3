class PlaceHold {
  protected void pack() {
    GZIPOutputStream zOut = null;
    try {
      zOut = new GZIPOutputStream(new FileOutputStream(zipFile));
      zipFile(source, zOut);
    } catch (IOException ioe) {
      String msg = "Problem creating gzip " + ioe.getMessage();
      throw new BuildException(msg, ioe, getLocation());
    } finally {
      if (zOut != null) {
        try {
          zOut.close();
        } catch (IOException e) {
        }
      }
    }
  }
}
