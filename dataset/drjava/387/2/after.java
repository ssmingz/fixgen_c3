class PlaceHold {
  protected String getCompilerErrorString() {
    final StringBuilder buf = new StringBuilder();
    buf.append(" compiler error(s):\n");
    buf.append(_model.getCompilerModel().getCompilerErrorModel().toString());
    return buf.toString();
  }
}
