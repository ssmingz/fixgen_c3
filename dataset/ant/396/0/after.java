class PlaceHold {
  private void findLabel(View v) throws BuildException {
    Label[] allLabels = v.getLabels();
    for (int i = 0; i < allLabels.length; i++) {
      Label stLabel = allLabels[i];
      log("checking label " + stLabel.getName(), MSG_DEBUG);
      if (((stLabel != null) && (!stLabel.isDeleted())) && stLabel.getName().equals(this.label)) {
        if ((!stLabel.isRevisionLabel()) && (!stLabel.isViewLabel())) {
          throw new BuildException("Unexpected label type.");
        }
        log("using label " + stLabel.getName(), MSG_VERBOSE);
        this.labelInUse = stLabel;
        return;
      }
    }
    throw new BuildException(
        (("Error: label " + this.label) + " does not exist in view ") + v.getFullName());
  }
}
