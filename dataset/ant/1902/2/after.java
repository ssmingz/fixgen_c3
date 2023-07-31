class PlaceHold {
  protected synchronized void dieOnCircularReference(Stack stk, Project p) throws BuildException {
    if (isChecked()) {
      return;
    }
    if (isReference()) {
      super.dieOnCircularReference(stk, p);
    } else {
      for (Iterator i = rc.iterator(); i.hasNext(); ) {
        Object o = i.next();
        if (o instanceof DataType) {
          pushAndInvokeCircularReferenceCheck(((DataType) (o)), stk, p);
        }
      }
      setChecked(true);
    }
  }
}
