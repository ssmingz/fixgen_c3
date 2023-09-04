class PlaceHold {
  protected void handleDispose(DisposeEvent event) {

    if (fKeyBindingService != null) {
      IAction a;
      if (fNextItem != null) {
        a = fNextItem.getAction();
        if (a != null) fKeyBindingService.unregisterAction(a);
      }
      if (fPreviousItem != null) {
        a = fPreviousItem.getAction();
        if (a != null) fKeyBindingService.unregisterAction(a);
      }
      if (fCopyDiffLeftToRightItem != null) {
        a = fCopyDiffLeftToRightItem.getAction();
        if (a != null) fKeyBindingService.unregisterAction(a);
      }
      if (fCopyDiffRightToLeftItem != null) {
        a = fCopyDiffRightToLeftItem.getAction();
        if (a != null) fKeyBindingService.unregisterAction(a);
      }
      fKeyBindingService = null;
    }

    Object input = getInput();
    DocumentManager.remove(getDocument2('A', input));
    DocumentManager.remove(getDocument2('L', input));
    DocumentManager.remove(getDocument2('R', input));

    if (DEBUG) DocumentManager.dump();

    if (fPreferenceChangeListener != null) {
      JFaceResources.getFontRegistry().removeListener(fPreferenceChangeListener);
      JFaceResources.getColorRegistry().removeListener(fPreferenceChangeListener);
      if (fPreferenceStore != null)
        fPreferenceStore.removePropertyChangeListener(fPreferenceChangeListener);
      fPreferenceChangeListener = null;
    }

    fLeftCanvas = null;
    fRightCanvas = null;
    fVScrollBar = null;
    fBirdsEyeCanvas = null;
    fSummaryHeader = null;

    unsetDocument(fAncestor);
    unsetDocument(fLeft);
    unsetDocument(fRight);

    if (fColors != null) {
      Iterator i = fColors.values().iterator();
      while (i.hasNext()) {
        Color color = (Color) i.next();
        if (!color.isDisposed()) color.dispose();
      }
      fColors = null;
    }

    if (fBirdsEyeCursor != null) {
      fBirdsEyeCursor.dispose();
      fBirdsEyeCursor = null;
    }

    super.handleDispose(event);
  }
}
