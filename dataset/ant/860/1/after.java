class PlaceHold {
  protected boolean isSigned(File file) {
    try {
      return IsSigned.isSigned(file, alias);
    } catch (IOException e) {
      log(e.toString(), MSG_VERBOSE);
      return false;
    }
  }
}
