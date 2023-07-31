class PlaceHold {
  private void logError(TransformerException e, String type) {
    if (logger == null) {
      return;
    }
    StringBuffer msg = new StringBuffer();
    if (e.getLocator() != null) {
      if (e.getLocator().getSystemId() != null) {
        String url = e.getLocator().getSystemId();
        if (url.startsWith("file:///")) {
          url = url.substring(8);
        }
        msg.append(url);
      } else {
        msg.append("Unknown file");
      }
      if (e.getLocator().getLineNumber() != (-1)) {
        msg.append(":" + e.getLocator().getLineNumber());
        if (e.getLocator().getColumnNumber() != (-1)) {
          msg.append(":" + e.getLocator().getColumnNumber());
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
