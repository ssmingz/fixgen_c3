class PlaceHold {
  protected void handleDispose(DisposeEvent event) {
    if (fHandlerService != null) fHandlerService.dispose();

    Object input = getInput();
    if (input instanceof ICompareInput) {
      ICompareContainer container = getCompareConfiguration().getContainer();
      container.removeCompareInputChangeListener(
          ((ICompareInput) (input)), fCompareInputChangeListener);
    }
    if (input != null) {
      ICompareInputLabelProvider lp = getCompareConfiguration().getLabelProvider();
      if (lp != null) lp.removeListener(labelChangeListener);
    }
    if (fPropertyChangeListener != null) {
      fCompareConfiguration.removePropertyChangeListener(fPropertyChangeListener);
      fPropertyChangeListener = null;
    }
    fAncestorLabel = null;
    fLeftLabel = null;
    fDirectionLabel = null;
    fRightLabel = null;
    fCenter = null;
    if (fRightArrow != null) {
      fRightArrow.dispose();
      fRightArrow = null;
    }
    if (fLeftArrow != null) {
      fLeftArrow.dispose();
      fLeftArrow = null;
    }
    if (fBothArrow != null) {
      fBothArrow.dispose();
      fBothArrow = null;
    }
    if (fNormalCursor != null) {
      fNormalCursor.dispose();
      fNormalCursor = null;
    }
    if (fHSashCursor != null) {
      fHSashCursor.dispose();
      fHSashCursor = null;
    }
    if (fVSashCursor != null) {
      fVSashCursor.dispose();
      fVSashCursor = null;
    }
    if (fHVSashCursor != null) {
      fHVSashCursor.dispose();
      fHVSashCursor = null;
    }
    super.handleDispose(event);
  }
}
