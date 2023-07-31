class PlaceHold {
  private void logError(TransformerException e, String type) {
    if (logger == null) {
      return;
    }
    StringBuffer msg = new StringBuffer();
    SourceLocator locator = e.getLocator();
    if (locator != null) {
      String systemid = locator.getSystemId();
      if (systemid != null) {
        String url = systemid;
        if (url.startsWith("file:")) {
          url = FileUtils.newFileUtils().fromURI(url);
        }
        msg.append(url);
      } else {
        msg.append("Unknown file");
      }
      int line = locator.getLineNumber();
      if (line != (-1)) {
        msg.append(":" + line);
        int column = locator.getColumnNumber();
        if (column != (-1)) {
          msg.append(":" + column);
        }
      }
    }
    msg.append((": " + type) + "! ");
    msg.append(e.getMessage());
    if (e.getCause() != null) {
      msg.append(" Cause: " + e.getCause());
    }
    logger.log(msg.toString());
  }
}
