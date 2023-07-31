class PlaceHold {
  public int getNameAndTypeEntry(String name, String type) {
    int index = -1;
    for (int i = 0; (i < entries.size()) && (index == (-1)); ++i) {
      Object element = entries.get(i);
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
