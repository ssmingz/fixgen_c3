class PlaceHold {
  protected synchronized void dieOnCircularReference(Stack stk, Project p) {
    if (isChecked()) {
      return;
    }
    super.dieOnCircularReference(stk, p);
    if (!isReference()) {
      pushAndInvokeCircularReferenceCheck(w, stk, p);
      setChecked(true);
    }
  }
}
