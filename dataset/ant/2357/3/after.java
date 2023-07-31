class PlaceHold {
  protected synchronized void dieOnCircularReference(Stack stk, Project p) throws BuildException {
    if (isChecked()) {
      return;
    }
    if (isReference()) {
      super.dieOnCircularReference(stk, p);
    } else {
      if (src != null) {
        pushAndInvokeCircularReferenceCheck(src, stk, p);
      }
      setChecked(true);
    }
  }
}
