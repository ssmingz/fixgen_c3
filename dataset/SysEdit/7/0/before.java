class PlaceHold {
  public void openCompareEditor(
      final CompareEditorInput input, final IWorkbenchPage page, final IReusableEditor editor) {
    if (input.canRunAsJob()) {
      Job job =
          new Job("Opening Compare Editor") {
            protected IStatus run(IProgressMonitor monitor) {
              IStatus status = prepareInput(input, monitor);
              if (status.isOK()) {
                internalOpenEditor(input, page, editor);
                return Status.OK_STATUS;
              }
              if (status.getCode() == NO_DIFFERENCE) {
                MessageDialog.openInformation(
                    getShell(),
                    Utilities.getString("CompareUIPlugin.dialogTitle"),
                    Utilities.getString(
                        "CompareUIPlugin.noDifferences")); //$NON-NLS-1$//$NON-NLS-2$
                return Status.OK_STATUS;
              }
              return status;
            }

            public boolean belongsTo(Object family) {
              return input.belongsTo(family);
            }
          };
      job.setUser(true);
      job.schedule();
    } else {
      if (compareResultOK(input, null)) {
        internalOpenEditor(input, page, editor);
      }
    }
  }
}
