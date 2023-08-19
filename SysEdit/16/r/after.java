class PlaceHold {
  public Object doSuffix(Object obj, bsh.CallStack callstack, bsh.Interpreter interpreter)
      throws bsh.EvalError {
    // Handle ".class" suffix operation
    if (operation == CLASS)
      if (obj instanceof bsh.BSHType) {
        bsh.NameSpace namespace = callstack.top();
        return ((bsh.BSHType) (obj)).getType(callstack, interpreter);
      } else throw new bsh.EvalError("Attempt to use .class suffix on non class.", this, callstack);

    // Handle other suffix operations
    /* eval( ) the node to an object

    Note: This construct is now necessary where the node may be
    an ambiguous name.  If this becomes common we might want to
    make a static method nodeToObject() or something.

    The point is that we can't just eval() - we need to direct the
    evaluation to the context sensitive type of result; namely
    object, class, etc.
     */
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
      throw new bsh.EvalError("reflection error: " + e, this, callstack);
    } catch (InvocationTargetException e) {
      throw new bsh.TargetError("target exception", e.getTargetException(), this, callstack, true);
    }
  }
}
