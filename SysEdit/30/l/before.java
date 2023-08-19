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
    org.eclipse.compare.internal.DocumentManager.remove(getDocument2('A', input));
    org.eclipse.compare.internal.DocumentManager.remove(getDocument2('L', input));
    org.eclipse.compare.internal.DocumentManager.remove(getDocument2('R', input));
    if (DEBUG) org.eclipse.compare.internal.DocumentManager.dump();

    if (fPreferenceChangeListener != null) {
      org.eclipse.jface.resource.JFaceResources.getFontRegistry()
          .removeListener(fPreferenceChangeListener);
      org.eclipse.jface.resource.JFaceResources.getColorRegistry()
          .removeListener(fPreferenceChangeListener);
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
        org.eclipse.swt.graphics.Color color = ((org.eclipse.swt.graphics.Color) (i.next()));
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
