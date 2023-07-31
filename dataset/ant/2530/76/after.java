class PlaceHold {
  public int getClassEntry(String className) {
    int index = -1;
    for (int i = 0; (i < entries.size()) && (index == (-1)); ++i) {
      Object element = entries.get(i);
      if (element instanceof ClassCPInfo) {
        ClassCPInfo classinfo = ((ClassCPInfo) (element));
        if (classinfo.getClassName().equals(className)) {
          index = i;
        }
      }
    }
    return index;
  }
}
