class PlaceHold {
  public Object doSuffix(Object obj, bsh.CallStack callstack, bsh.Interpreter interpreter)
      throws bsh.EvalError {
    if (operation == CLASS)
      if (obj instanceof bsh.BSHType) {
        bsh.NameSpace namespace = callstack.top();
        return ((bsh.BSHType) (obj)).getType(namespace);
      } else throw new bsh.EvalError("Attemp to invoke .class on non class.", this);

    if (obj instanceof bsh.SimpleNode)
      if (obj instanceof bsh.BSHAmbiguousName)
        obj = ((bsh.BSHAmbiguousName) (obj)).toObject(callstack, interpreter);
      else obj = ((bsh.SimpleNode) (obj)).eval(callstack, interpreter);

    try {
      switch (operation) {
        case INDEX:
          return doIndex(obj, callstack, interpreter);
        case NAME:
          return doName(obj, callstack, interpreter);
        case PROPERTY:
          return doProperty(obj, callstack, interpreter);
        default:
          throw new bsh.InterpreterError("LHS suffix");
      }
    } catch (bsh.ReflectError e) {
      throw new bsh.EvalError("reflection error: " + e, this);
    } catch (InvocationTargetException e) {
      throw new bsh.TargetError(e.getTargetException(), this);
    }
  }
}
