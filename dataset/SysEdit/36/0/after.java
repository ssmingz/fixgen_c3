class PlaceHold {
  private void initLabels() {
    CompareConfiguration cc = getCompareConfiguration();
    cc.setLeftEditable(false);
    cc.setRightEditable(false);
    if (config != null) {
      cc.setLeftLabel(config.getLeftLabel(config));
      cc.setLeftImage(config.getLeftImage(config));
      cc.setRightLabel(config.getRightLabel(config));
      cc.setRightImage(config.getRightImage(config));
    } else {
      String leftLabel = PatchMessages.PatcherCompareEditorInput_LocalCopy;
      cc.setLeftLabel(leftLabel);
      String rightLabel = PatchMessages.PatcherCompareEditorInput_AfterPatch;
      cc.setRightLabel(rightLabel);
    }
  }
}
