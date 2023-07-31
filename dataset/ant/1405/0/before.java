class PlaceHold {
  public void processDescriptor(String descriptorName, SAXParser saxParser) {
    this.descriptorName = descriptorName;
    log(
        ((("iPlanet Deployment Tool processing: " + descriptorName) + " (and ")
                + getIasDescriptorName())
            + ")",
        MSG_VERBOSE);
    super.processDescriptor(descriptorName, saxParser);
  }
}
