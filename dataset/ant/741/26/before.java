class PlaceHold {
  private File findSecurityPolicyFile(String defaultSecurityPolicy) {
    String securityPolicy = this.securityPolicy;
    if (securityPolicy == null) {
      securityPolicy = defaultSecurityPolicy;
    }
    File securityPolicyFile = new File(weblogicSystemHome, securityPolicy);
    if ((this.securityPolicy != null) && (!securityPolicyFile.exists())) {
      securityPolicyFile = project.resolveFile(securityPolicy);
    }
    if (!securityPolicyFile.exists()) {
      throw new BuildException(("Security policy " + securityPolicy) + " was not found.");
    }
    return securityPolicyFile;
  }
}
