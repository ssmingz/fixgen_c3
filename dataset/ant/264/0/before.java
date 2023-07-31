class PlaceHold {
  private void processLine() {
    String s = line.toString();
    project.log(s, msgOutputLevel);
    line = new StringBuffer();
  }
}
