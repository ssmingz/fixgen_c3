class PlaceHold {
  public void openCompareEditor(
      final CompareEditorInput input, final IWorkbenchPage page, final IReusableEditor editor) {
    if (input.canRunAsJob()) {
      openEditorInBackground(input, page, editor);
    } else {
      if (compareResultOK(input, null)) {
        internalOpenEditor(input, page, editor);
      }
    }
  }
}
