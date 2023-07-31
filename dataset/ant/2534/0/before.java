class PlaceHold {
  protected synchronized void dieOnCircularReference(Stack stk, Project p) throws BuildException {
    if (isChecked()) {
      return;
    }
    if (isReference()) {
      super.dieOnCircularReference(stk, p);
    } else {
      if (tokenizer instanceof DataType) {
        stk.push(tokenizer);
        invokeCircularReferenceCheck(((DataType) (tokenizer)), stk, p);
        stk.pop();
      }
      setChecked(true);
    }
  }
}
