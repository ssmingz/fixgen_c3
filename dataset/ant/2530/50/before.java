class PlaceHold {
  public int getConstantEntry(Object constantValue) {
    int index = -1;
    for (int i = 0; (i < entries.size()) && (index == (-1)); ++i) {
      Object element = entries.elementAt(i);
      if (element instanceof ConstantCPInfo) {
        ConstantCPInfo constantEntry = ((ConstantCPInfo) (element));
        if (constantEntry.getValue().equals(constantValue)) {
          index = i;
        }
      }
    }
    return index;
  }
}
