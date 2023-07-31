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
          stk.push(o);
          invokeCircularReferenceCheck(((DataType) (o)), stk, p);
          stk.pop();
        }
      }
      setChecked(true);
    }
  }
}
