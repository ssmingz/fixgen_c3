class PlaceHold {
  public Object visit(StaticMethodCall node) {
    Method m = ((Method) (node.getProperty(METHOD)));
    List<Expression> larg = node.getArguments();
    Object[] args = Constants.EMPTY_OBJECT_ARRAY;
    Class<?>[] typs = m.getParameterTypes();
    if (larg != null) {
      args = new Object[typs.length];
      ListIterator<Expression> it = larg.listIterator();
      int i = 0;
      while (i < (typs.length - 1)) {
        args[i] = it.next().acceptVisitor(this);
        i++;
      }
      if (typs.length > 0) {
        Object last = null;
        if (it.hasNext()) {
          last = it.next().acceptVisitor(this);
        }
        if (!TigerUtilities.isVarArgs(m)) {
          args[i] = last;
        } else if ((last != null) && typs[i].isAssignableFrom(last.getClass())) {
          args[i] = last;
        } else {
          it.previous();
          args[i] = buildArrayOfRemainingArgs(typs, larg.size(), it);
        }
      }
    } else if (TigerUtilities.isVarArgs(m) && (typs.length == 1)) {
      Class<?> compType = typs[0].getComponentType();
      args = new Object[] {Array.newInstance(compType, 0)};
    }
    try {
      return m.invoke(null, args);
    } catch (InvocationTargetException e) {
      if (e.getTargetException() instanceof Error) {
        throw ((Error) (e.getTargetException()));
      } else if (e.getTargetException() instanceof RuntimeException) {
        throw ((RuntimeException) (e.getTargetException()));
      }
      throw new ThrownException(e.getTargetException());
    } catch (Exception e) {
      throw new CatchedExceptionError(e, node);
    }
  }
}
