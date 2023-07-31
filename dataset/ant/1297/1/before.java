class PlaceHold {
  public synchronized String toString() {
    if (isReference()) {
      return getCheckedRef().toString();
    }
    if (cacheCollection().size() == 0) {
      return "";
    }
    StringBuffer sb = new StringBuffer();
    for (Iterator i = coll.iterator(); i.hasNext(); ) {
      if (sb.length() > 0) {
        sb.append(File.pathSeparatorChar);
      }
      sb.append(i.next());
    }
    return sb.toString();
  }
}
