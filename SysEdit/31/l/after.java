class PlaceHold {
  protected final Control buildControl(Composite parent) {

    fComposite =
        new Composite(parent, fStyles | SWT.LEFT_TO_RIGHT) { // we force a specific direction
          public boolean setFocus() {
            return internalSetFocus();
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

    if (fCenter == null || fCenter.isDisposed()) fCenter = createCenter(fComposite);

    createControls(fComposite);

    IWorkbenchPartSite ps = Utilities.findSite(fComposite);
    fHandlerService = ps != null ? (IHandlerService) ps.getService(IHandlerService.class) : null;

    ToolBarManager tbm = CompareViewerPane.getToolBarManager(parent);
    if (tbm != null) {
      tbm.removeAll();

      // define groups
      tbm.add(new Separator("modes")); // $NON-NLS-1$
      tbm.add(new Separator("merge")); // $NON-NLS-1$
      tbm.add(new Separator("navigation")); // $NON-NLS-1$

      CompareConfiguration cc = getCompareConfiguration();

      if (cc.isRightEditable()) {
        fCopyLeftToRightAction =
            new Action() {
              public void run() {
                copy(true);
              }
            };
        Utilities.initAction(
            fCopyLeftToRightAction, getResourceBundle(), "action.CopyLeftToRight."); // $NON-NLS-1$
        tbm.appendToGroup("merge", fCopyLeftToRightAction); // $NON-NLS-1$
        Utilities.registerAction(
            fHandlerService,
            fCopyLeftToRightAction,
            "org.eclipse.compare.copyAllLeftToRight",
            fActivations); //$NON-NLS-1$
      }

      if (cc.isLeftEditable()) {
        fCopyRightToLeftAction =
            new Action() {
              public void run() {
                copy(false);
              }
            };
        Utilities.initAction(
            fCopyRightToLeftAction, getResourceBundle(), "action.CopyRightToLeft."); // $NON-NLS-1$
        tbm.appendToGroup("merge", fCopyRightToLeftAction); // $NON-NLS-1$
        Utilities.registerAction(
            fHandlerService,
            fCopyRightToLeftAction,
            "org.eclipse.compare.copyAllRightToLeft",
            fActivations); //$NON-NLS-1$
      }

      Action a =
          new ChangePropertyAction(
              fBundle,
              fCompareConfiguration,
              "action.EnableAncestor.",
              ANCESTOR_ENABLED); //$NON-NLS-1$
      a.setChecked(fAncestorEnabled);
      fAncestorItem = new ActionContributionItem(a);
      fAncestorItem.setVisible(false);
      tbm.appendToGroup("modes", fAncestorItem); // $NON-NLS-1$

      createToolItems(tbm);
      updateToolItems();

      tbm.update(true);
    }

    return fComposite;
  }
}
