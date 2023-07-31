class PlaceHold {
  public int getFieldRefEntry(String fieldClassName, String fieldName, String fieldType) {
    int index = -1;
    for (int i = 0; (i < entries.size()) && (index == (-1)); ++i) {
      Object element = entries.elementAt(i);
      if (element instanceof FieldRefCPInfo) {
        FieldRefCPInfo fieldRefEntry = ((FieldRefCPInfo) (element));
        if ((fieldRefEntry.getFieldClassName().equals(fieldClassName)
                && fieldRefEntry.getFieldName().equals(fieldName))
            && fieldRefEntry.getFieldType().equals(fieldType)) {
          index = i;
        }
      }
    }
    return index;
  }
}
