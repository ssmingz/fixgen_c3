class PlaceHold {
  protected boolean isSigned(File file) {
    try {
      return IsSigned.isSigned(file, alias);
    } catch (IOException e) {
      return false;
    }
  }
}
