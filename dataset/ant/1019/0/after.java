class PlaceHold {
  public void addConfiguredSysproperty(Environment.Variable sysp) {
    String testString = sysp.getContent();
    getProject().log("sysproperty added : " + testString, MSG_DEBUG);
    getCommandline().addSysproperty(sysp);
  }
}
