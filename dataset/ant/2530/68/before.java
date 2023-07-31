class PlaceHold {
  public int getMethodRefEntry(String methodClassName, String methodName, String methodType) {
    int index = -1;
    final int size = entries.size();
    for (int i = 0; (i < size) && (index == (-1)); ++i) {
      Object element = entries.elementAt(i);
      if (element instanceof MethodRefCPInfo) {
        MethodRefCPInfo methodRefEntry = ((MethodRefCPInfo) (element));
        if ((methodRefEntry.getMethodClassName().equals(methodClassName)
                && methodRefEntry.getMethodName().equals(methodName))
            && methodRefEntry.getMethodType().equals(methodType)) {
          index = i;
        }
      }
    }
    return index;
  }
}
