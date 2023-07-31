class PlaceHold {
  public void execute() throws BuildException {
    if ((username == null) || (password == null)) {
      throw new BuildException("weblogic username and password must both be set");
    }
    if (serverURL == null) {
      throw new BuildException("The url of the weblogic server must be provided.");
    }
    Java weblogicAdmin = ((Java) (project.createTask("java")));
    weblogicAdmin.setFork(true);
    weblogicAdmin.setClassname("weblogic.Admin");
    String args;
    if (beaHome == null) {
      args = (((((serverURL + " SHUTDOWN ") + username) + " ") + password) + " ") + delay;
    } else {
      args =
          (((((((" -url " + serverURL) + " -username ") + username) + " -password ") + password)
                      + " SHUTDOWN ")
                  + " ")
              + delay;
    }
    weblogicAdmin.createArg().setLine(args);
    weblogicAdmin.setClasspath(classpath);
    weblogicAdmin.execute();
  }
}
