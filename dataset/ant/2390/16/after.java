class PlaceHold {
  public boolean doGet(int logLevel, DownloadProgress progress) throws IOException {
    if (source == null) {
      throw new BuildException("src attribute is required", getLocation());
    }
    if (dest == null) {
      throw new BuildException("dest attribute is required", getLocation());
    }
    if (dest.exists() && dest.isDirectory()) {
      throw new BuildException("The specified destination is a directory", getLocation());
    }
    if (dest.exists() && (!dest.canWrite())) {
      throw new BuildException("Can't write to " + dest.getAbsolutePath(), getLocation());
    }
    if (progress == null) {
      progress = new NullProgress();
    }
    log("Getting: " + source, logLevel);
    log("To: " + dest.getAbsolutePath(), logLevel);
    long timestamp = 0;
    boolean hasTimestamp = false;
    if (useTimestamp && dest.exists()) {
      timestamp = dest.lastModified();
      if (verbose) {
        Date t = new Date(timestamp);
        log("local file date : " + t.toString(), logLevel);
      }
      hasTimestamp = true;
    }
    URLConnection connection = source.openConnection();
    if (useTimestamp && hasTimestamp) {
      connection.setIfModifiedSince(timestamp);
    }
    if ((uname != null) || (pword != null)) {
      String up = (uname + ":") + pword;
      String encoding;
      Base64Converter encoder = new Base64Converter();
      encoding = encoder.encode(up.getBytes());
      connection.setRequestProperty("Authorization", "Basic " + encoding);
    }
    connection.connect();
    if (connection instanceof HttpURLConnection) {
      HttpURLConnection httpConnection = ((HttpURLConnection) (connection));
      if (httpConnection.getResponseCode() == HttpURLConnection.HTTP_NOT_MODIFIED) {
        log("Not modified - so not downloaded", logLevel);
        return false;
      }
      if (httpConnection.getResponseCode() == HttpURLConnection.HTTP_UNAUTHORIZED) {
        String message = "HTTP Authorization failure";
        if (ignoreErrors) {
          log(message, logLevel);
          return false;
        } else {
          throw new BuildException(message);
        }
      }
    }
    InputStream is = null;
    for (int i = 0; i < 3; i++) {
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
    FileOutputStream fos = new FileOutputStream(dest);
    progress.beginDownload();
    boolean finished = false;
    try {
      byte[] buffer = new byte[100 * 1024];
      int length;
      while ((length = is.read(buffer)) >= 0) {
        fos.write(buffer, 0, length);
        progress.onTick();
      }
      finished = true;
    } finally {
      FileUtils.close(fos);
      FileUtils.close(is);
      if (!finished) {
        dest.delete();
      }
    }
    progress.endDownload();
    if (useTimestamp) {
      long remoteTimestamp = connection.getLastModified();
      if (verbose) {
        Date t = new Date(remoteTimestamp);
        log(
            ("last modified = " + t.toString())
                + (remoteTimestamp == 0 ? " - using current time instead" : ""),
            logLevel);
      }
      if (remoteTimestamp != 0) {
        FILE_UTILS.setFileLastModified(dest, remoteTimestamp);
      }
    }
    return true;
  }
}
