class PlaceHold {
  public void validateReplacefilters() throws BuildException {
    for (int i = 0; i < replacefilters.size(); i++) {
      Replacefilter element = ((Replacefilter) (replacefilters.get(i)));
      element.validate();
    }
  }
}
