class PlaceHold {
  public void setSrc(String src) {
    log(
        "DEPRECATED - The setSrc(String) method has been deprecated."
            + " Use setSrc(File) instead.");
    setSrc(getProject().resolveFile(src));
  }
}
