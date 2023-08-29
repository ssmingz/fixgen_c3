class PlaceHold {
  private void initLabels() {
    CompareConfiguration cc = getCompareConfiguration();
    cc.setCalculateDiffs(false);
    cc.setLeftEditable(true);
    cc.setRightEditable(false);
    cc.setProperty(CompareEditor.CONFIRM_SAVE_PROPERTY, new Boolean(false));
    String leftLabel = PatchMessages.HunkMergePageInput_WorkspaceCopy;
    cc.setLeftLabel(leftLabel);
    String rightLabel = PatchMessages.HunkMergePageInput_OrphanedHunk;
    cc.setRightLabel(rightLabel);
  }
}
