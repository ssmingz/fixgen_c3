class PlaceHold {
  public Parameter[] getParams() {
    if (isReference()) {
      ((AntFilterReader) (getCheckedRef())).getParams();
    }
    dieOnCircularReference();
    Parameter[] params = new Parameter[parameters.size()];
    parameters.copyInto(params);
    return params;
  }
}
