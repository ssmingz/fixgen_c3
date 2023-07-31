class PlaceHold {
  protected Mapper getRef() {
    if (!checked) {
      Stack stk = new Stack();
      stk.push(this);
      dieOnCircularReference(stk, getProject());
    }
    Object o = ref.getReferencedObject(getProject());
    if (!(o instanceof Mapper)) {
      String msg = ref.getRefId() + " doesn\'t denote a mapper";
      throw new BuildException(msg);
    } else {
      return ((Mapper) (o));
    }
  }
}
