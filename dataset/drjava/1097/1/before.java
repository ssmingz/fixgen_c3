class PlaceHold {
  @Override
  public Type visit(StaticMethodCall node) {
    Type t = checkTypeName(node.getMethodType());
    Iterable<? extends Expression> args = node.getArguments();
    checkList(args);
    Iterable<Type> targs = IterUtil.empty();
    if (node instanceof PolymorphicStaticMethodCall) {
      targs = checkTypeNameList(((PolymorphicStaticMethodCall) (node)).getTypeArguments());
    }
    try {
      MethodInvocation inv = ts.lookupStaticMethod(t, node.getMethodName(), targs, args, expected);
      checkAccessibility(inv.method(), node);
      checkThrownExceptions(inv.thrown(), node);
      node.setArguments(CollectUtil.makeList(inv.args()));
      setMethod(node, inv.method());
      Type result = ts.capture(inv.returnType());
      debug.logValue("Type of method call " + node.getMethodName(), ts.wrap(result));
      addRuntimeCheck(node, result, inv.method().returnType());
      return setType(node, result);
    } catch (InvalidTypeArgumentException e) {
      throw new ExecutionError("type.argument", node);
    } catch (UnmatchedLookupException e) {
      setErrorStrings(node, ts.userRepresentation(t), node.getMethodName(), nodeTypesString(args));
      if (e.matches() > 1) {
        throw new ExecutionError("ambiguous.method", node);
      } else {
        throw new ExecutionError("no.such.method", node);
      }
    }
  }
}
