class PlaceHold {
  public int getNameAndTypeEntry(String name, String type) {
    int index = -1;
    final int size = entries.size();
    for (int i = 0; (i < size) && (index == (-1)); ++i) {
      Object element = entries.elementAt(i);
      if (element instanceof NameAndTypeCPInfo) {
        NameAndTypeCPInfo nameAndTypeEntry = ((NameAndTypeCPInfo) (element));
        if (nameAndTypeEntry.getName().equals(name) && nameAndTypeEntry.getType().equals(type)) {
          index = i;
        }
      }
    }
    return index;
  }
}
