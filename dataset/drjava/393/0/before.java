class PlaceHold {
  public Object visit(StaticMethodCall node) {
    _checkInterrupted(node);
    Method m = ((Method) (node.getProperty(METHOD)));
    if (!Modifier.isStatic(m.getModifiers())) {
      StringBuffer buf = new StringBuffer();
      buf.append(m.getDeclaringClass());
      buf.append(".");
      buf.append(m.getName());
      buf.append("(");
      boolean first = true;
      Class<?>[] params = m.getParameterTypes();
      for (int i = 0; i < params.length; i++) {
        if (first) {
          first = false;
        } else {
          buf.append(", ");
        }
        buf.append(params[i].getName());
      }
      buf.append(")");
      buf.append(" is not a static method.");
      throw new InteractionsException(buf.toString());
    }
    Object ret = super.visit(node);
    if (m.getReturnType().equals(Void.TYPE)) {
      return Interpreter.NO_RESULT;
    } else {
      return ret;
    }
  }
}
