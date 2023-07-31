class PlaceHold {
  protected void addVendorFiles(Hashtable ejbFiles, String ddPrefix) {
    File jbossDD = new File(getConfig().descriptorDir, ddPrefix + JBOSS_DD);
    if (jbossDD.exists()) {
      ejbFiles.put(META_DIR + JBOSS_DD, jbossDD);
    } else {
      log(
          ("Unable to locate jboss deployment descriptor. " + "It was expected to be in ")
              + jbossDD.getPath(),
          MSG_WARN);
      return;
    }
    String descriptorFileName = JBOSS_CMP10D;
    if (CMP2_0.equals(getParent().getCmpversion())) {
      descriptorFileName = JBOSS_CMP20D;
    }
    File jbossCMPD = new File(getConfig().descriptorDir, ddPrefix + descriptorFileName);
    if (jbossCMPD.exists()) {
      ejbFiles.put(META_DIR + descriptorFileName, jbossCMPD);
    } else {
      log(
          ("Unable to locate jboss cmp descriptor. " + "It was expected to be in ")
              + jbossCMPD.getPath(),
          MSG_WARN);
      return;
    }
  }
}
