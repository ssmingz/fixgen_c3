class PlaceHold {
  @Override
  public Object clone() {
    try {
      Archives a = ((Archives) (super.clone()));
      a.zips = ((Union) (zips.clone()));
      a.tars = ((Union) (tars.clone()));
      return a;
    } catch (CloneNotSupportedException e) {
      throw new BuildException(e);
    }
  }
}
