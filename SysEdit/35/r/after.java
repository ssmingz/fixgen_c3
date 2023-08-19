class PlaceHold {
  protected final org.eclipse.compare.contentmergeviewer.Control buildControl(Composite parent) {
    fComposite =
        new Composite(parent, fStyles | org.eclipse.swt.SWT.LEFT_TO_RIGHT) {
          // we force a specific direction
          public boolean setFocus() {
            return org.eclipse.compare.contentmergeviewer.ContentMergeViewer.this.handleSetFocus();
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
    if ((fCenter == null) || fCenter.isDisposed()) fCenter = createCenterControl(fComposite);

    createControls(fComposite);
    org.eclipse.ui.services.IServiceLocator locator =
        getCompareConfiguration().getContainer().getServiceLocator();
    fHandlerService =
        (locator != null)
            ? ((org.eclipse.ui.handlers.IHandlerService)
                (locator.getService(org.eclipse.ui.handlers.IHandlerService.class)))
            : null;
    initializeToolbars(parent);
    return fComposite;
  }
}
