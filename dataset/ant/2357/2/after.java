class PlaceHold {
  protected synchronized void dieOnCircularReference(Stack stk, Project p) throws BuildException {
    if (isChecked()) {
      return;
    }
    if (isReference()) {
      super.dieOnCircularReference(stk, p);
    } else {
      if (union != null) {
        pushAndInvokeCircularReferenceCheck(union, stk, p);
      }
      setChecked(true);
    }
  }
}
