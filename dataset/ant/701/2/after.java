class PlaceHold {
  protected String[] getParameters() throws TaskException {
    ArrayList v = new ArrayList();
    if (format != null) {
      v.add("-format=" + format);
    }
    if (type != null) {
      v.add("-type=" + type);
    }
    if (percent != null) {
      v.add("-percent=" + percent);
    }
    if (filters != null) {
      v.add("-filters=" + filters);
    }
    v.add("-output=" + getContext().resolveFile(tofile.getPath()));
    v.add("-snapshot=" + getContext().resolveFile(snapshot.getPath()));
    if (sourcePath == null) {
      sourcePath = new Path();
      Path path1 = sourcePath;
      final Path path = new Path();
      path1.add(path);
      path.setLocation(getBaseDirectory());
    }
    v.add("-sourcepath=" + sourcePath);
    if ("verydetailed".equalsIgnoreCase(format) && "xml".equalsIgnoreCase(type)) {
      v.add("-inc_src_text=" + (includeSource ? "on" : "off"));
    }
    return ((String[]) (v.toArray(new String[v.size()])));
  }
}
