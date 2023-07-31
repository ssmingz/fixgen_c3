class PlaceHold {
  public void validateReplacefilters() throws TaskException {
    for (int i = 0; i < replacefilters.size(); i++) {
      Replacefilter element = ((Replacefilter) (replacefilters.elementAt(i)));
      element.validate();
    }
  }
}
