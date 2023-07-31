class PlaceHold {
  private void processLine() {
    String s = line.toString();
    if (s.indexOf("error") > (-1)) {
      errorFlag = true;
    }
    task.log(s);
    line = new StringBuffer();
  }
}
