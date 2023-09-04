class PlaceHold {
  protected void createToolItems(ToolBarManager tbm) {

    IServiceLocator locator = getCompareConfiguration().getContainer().getServiceLocator();
    fHandlerService =
        locator != null ? (IHandlerService) locator.getService(IHandlerService.class) : null;

    final String ignoreAncestorActionKey = "action.IgnoreAncestor."; // $NON-NLS-1$
    Action ignoreAncestorAction =
        new Action() {
          public void run() {
            // First make sure the ancestor is hidden
            if (!isIgnoreAncestor())
              getCompareConfiguration()
                  .setProperty(ICompareUIConstants.PROP_ANCESTOR_VISIBLE, Boolean.FALSE);
            // Then set the property to ignore the ancestor
            getCompareConfiguration()
                .setProperty(
                    ICompareUIConstants.PROP_IGNORE_ANCESTOR, Boolean.valueOf(!isIgnoreAncestor()));
            Utilities.initToggleAction(
                this, getResourceBundle(), ignoreAncestorActionKey, isIgnoreAncestor());
          }
        };
    ignoreAncestorAction.setChecked(isIgnoreAncestor());
    Utilities.initAction(ignoreAncestorAction, getResourceBundle(), ignoreAncestorActionKey);
    Utilities.initToggleAction(
        ignoreAncestorAction, getResourceBundle(), ignoreAncestorActionKey, isIgnoreAncestor());

    fIgnoreAncestorItem = new ActionContributionItem(ignoreAncestorAction);
    fIgnoreAncestorItem.setVisible(false);
    tbm.appendToGroup("modes", fIgnoreAncestorItem); // $NON-NLS-1$

    tbm.add(new Separator());

    Action a =
        new Action() {
          public void run() {
            navigate(true, true, isStepIntoEnabled());
          }
        };
    Utilities.initAction(a, getResourceBundle(), "action.NextDiff."); // $NON-NLS-1$
    fNextItem = new ActionContributionItem(a);
    tbm.appendToGroup("navigation", fNextItem); // $NON-NLS-1$
    Utilities.registerAction(
        fHandlerService, a, "org.eclipse.compare.selectNextChange", fActivations); // $NON-NLS-1$

    a =
        new Action() {
          public void run() {
            navigate(false, true, isStepIntoEnabled());
          }
        };
    Utilities.initAction(a, getResourceBundle(), "action.PrevDiff."); // $NON-NLS-1$
    fPreviousItem = new ActionContributionItem(a);
    tbm.appendToGroup("navigation", fPreviousItem); // $NON-NLS-1$
    Utilities.registerAction(
        fHandlerService,
        a,
        "org.eclipse.compare.selectPreviousChange",
        fActivations); //$NON-NLS-1$

    a =
        new ChangePropertyAction(
            getResourceBundle(),
            getCompareConfiguration(),
            "action.StepInto.",
            STEP_INTO_PROPERTY); //$NON-NLS-1$
    fStepIntoItem = new ActionContributionItem(a);
    tbm.appendToGroup("navigation", fStepIntoItem); // $NON-NLS-1$

    CompareConfiguration cc = getCompareConfiguration();

    if (cc.isRightEditable()) {
      a =
          new Action() {
            public void run() {
              copyDiffLeftToRight();
            }
          };
      Utilities.initAction(a, getResourceBundle(), "action.CopyDiffLeftToRight."); // $NON-NLS-1$
      fCopyDiffLeftToRightItem = new ActionContributionItem(a);
      fCopyDiffLeftToRightItem.setVisible(true);
      tbm.appendToGroup("merge", fCopyDiffLeftToRightItem); // $NON-NLS-1$
      Utilities.registerAction(
          fHandlerService, a, "org.eclipse.compare.copyLeftToRight", fActivations); // $NON-NLS-1$
    }

    if (cc.isLeftEditable()) {
      a =
          new Action() {
            public void run() {
              copyDiffRightToLeft();
            }
          };
      Utilities.initAction(a, getResourceBundle(), "action.CopyDiffRightToLeft."); // $NON-NLS-1$
      fCopyDiffRightToLeftItem = new ActionContributionItem(a);
      fCopyDiffRightToLeftItem.setVisible(true);
      tbm.appendToGroup("merge", fCopyDiffRightToLeftItem); // $NON-NLS-1$
      Utilities.registerAction(
          fHandlerService, a, "org.eclipse.compare.copyRightToLeft", fActivations); // $NON-NLS-1$
    }
  }
}
