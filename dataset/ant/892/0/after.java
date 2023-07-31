class PlaceHold {
  protected void dieOnCircularReference(Stack stk, Project p) throws BuildException {
    if (isChecked()) {
      return;
    }
    if (isReference()) {
      super.dieOnCircularReference(stk, p);
    } else {
      for (Iterator i = v.iterator(); i.hasNext(); ) {
        Object o = i.next();
        if (o instanceof DataType) {
          pushAndInvokeCircularReferenceCheck(((DataType) (o)), stk, p);
        }
      }
      setChecked(true);
    }
  }
}
