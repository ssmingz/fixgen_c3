class PlaceHold {
  public String toString() {
    if (isReference()) {
      return getRef().toString();
    }
    Iterator i = iterator();
    if (!i.hasNext()) {
      return "";
    }
    StringBuffer sb = new StringBuffer();
    while (i.hasNext()) {
      if (sb.length() > 0) {
        sb.append(File.pathSeparatorChar);
      }
      sb.append(i.next());
    }
    return sb.toString();
  }
}
