class PlaceHold {
  protected Mapper getRef() {
    if (!isChecked()) {
      Stack stk = new Stack();
      stk.push(this);
      dieOnCircularReference(stk, getProject());
    }
    Object o = getRefid().getReferencedObject(getProject());
    if (!(o instanceof Mapper)) {
      String msg = getRefid().getRefId() + " doesn\'t denote a mapper";
      throw new BuildException(msg);
    } else {
      return ((Mapper) (o));
    }
  }
}
