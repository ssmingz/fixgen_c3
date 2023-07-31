class PlaceHold {
  protected synchronized void dieOnCircularReference(Stack stk, Project p) throws BuildException {
    if (isChecked()) {
      return;
    }
    if (isReference()) {
      super.dieOnCircularReference(stk, p);
    } else {
      if (rc instanceof DataType) {
        stk.push(rc);
        invokeCircularReferenceCheck(((DataType) (rc)), stk, p);
        stk.pop();
      }
      setChecked(true);
    }
  }
}
