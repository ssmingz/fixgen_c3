class PlaceHold {
  protected final org.eclipse.swt.widgets.Control buildControl(
      org.eclipse.swt.widgets.Composite parent) {
    fComposite =
        new org.eclipse.swt.widgets.Composite(parent, fStyles | org.eclipse.swt.SWT.LEFT_TO_RIGHT) {
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
    org.eclipse.ui.IWorkbenchPartSite ps =
        org.eclipse.compare.internal.Utilities.findSite(fComposite);
    fHandlerService =
        (ps != null)
            ? ((org.eclipse.ui.handlers.IHandlerService)
                (ps.getService(org.eclipse.ui.handlers.IHandlerService.class)))
            : null;
    org.eclipse.jface.action.ToolBarManager tbm =
        org.eclipse.compare.CompareViewerPane.getToolBarManager(parent);
    if (tbm != null) {
      tbm.removeAll();
      // define groups
      tbm.add(new org.eclipse.jface.action.Separator("modes")); // $NON-NLS-1$

      tbm.add(new org.eclipse.jface.action.Separator("merge")); // $NON-NLS-1$

      tbm.add(new org.eclipse.jface.action.Separator("navigation")); // $NON-NLS-1$

      org.eclipse.compare.CompareConfiguration cc = getCompareConfiguration();
      if (cc.isRightEditable()) {
        fCopyLeftToRightAction =
            new org.eclipse.jface.action.Action() {
              public void run() {
                copy(true);
              }
            };
        org.eclipse.compare.internal.Utilities.initAction(
            fCopyLeftToRightAction, getResourceBundle(), "action.CopyLeftToRight."); // $NON-NLS-1$

        tbm.appendToGroup("merge", fCopyLeftToRightAction); // $NON-NLS-1$

        org.eclipse.compare.internal.Utilities.registerAction(
            fHandlerService,
            fCopyLeftToRightAction,
            "org.eclipse.compare.copyAllLeftToRight",
            fActivations); // $NON-NLS-1$
      }
      if (cc.isLeftEditable()) {
        fCopyRightToLeftAction =
            new org.eclipse.jface.action.Action() {
              public void run() {
                copy(false);
              }
            };
        org.eclipse.compare.internal.Utilities.initAction(
            fCopyRightToLeftAction, getResourceBundle(), "action.CopyRightToLeft."); // $NON-NLS-1$

        tbm.appendToGroup("merge", fCopyRightToLeftAction); // $NON-NLS-1$

        org.eclipse.compare.internal.Utilities.registerAction(
            fHandlerService,
            fCopyRightToLeftAction,
            "org.eclipse.compare.copyAllRightToLeft",
            fActivations); // $NON-NLS-1$
      }
      org.eclipse.jface.action.Action a =
          new org.eclipse.compare.internal.ChangePropertyAction(
              fBundle,
              fCompareConfiguration,
              "action.EnableAncestor.",
              ANCESTOR_ENABLED); // $NON-NLS-1$

      a.setChecked(fAncestorEnabled);
      fAncestorItem = new org.eclipse.jface.action.ActionContributionItem(a);
      fAncestorItem.setVisible(false);
      tbm.appendToGroup("modes", fAncestorItem); // $NON-NLS-1$

      createToolItems(tbm);
      updateToolItems();
      tbm.update(true);
    }
    return fComposite;
  }
}
