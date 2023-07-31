class PlaceHold {
  public String getClasspathString() {
    StringBuffer cp = new StringBuffer();
    for (int i = 0; i < _classpath.size(); i++) {
      cp.append(_classpath.elementAt(i));
      cp.append(System.getProperty("path.separator"));
    }
    return cp.toString();
  }
}
