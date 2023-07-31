class PlaceHold {
  public int getInterfaceMethodRefEntry(
      String interfaceMethodClassName, String interfaceMethodName, String interfaceMethodType) {
    int index = -1;
    final int size = entries.size();
    for (int i = 0; (i < size) && (index == (-1)); ++i) {
      Object element = entries.elementAt(i);
      if (element instanceof InterfaceMethodRefCPInfo) {
        InterfaceMethodRefCPInfo interfaceMethodRefEntry = ((InterfaceMethodRefCPInfo) (element));
        if ((interfaceMethodRefEntry.getInterfaceMethodClassName().equals(interfaceMethodClassName)
                && interfaceMethodRefEntry.getInterfaceMethodName().equals(interfaceMethodName))
            && interfaceMethodRefEntry.getInterfaceMethodType().equals(interfaceMethodType)) {
          index = i;
        }
      }
    }
    return index;
  }
}
