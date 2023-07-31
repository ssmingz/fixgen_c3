class PlaceHold {
  protected synchronized void dieOnCircularReference(Stack stk, Project p) {
    if (isChecked()) {
      return;
    }
    super.dieOnCircularReference(stk, p);
    if (!isReference()) {
      stk.push(w);
      invokeCircularReferenceCheck(w, stk, p);
      stk.pop();
      setChecked(true);
    }
  }
}
