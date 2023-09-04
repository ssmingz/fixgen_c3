class PlaceHold {
  protected void handleDispose(DisposeEvent event) {
    Utilities.deregisterActions(fHandlerService, fActivations);
    fHandlerService = null;
    Object input = getInput();
    removeFromDocumentManager(ANCESTOR_CONTRIBUTOR, input);
    removeFromDocumentManager(LEFT_CONTRIBUTOR, input);
    removeFromDocumentManager(RIGHT_CONTRIBUTOR, input);
    if (DEBUG) DocumentManager.dump();

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
    fAncestorContributor.unsetDocument(fAncestor);
    fLeftContributor.unsetDocument(fLeft);
    fRightContributor.unsetDocument(fRight);
    disconnect(fLeftContributor);
    disconnect(fRightContributor);
    disconnect(fAncestorContributor);
    if (fColors != null) {
      Iterator i = fColors.values().iterator();
      while (i.hasNext()) {
        Color color = ((Color) (i.next()));
        if (!color.isDisposed()) color.dispose();
      }
      fColors = null;
    }
    if (fBirdsEyeCursor != null) {
      fBirdsEyeCursor.dispose();
      fBirdsEyeCursor = null;
    }
    if (showWhitespaceAction != null) showWhitespaceAction.dispose();

    if (toggleLineNumbersAction != null) toggleLineNumbersAction.dispose();

    super.handleDispose(event);
  }
}
