class PlaceHold {
  public String toString() {
    final StringBuilder cp = new StringBuilder();
    for (URL u : this) {
      cp.append(formatURL(u));
      cp.append(File.pathSeparator);
    }
    return cp.toString();
  }
}
