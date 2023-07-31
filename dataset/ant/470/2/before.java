class PlaceHold {
  protected FileList getRef(Project p) {
    if (!checked) {
      Stack stk = new Stack();
      stk.push(this);
      dieOnCircularReference(stk, p);
    }
    Object o = ref.getReferencedObject(p);
    if (!(o instanceof FileList)) {
      String msg = ref.getRefId() + " doesn\'t denote a filelist";
      throw new BuildException(msg);
    } else {
      return ((FileList) (o));
    }
  }
}
