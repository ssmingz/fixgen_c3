class PlaceHold {
  public Object visit(SimpleAllocation node) {
    Constructor cons = ((Constructor) (node.getProperty(CONSTRUCTOR)));
    List<Expression> larg = node.getArguments();
    Object[] args = Constants.EMPTY_OBJECT_ARRAY;
    Class<?>[] typs = cons.getParameterTypes();
    if (larg != null) {
      args = new Object[typs.length];
      ListIterator<Expression> it = larg.listIterator();
      int i = 0;
      while (i < (typs.length - 1)) {
        args[i++] = it.next().acceptVisitor(this);
      }
      if (typs.length > 0) {
        Object last = null;
        if (it.hasNext()) {
          last = it.next().acceptVisitor(this);
        }
        if (!TigerUtilities.isVarArgs(cons)) {
          args[i] = last;
        } else if ((last != null) && typs[i].isAssignableFrom(last.getClass())) {
          args[i] = last;
        } else {
          it.previous();
          args[i] = buildArrayOfRemainingArgs(typs, larg.size(), it);
        }
      }
    }
    return context.invokeConstructor(node, args);
  }
}
