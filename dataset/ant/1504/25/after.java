class PlaceHold {
  protected String[] getParameters() throws TaskException {
    Vector v = new Vector();
    if (format != null) {
      v.addElement("-format=" + format);
    }
    if (type != null) {
      v.addElement("-type=" + type);
    }
    if (percent != null) {
      v.addElement("-percent=" + percent);
    }
    if (filters != null) {
      v.addElement("-filters=" + filters);
    }
    v.addElement("-output=" + resolveFile(tofile.getPath()));
    v.addElement("-snapshot=" + resolveFile(snapshot.getPath()));
    if (sourcePath == null) {
      sourcePath = new Path(getProject());
      sourcePath.createPath().setLocation(getBaseDirectory());
    }
    v.addElement("-sourcepath=" + sourcePath);
    if ("verydetailed".equalsIgnoreCase(format) && "xml".equalsIgnoreCase(type)) {
      v.addElement("-inc_src_text=" + (includeSource ? "on" : "off"));
    }
    String[] params = new String[v.size()];
    v.copyInto(params);
    return params;
  }
}
