class PlaceHold {
  private void processLine() {
    String s = line.toString();
    task.log(s, msgOutputLevel);
    line = new StringBuffer();
  }
}
