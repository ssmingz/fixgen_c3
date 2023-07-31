class PlaceHold {
  private void dumpVariablesIntoInterpreterAndSwitch() throws DebugException {
    try {
      ThreadReference suspendedThreadRef = _suspendedThreads.peek();
      String interpreterName = _getUniqueThreadName(suspendedThreadRef);
      ((DefaultGlobalModel) (_model)).getInteractionsModel().addJavaInterpreter(interpreterName);
      StackFrame frame = suspendedThreadRef.frame(0);
      List vars = frame.visibleVariables();
      Iterator varsIterator = vars.iterator();
      System.out.print("Getting debugInterpreter...");
      ObjectReference debugInterpreter = getDebugInterpreter(interpreterName);
      System.out.println("done.");
      while (varsIterator.hasNext()) {
        LocalVariable localVar = ((LocalVariable) (varsIterator.next()));
        frame = suspendedThreadRef.frame(0);
        Value val = frame.getValue(localVar);
        _defineVariable(suspendedThreadRef, debugInterpreter, localVar.name(), val);
      }
      frame = suspendedThreadRef.frame(0);
      Value thisVal = frame.thisObject();
      if (thisVal != null) {
        _defineVariable(suspendedThreadRef, debugInterpreter, "this2", thisVal);
      }
      String prompt = ("[" + suspendedThreadRef.name()) + "] > ";
      ((DefaultGlobalModel) (_model))
          .getInteractionsModel()
          .setActiveInterpreter(interpreterName, prompt);
    } catch (InvalidTypeException exc) {
      throw new DebugException(exc.toString());
    } catch (AbsentInformationException e2) {
      throw new DebugException(e2.toString());
    } catch (IncompatibleThreadStateException e) {
      throw new DebugException(e.toString());
    } catch (ClassNotLoadedException e3) {
      throw new DebugException(e3.toString());
    } catch (InvocationException e4) {
      throw new DebugException(e4.toString());
    }
  }
}
