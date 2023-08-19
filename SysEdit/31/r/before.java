class PlaceHold {
  protected void createToolItems(ToolBarManager tbm) {
    org.eclipse.ui.IWorkbenchPartSite ps =
        org.eclipse.compare.internal.Utilities.findSite(fComposite);
    fKeyBindingService = (ps != null) ? ps.getKeyBindingService() : null;
    final String ignoreAncestorActionKey = "action.IgnoreAncestor."; // $NON-NLS-1$

    Action ignoreAncestorAction =
        new Action() {
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
    fIgnoreAncestorItem = new ActionContributionItem(ignoreAncestorAction);
    fIgnoreAncestorItem.setVisible(false);
    tbm.appendToGroup("modes", fIgnoreAncestorItem); // $NON-NLS-1$

    tbm.add(new Separator());
    Action a =
        new Action() {
          public void run() {
            navigate(true, true, true);
          }
        };
    org.eclipse.compare.internal.Utilities.initAction(
        a, getResourceBundle(), "action.NextDiff."); // $NON-NLS-1$

    fNextItem = new ActionContributionItem(a);
    tbm.appendToGroup("navigation", fNextItem); // $NON-NLS-1$

    org.eclipse.compare.internal.Utilities.registerAction(
        fKeyBindingService, a, "org.eclipse.compare.selectNextChange"); // $NON-NLS-1$

    a =
        new Action() {
          public void run() {
            navigate(false, true, true);
          }
        };
    org.eclipse.compare.internal.Utilities.initAction(
        a, getResourceBundle(), "action.PrevDiff."); // $NON-NLS-1$

    fPreviousItem = new ActionContributionItem(a);
    tbm.appendToGroup("navigation", fPreviousItem); // $NON-NLS-1$

    org.eclipse.compare.internal.Utilities.registerAction(
        fKeyBindingService, a, "org.eclipse.compare.selectPreviousChange"); // $NON-NLS-1$

    CompareConfiguration cc = getCompareConfiguration();
    if (cc.isRightEditable()) {
      a =
          new Action() {
            public void run() {
              copyDiffLeftToRight();
            }
          };
      org.eclipse.compare.internal.Utilities.initAction(
          a, getResourceBundle(), "action.CopyDiffLeftToRight."); // $NON-NLS-1$

      fCopyDiffLeftToRightItem = new ActionContributionItem(a);
      fCopyDiffLeftToRightItem.setVisible(true);
      tbm.appendToGroup("merge", fCopyDiffLeftToRightItem); // $NON-NLS-1$

      org.eclipse.compare.internal.Utilities.registerAction(
          fKeyBindingService, a, "org.eclipse.compare.copyLeftToRight"); // $NON-NLS-1$
    }
    if (cc.isLeftEditable()) {
      a =
          new Action() {
            public void run() {
              copyDiffRightToLeft();
            }
          };
      org.eclipse.compare.internal.Utilities.initAction(
          a, getResourceBundle(), "action.CopyDiffRightToLeft."); // $NON-NLS-1$

      fCopyDiffRightToLeftItem = new ActionContributionItem(a);
      fCopyDiffRightToLeftItem.setVisible(true);
      tbm.appendToGroup("merge", fCopyDiffRightToLeftItem); // $NON-NLS-1$

      org.eclipse.compare.internal.Utilities.registerAction(
          fKeyBindingService, a, "org.eclipse.compare.copyRightToLeft"); // $NON-NLS-1$
    }
  }
}
