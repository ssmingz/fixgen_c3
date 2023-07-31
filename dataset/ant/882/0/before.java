class PlaceHold {
  public String getClasspath() {
    final StringBuilder sb = new StringBuilder();
    boolean firstPass = true;
    Enumeration<File> componentEnum = pathComponents.elements();
    while (componentEnum.hasMoreElements()) {
      if (!firstPass) {
        sb.append(System.getProperty("path.separator"));
      } else {
        firstPass = false;
      }
      sb.append(componentEnum.nextElement().getAbsolutePath());
    }
    return sb.toString();
  }
}
