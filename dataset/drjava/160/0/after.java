class PlaceHold {
  protected ReferenceType _getReferenceTypeForField(String field, Context context) {
    TypeChecker tc = makeTypeChecker(context);
    int index = _indexOfWithinBoundaries(_getFullyQualifiedClassNameForThis(), field);
    if (index != (-1)) {
      int lastDollar = field.lastIndexOf("$");
      int lastDot = field.lastIndexOf(".");
      if (lastDollar != (-1)) {
        field = field.substring(lastDollar + 1, field.length());
      } else if (lastDot != (-1)) {
        field = field.substring(lastDot + 1, field.length());
      }
      LinkedList list = new LinkedList();
      StringTokenizer st = new StringTokenizer(_getFullyQualifiedClassNameForThis(), "$.");
      String currString = st.nextToken();
      while (!currString.equals(field)) {
        list.add(new Identifier(currString));
        currString = st.nextToken();
      }
      list.add(new Identifier(field));
      ReferenceType rt = new ReferenceType(list);
      try {
        tc.visit(rt);
        return rt;
      } catch (ExecutionError e) {
        return null;
      }
    } else {
      return null;
    }
  }
}
