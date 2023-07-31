class PlaceHold {
  protected void dieOnCircularReference(Stack stk, Project p) throws BuildException {
    if (isChecked()) {
      return;
    }
    if (isReference()) {
      super.dieOnCircularReference(stk, p);
    } else {
      for (Iterator i = getNested().iterator(); i.hasNext(); ) {
        Object o = i.next();
        if (o instanceof DataType) {
          invokeCircularReferenceCheck(((DataType) (o)), stk, p);
        }
      }
      setChecked(true);
    }
  }
}
