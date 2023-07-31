class PlaceHold {
  public void execute() throws BuildException {
    if (weblogicSystemHome == null) {
      throw new BuildException("weblogic home must be set");
    }
    if (!weblogicSystemHome.isDirectory()) {
      throw new BuildException(
          ("weblogic home directory " + weblogicSystemHome.getPath()) + " is not valid");
    }
    File propertiesFile = new File(weblogicSystemHome, weblogicPropertiesFile);
    if (!propertiesFile.exists()) {
      throw new BuildException(
          (("Properties file " + weblogicPropertiesFile) + " not found in weblogic home ")
              + weblogicSystemHome);
    }
    File securityPolicyFile = null;
    if (securityPolicy == null) {
      securityPolicyFile = new File(weblogicSystemHome, defaultPolicyFile);
    } else {
      securityPolicyFile = new File(weblogicSystemHome, securityPolicy);
    }
    if (!securityPolicyFile.exists()) {
      throw new BuildException(("Security policy " + securityPolicyFile) + " was not found.");
    }
    String execClassPath = project.translatePath(classpath);
    Java weblogicServer = ((Java) (project.createTask("java")));
    weblogicServer.setFork("yes");
    weblogicServer.setClassname("weblogic.Server");
    String jvmArgs = "";
    if (weblogicClasspath != null) {
      jvmArgs += "-Dweblogic.class.path=" + project.translatePath(weblogicClasspath);
    }
    jvmArgs += " -Djava.security.manager -Djava.security.policy==" + securityPolicyFile;
    jvmArgs += " -Dweblogic.system.home=" + weblogicSystemHome;
    jvmArgs += " -Dweblogic.system.name=" + weblogicSystemName;
    jvmArgs += " -Dweblogic.system.propertiesFile=" + weblogicPropertiesFile;
    weblogicServer.setJvmargs(jvmArgs);
    weblogicServer.setClasspath(new Path(execClassPath));
    if (weblogicServer.executeJava() != 0) {
      throw new BuildException("Execution of weblogic server failed");
    }
  }
}
