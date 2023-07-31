class PlaceHold {
  public void sendString(String s, boolean echoString) {
    OutputStream os = this.getOutputStream();
    try {
      os.write((s + "\n").getBytes());
      if (echoString) {
        log(s, MSG_INFO);
      }
      os.flush();
    } catch (Exception e) {
      throw new BuildException(e);
    }
  }
}
