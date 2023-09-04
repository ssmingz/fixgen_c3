class PlaceHold {
  public bsh.LHS doLHSSuffix(Object obj, bsh.CallStack callstack, bsh.Interpreter interpreter)
      throws bsh.EvalError {
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
      throw new bsh.EvalError("reflection error: " + e, this, callstack);
    } catch (InvocationTargetException e) {
      throw new bsh.TargetError("target exception", e.getTargetException(), this, callstack, true);
    }
  }
}
