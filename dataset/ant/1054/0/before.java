class PlaceHold {
  protected synchronized void dieOnCircularReference(Stack stk, Project p) throws BuildException {
    if (isChecked()) {
      return;
    }
    if (isReference()) {
      super.dieOnCircularReference(stk, p);
    } else {
      stk.push(comp);
      DataType.invokeCircularReferenceCheck(comp, stk, p);
      stk.pop();
      setChecked(true);
    }
  }
}
