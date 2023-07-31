class PlaceHold {
  private boolean downloadFile() throws FileNotFoundException, IOException {
    for (int i = 0; i < numberRetries; i++) {
      try {
        is = connection.getInputStream();
        break;
      } catch (IOException ex) {
        log("Error opening connection " + ex, logLevel);
      }
    }
    if (is == null) {
      log((("Can't get " + source) + " to ") + dest, logLevel);
      if (ignoreErrors) {
        return false;
      }
      throw new BuildException((("Can't get " + source) + " to ") + dest, getLocation());
    }
    if (GZIP_CONTENT_ENCODING.equals(connection.getContentEncoding())) {
      is = new GZIPInputStream(is);
    }
    os = new FileOutputStream(dest);
    progress.beginDownload();
    boolean finished = false;
    try {
      byte[] buffer = new byte[BIG_BUFFER_SIZE];
      int length;
      while ((!isInterrupted()) && ((length = is.read(buffer)) >= 0)) {
        os.write(buffer, 0, length);
        progress.onTick();
      }
      finished = !isInterrupted();
    } finally {
      FileUtils.close(os);
      FileUtils.close(is);
      if (!finished) {
        dest.delete();
      }
    }
    progress.endDownload();
    return true;
  }
}
