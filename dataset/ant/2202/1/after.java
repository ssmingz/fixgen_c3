class PlaceHold {
  public RegularExpression getRef(Project p) {
    if (!isChecked()) {
      Stack stk = new Stack();
      stk.push(this);
      dieOnCircularReference(stk, p);
    }
    Object o = getRefid().getReferencedObject(p);
    if (!(o instanceof RegularExpression)) {
      String msg = (getRefid().getRefId() + " doesn\'t denote a ") + DATA_TYPE_NAME;
      throw new BuildException(msg);
    } else {
      return ((RegularExpression) (o));
    }
  }
}
