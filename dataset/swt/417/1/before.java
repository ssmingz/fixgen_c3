class PlaceHold {
  private void dropTargetHandleDrop(DropTargetEvent event, File targetFile) {
    if (!dropTargetValidate(event, targetFile)) {
      return;
    }
    final String[] sourceNames = ((String[]) (event.data));
    if (sourceNames == null) {
      event.detail = DND.DROP_NONE;
    }
    if (event.detail == DND.DROP_NONE) {
      return;
    }
    progressDialog =
        new ProgressDialog(
            shell, event.detail == DND.DROP_MOVE ? ProgressDialog.MOVE : ProgressDialog.COPY);
    progressDialog.setTotalWorkUnits(sourceNames.length);
    progressDialog.open();
    Vector<File> processedFiles = new Vector<File>();
    for (int i = 0; (i < sourceNames.length) && (!progressDialog.isCancelled()); i++) {
      final File source = new File(sourceNames[i]);
      final File dest = new File(targetFile, source.getName());
      if (source.equals(dest)) {
        continue;
      }
      progressDialog.setDetailFile(source, ProgressDialog.COPY);
      while (!progressDialog.isCancelled()) {
        if (copyFileStructure(source, dest)) {
          processedFiles.add(source);
          break;
        } else if (!progressDialog.isCancelled()) {
          if ((event.detail == DND.DROP_MOVE) && (!isDragging)) {
            MessageBox box = new MessageBox(shell, (SWT.ICON_ERROR | SWT.RETRY) | SWT.CANCEL);
            box.setText(getResourceString("dialog.FailedCopy.title"));
            box.setMessage(
                getResourceString("dialog.FailedCopy.description", new Object[] {source, dest}));
            int button = box.open();
            if (button == SWT.CANCEL) {
              i = sourceNames.length;
              event.detail = DND.DROP_NONE;
              break;
            }
          } else {
            MessageBox box =
                new MessageBox(shell, ((SWT.ICON_ERROR | SWT.ABORT) | SWT.RETRY) | SWT.IGNORE);
            box.setText(getResourceString("dialog.FailedCopy.title"));
            box.setMessage(
                getResourceString("dialog.FailedCopy.description", new Object[] {source, dest}));
            int button = box.open();
            if (button == SWT.ABORT) {
              i = sourceNames.length;
            }
            if (button != SWT.RETRY) {
              break;
            }
          }
        }
        progressDialog.addProgress(1);
      }
    }
    if (isDragging) {
      processedDropFiles = processedFiles.toArray(new File[processedFiles.size()]);
    } else {
      progressDialog.close();
      progressDialog = null;
    }
    notifyRefreshFiles(new File[] {targetFile});
  }
}
