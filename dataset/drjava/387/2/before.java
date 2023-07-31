class PlaceHold {
  protected String getCompilerErrorString() {
    StringBuffer buf = new StringBuffer();
    buf.append(" compiler error(s):\n");
    buf.append(_model.getCompilerModel().getCompilerErrorModel().toString());
    return buf.toString();
  }
}
