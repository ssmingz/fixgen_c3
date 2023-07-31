class PlaceHold {
  public void sendString(String s, boolean echoString) {
    OutputStream os = this.getOutputStream();
    try {
      os.write((s + "\n").getBytes());
      if (echoString) {
        getLogger().info(s);
      }
      os.flush();
    } catch (Exception e) {
      throw new TaskException("Error", e);
    }
  }
}
