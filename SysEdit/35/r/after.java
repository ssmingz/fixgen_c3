class PlaceHold {
  protected final Control buildControl(Composite parent) {

    fComposite =
        new Composite(parent, fStyles | SWT.LEFT_TO_RIGHT) { // we force a specific direction
          public boolean setFocus() {
            return ContentMergeViewer.this.handleSetFocus();
          }
        };
    fComposite.setData(CompareUI.COMPARE_VIEWER_TITLE, getTitle());

    hookControl(fComposite); // hook help & dispose listener

    fComposite.setLayout(new ContentMergeViewerLayout());

    int style = SWT.SHADOW_OUT;
    fAncestorLabel = new CLabel(fComposite, style);

    fLeftLabel = new CLabel(fComposite, style);
    new Resizer(fLeftLabel, VERTICAL);

    fDirectionLabel = new CLabel(fComposite, style);
    fDirectionLabel.setAlignment(SWT.CENTER);
    new Resizer(fDirectionLabel, HORIZONTAL | VERTICAL);

    fRightLabel = new CLabel(fComposite, style);
    new Resizer(fRightLabel, VERTICAL);

    if (fCenter == null || fCenter.isDisposed()) fCenter = createCenterControl(fComposite);

    createControls(fComposite);

    IServiceLocator locator = getCompareConfiguration().getContainer().getServiceLocator();
    fHandlerService =
        locator != null ? (IHandlerService) locator.getService(IHandlerService.class) : null;

    initializeToolbars(parent);

    return fComposite;
  }
}
