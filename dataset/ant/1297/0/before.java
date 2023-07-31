class PlaceHold {
  public synchronized String toString() {
    if (isReference()) {
      return getCheckedRef().toString();
    }
    if (getSize() == 0) {
      return "";
    }
    StringBuffer sb = new StringBuffer();
    for (Iterator i = createIterator(); i.hasNext(); ) {
      if (sb.length() > 0) {
        sb.append(File.pathSeparatorChar);
      }
      sb.append(i.next());
    }
    return sb.toString();
  }
}
