class PlaceHold {
  public void setAppxml(File descr) {
    deploymentDescriptor = descr;
    if (!deploymentDescriptor.exists()) {
      throw new BuildException(
          ("Deployment descriptor: " + deploymentDescriptor) + " does not exist.");
    }
    ZipFileSet fs = new ZipFileSet();
    fs.setFile(deploymentDescriptor);
    fs.setFullpath(XML_DESCRIPTOR_PATH);
    super.addFileset(fs);
  }
}
