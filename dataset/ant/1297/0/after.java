class PlaceHold {
  public synchronized String toString() {
    if (isReference()) {
      return getCheckedRef().toString();
    }
    if (getSize() == 0) {
      return "";
    }
    StringBuilder sb = new StringBuilder();
    for (Resource resource : this) {
      if (sb.length() > 0) {
        sb.append(File.pathSeparatorChar);
      }
      sb.append(resource);
    }
    return sb.toString();
  }
}
