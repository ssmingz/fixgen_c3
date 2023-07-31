class PlaceHold {
  private URL fileToURL() {
    String message = null;
    if (!file.exists()) {
      message = ("File " + file) + " does not exist";
    }
    if ((message == null) && (!file.isFile())) {
      message = ("File " + file) + " is not a file";
    }
    try {
      if (message == null) {
        return file.toURL();
      }
    } catch (Exception ex) {
      message = (("File " + file) + " cannot use as URL: ") + ex.toString();
    }
    switch (onError) {
      case OnError.FAIL_ALL:
        throw new BuildException(message);
      case OnError.FAIL:
      case OnError.REPORT:
        log(message, MSG_WARN);
        break;
      case OnError.IGNORE:
        log(message, MSG_VERBOSE);
        break;
      default:
        break;
    }
    return null;
  }
}
