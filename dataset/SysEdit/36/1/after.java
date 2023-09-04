class PlaceHold {
  private void initLabels() {
    CompareConfiguration cc = getCompareConfiguration();
    cc.setCalculateDiffs(false);
    cc.setLeftEditable(true);
    cc.setRightEditable(false);
    cc.setProperty(CompareEditor.CONFIRM_SAVE_PROPERTY, new Boolean(false));
    if (config != null) {
      cc.setLeftLabel(config.getLeftLabel(config));
      cc.setLeftImage(config.getLeftImage(config));
      cc.setRightLabel(config.getRightLabel(config));
      cc.setRightImage(config.getRightImage(config));
    } else {
      String leftLabel = PatchMessages.HunkMergePageInput_WorkspaceCopy;
      cc.setLeftLabel(leftLabel);
      String rightLabel = PatchMessages.HunkMergePageInput_OrphanedHunk;
      cc.setRightLabel(rightLabel);
    }
  }
}
