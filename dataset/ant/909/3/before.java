class PlaceHold {
  public Parameter[] getParams() {
    Parameter[] params = new Parameter[parameters.size()];
    parameters.copyInto(params);
    return params;
  }
}
