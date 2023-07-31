class PlaceHold {
  public void execute() throws BuildException {
    if ((username == null) || (password == null)) {
      throw new BuildException("weblogic username and password must both be set");
    }
    if (serverURL == null) {
      throw new BuildException("The url of the weblogic server must be provided.");
    }
    String execClassPath = project.translatePath(classpath);
    Java weblogicAdmin = ((Java) (project.createTask("java")));
    weblogicAdmin.setFork(true);
    weblogicAdmin.setClassname("weblogic.Admin");
    String args = (((((serverURL + " SHUTDOWN ") + username) + " ") + password) + " ") + delay;
    weblogicAdmin.setArgs(args);
    weblogicAdmin.setClasspath(new Path(project, execClassPath));
    weblogicAdmin.execute();
  }
}
