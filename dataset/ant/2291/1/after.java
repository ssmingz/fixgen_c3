class PlaceHold {
  public void setDest(String dest) {
    log(
        "DEPRECATED - The setDest(String) method has been deprecated."
            + " Use setDest(File) instead.");
    setDest(getProject().resolveFile(dest));
  }
}
