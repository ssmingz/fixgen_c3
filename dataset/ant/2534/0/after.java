class PlaceHold {
  protected synchronized void dieOnCircularReference(Stack stk, Project p) throws BuildException {
    if (isChecked()) {
      return;
    }
    if (isReference()) {
      super.dieOnCircularReference(stk, p);
    } else {
      if (tokenizer instanceof DataType) {
        pushAndInvokeCircularReferenceCheck(((DataType) (tokenizer)), stk, p);
      }
      setChecked(true);
    }
  }
}
