class PlaceHold {
  protected synchronized void dieOnCircularReference(Stack stk, Project p) throws BuildException {
    if (isChecked()) {
      return;
    }
    if (isReference()) {
      super.dieOnCircularReference(stk, p);
    } else {
      if (src != null) {
        stk.push(src);
        invokeCircularReferenceCheck(src, stk, p);
        stk.pop();
      }
      setChecked(true);
    }
  }
}
