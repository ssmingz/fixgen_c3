class PlaceHold {
  public void setWebxml(File descr) {
    deploymentDescriptor = descr;
    if (!deploymentDescriptor.exists()) {
      throw new BuildException(
          ("Deployment descriptor: " + deploymentDescriptor) + " does not exist.");
    }
    ZipFileSet fs = new ZipFileSet();
    fs.setFile(deploymentDescriptor);
    fs.setFullpath("WEB-INF/web.xml");
    super.addFileset(fs);
  }
}
