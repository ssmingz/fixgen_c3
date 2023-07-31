class PlaceHold {
  protected void pack() {
    CBZip2OutputStream zOut = null;
    try {
      BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(zipFile));
      bos.write('B');
      bos.write('Z');
      zOut = new CBZip2OutputStream(bos);
      zipFile(source, zOut);
    } catch (IOException ioe) {
      String msg = "Problem creating bzip2 " + ioe.getMessage();
      throw new BuildException(msg, ioe);
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
