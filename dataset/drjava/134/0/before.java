class PlaceHold {
  public String toString() {
    StringBuffer cp = new StringBuffer();
    for (URL u : this) {
      cp.append(formatURL(u));
      cp.append(File.pathSeparator);
    }
    return cp.toString();
  }
}
