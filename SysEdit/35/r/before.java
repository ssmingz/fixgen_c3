class PlaceHold {
  protected final org.eclipse.compare.contentmergeviewer.Control buildControl(Composite parent) {
    fComposite =
        new Composite(parent, fStyles | org.eclipse.swt.SWT.LEFT_TO_RIGHT) {
          // we force a specific direction
          public boolean setFocus() {
            return internalSetFocus();
          }
        };
    fComposite.setData(CompareUI.COMPARE_VIEWER_TITLE, getTitle());
    hookControl(fComposite); // hook help & dispose listener

    fComposite.setLayout(new ContentMergeViewerLayout());
    int style = org.eclipse.swt.SWT.SHADOW_OUT;
    fAncestorLabel = new org.eclipse.swt.custom.CLabel(fComposite, style);
    fLeftLabel = new org.eclipse.swt.custom.CLabel(fComposite, style);
    new Resizer(fLeftLabel, VERTICAL);
    fDirectionLabel = new org.eclipse.swt.custom.CLabel(fComposite, style);
    fDirectionLabel.setAlignment(SWT.CENTER);
    new Resizer(fDirectionLabel, HORIZONTAL | VERTICAL);
    fRightLabel = new org.eclipse.swt.custom.CLabel(fComposite, style);
    new Resizer(fRightLabel, VERTICAL);
    if ((fCenter == null) || fCenter.isDisposed()) fCenter = createCenter(fComposite);

    createControls(fComposite);
    org.eclipse.ui.IWorkbenchPartSite ps = Utilities.findSite(fComposite);
    fHandlerService =
        (ps != null)
            ? ((org.eclipse.ui.handlers.IHandlerService)
                (ps.getService(org.eclipse.ui.handlers.IHandlerService.class)))
            : null;
    initializeToolbars(parent);
    return fComposite;
  }
}
