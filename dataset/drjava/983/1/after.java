class PlaceHold {
  private void _dumpVariablesIntoInterpreterAndSwitch()
      throws DebugException, AbsentInformationException {
    _log.log(this + " invoked dumpVariablesIntoInterpreterAndSwitch");
    try {
      ThreadReference suspendedThreadRef = _suspendedThreads.peek();
      StackFrame frame = suspendedThreadRef.frame(0);
      Location l = frame.location();
      ReferenceType rt = l.declaringType();
      String className = rt.name();
      String interpreterName = _getUniqueThreadName(suspendedThreadRef);
      _model.getInteractionsModel().addInterpreter(interpreterName);
      ObjectReference debugInterpreter = _getDebugInterpreter();
      _log.log(this + " executing: frame = suspendedThreadRef.frame(0);");
      frame = suspendedThreadRef.frame(0);
      List<LocalVariable> vars = frame.visibleVariables();
      Iterator<LocalVariable> varsIterator = vars.iterator();
      _log.log(this + " got visibleVariables");
      while (varsIterator.hasNext()) {
        LocalVariable localVar = varsIterator.next();
        _log.log((this + " defined local variable: ") + localVar);
        frame = suspendedThreadRef.frame(0);
        Value val = frame.getValue(localVar);
        Type type;
        if (val != null) {
          type = val.type();
        } else {
          try {
            type = localVar.type();
          } catch (ClassNotLoadedException e) {
            List<ReferenceType> classes = _vm.classesByName(localVar.typeName());
            if (!classes.isEmpty()) {
              type = classes.get(0);
            } else {
              type = null;
            }
          }
        }
        _defineVariable(suspendedThreadRef, debugInterpreter, localVar.name(), val, type);
      }
      frame = suspendedThreadRef.frame(0);
      Value thisVal = frame.thisObject();
      if (thisVal != null) {
        _defineVariable(suspendedThreadRef, debugInterpreter, "this", thisVal, thisVal.type());
      }
      String prompt = _getPromptString(suspendedThreadRef);
      _log.log(this + " is setting active interpreter");
      _model.getInteractionsModel().setActiveInterpreter(interpreterName, prompt);
    } catch (InvalidTypeException exc) {
      throw new DebugException(exc.toString());
    } catch (IncompatibleThreadStateException e2) {
      throw new DebugException(e2.toString());
    } catch (ClassNotLoadedException e3) {
      throw new DebugException(e3.toString());
    } catch (InvocationException e4) {
      throw new DebugException(e4.toString());
    }
  }
}
