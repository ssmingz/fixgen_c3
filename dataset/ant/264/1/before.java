class PlaceHold {
  private void processLine() {
    String s = line.toString();
    if (s.indexOf("error") > (-1)) {
      errorFlag = true;
    }
    project.log(s);
    line = new StringBuffer();
  }
}
