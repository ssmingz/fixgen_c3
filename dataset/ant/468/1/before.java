class PlaceHold {
  protected AbstractFileSet getRef(Project p) {
    if (!checked) {
      Stack stk = new Stack();
      stk.push(this);
      dieOnCircularReference(stk, p);
    }
    Object o = ref.getReferencedObject(p);
    if (!(o instanceof FileSet)) {
      String msg = ref.getRefId() + " doesn\'t denote a fileset";
      throw new BuildException(msg);
    } else {
      return ((AbstractFileSet) (o));
    }
  }
}
