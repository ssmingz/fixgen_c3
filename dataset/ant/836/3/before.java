class PlaceHold {
  public RegularExpression getRef(Project p) {
    if (!checked) {
      Stack stk = new Stack();
      stk.push(this);
      dieOnCircularReference(stk, p);
    }
    Object o = ref.getReferencedObject(p);
    if (!(o instanceof RegularExpression)) {
      String msg = (ref.getRefId() + " doesn\'t denote a ") + DATA_TYPE_NAME;
      throw new BuildException(msg);
    } else {
      return ((RegularExpression) (o));
    }
  }
}
