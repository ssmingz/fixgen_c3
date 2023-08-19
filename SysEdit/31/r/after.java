class PlaceHold {
  protected void createToolItems(org.eclipse.jface.action.ToolBarManager tbm) {
    org.eclipse.ui.IWorkbenchPartSite ps =
        org.eclipse.compare.internal.Utilities.findSite(fComposite);
    fHandlerService =
        (ps != null)
            ? ((org.eclipse.ui.handlers.IHandlerService)
                (ps.getService(org.eclipse.ui.handlers.IHandlerService.class)))
            : null;
    final String ignoreAncestorActionKey = "action.IgnoreAncestor."; // $NON-NLS-1$

    org.eclipse.jface.action.Action ignoreAncestorAction =
        new org.eclipse.jface.action.Action() {
          public void run() {
            setIgnoreAncestor(!fIgnoreAncestor);
            org.eclipse.compare.internal.Utilities.initToggleAction(
                this, getResourceBundle(), ignoreAncestorActionKey, fIgnoreAncestor);
          }
        };
    ignoreAncestorAction.setChecked(fIgnoreAncestor);
    org.eclipse.compare.internal.Utilities.initAction(
        ignoreAncestorAction, getResourceBundle(), ignoreAncestorActionKey);
    org.eclipse.compare.internal.Utilities.initToggleAction(
        ignoreAncestorAction, getResourceBundle(), ignoreAncestorActionKey, fIgnoreAncestor);
    fIgnoreAncestorItem = new org.eclipse.jface.action.ActionContributionItem(ignoreAncestorAction);
    fIgnoreAncestorItem.setVisible(false);
    tbm.appendToGroup("modes", fIgnoreAncestorItem); // $NON-NLS-1$

    tbm.add(new org.eclipse.jface.action.Separator());
    org.eclipse.jface.action.Action a =
        new org.eclipse.jface.action.Action() {
          public void run() {
            navigate(true, true, true);
          }
        };
    org.eclipse.compare.internal.Utilities.initAction(
        a, getResourceBundle(), "action.NextDiff."); // $NON-NLS-1$

    fNextItem = new org.eclipse.jface.action.ActionContributionItem(a);
    tbm.appendToGroup("navigation", fNextItem); // $NON-NLS-1$

    org.eclipse.compare.internal.Utilities.registerAction(
        fHandlerService, a, "org.eclipse.compare.selectNextChange", fActivations); // $NON-NLS-1$

    a =
        new org.eclipse.jface.action.Action() {
          public void run() {
            navigate(false, true, true);
          }
        };
    org.eclipse.compare.internal.Utilities.initAction(
        a, getResourceBundle(), "action.PrevDiff."); // $NON-NLS-1$

    fPreviousItem = new org.eclipse.jface.action.ActionContributionItem(a);
    tbm.appendToGroup("navigation", fPreviousItem); // $NON-NLS-1$

    org.eclipse.compare.internal.Utilities.registerAction(
        fHandlerService,
        a,
        "org.eclipse.compare.selectPreviousChange",
        fActivations); // $NON-NLS-1$

    org.eclipse.compare.CompareConfiguration cc = getCompareConfiguration();
    if (cc.isRightEditable()) {
      a =
          new org.eclipse.jface.action.Action() {
            public void run() {
              copyDiffLeftToRight();
            }
          };
      org.eclipse.compare.internal.Utilities.initAction(
          a, getResourceBundle(), "action.CopyDiffLeftToRight."); // $NON-NLS-1$

      fCopyDiffLeftToRightItem = new org.eclipse.jface.action.ActionContributionItem(a);
      fCopyDiffLeftToRightItem.setVisible(true);
      tbm.appendToGroup("merge", fCopyDiffLeftToRightItem); // $NON-NLS-1$

      org.eclipse.compare.internal.Utilities.registerAction(
          fHandlerService, a, "org.eclipse.compare.copyLeftToRight", fActivations); // $NON-NLS-1$
    }
    if (cc.isLeftEditable()) {
      a =
          new org.eclipse.jface.action.Action() {
            public void run() {
              copyDiffRightToLeft();
            }
          };
      org.eclipse.compare.internal.Utilities.initAction(
          a, getResourceBundle(), "action.CopyDiffRightToLeft."); // $NON-NLS-1$

      fCopyDiffRightToLeftItem = new org.eclipse.jface.action.ActionContributionItem(a);
      fCopyDiffRightToLeftItem.setVisible(true);
      tbm.appendToGroup("merge", fCopyDiffRightToLeftItem); // $NON-NLS-1$

      org.eclipse.compare.internal.Utilities.registerAction(
          fHandlerService, a, "org.eclipse.compare.copyRightToLeft", fActivations); // $NON-NLS-1$
    }
  }
}
