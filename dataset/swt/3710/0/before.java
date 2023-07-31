class DropTarget {
  public DropTarget(Control control, int style) {
    super(control, checkStyle(style));
    this.control = control;
    if (((DropProc == null) || (DragProc == null)) || (TransferProc == null)) {
      DND.error(ERROR_CANNOT_INIT_DROP);
    }
    if (control.getData(DROPTARGETID) != null) {
      DND.error(ERROR_CANNOT_INIT_DROP);
    }
    control.setData(DROPTARGETID, this);
    controlListener =
        new Listener() {
          public void handleEvent(Event event) {
            switch (event.type) {
              case SWT.Dispose:
                {
                  if (!DropTarget.this.isDisposed()) {
                    onDispose();
                  }
                  break;
                }
              case SWT.Show:
                {
                  if (!registered) {
                    registerDropTarget();
                  } else {
                    int[] args = new int[] {OS.XmNdropSiteActivity, OS.XmDROP_SITE_ACTIVE};
                    OS.XmDropSiteUpdate(DropTarget.this.control.handle, args, args.length / 2);
                    if (DropTarget.this.control instanceof Label) {
                      int formHandle = OS.XtParent(DropTarget.this.control.handle);
                      OS.XmDropSiteUpdate(formHandle, args, args.length / 2);
                    }
                  }
                  break;
                }
              case SWT.Hide:
                {
                  int[] args = new int[] {OS.XmNdropSiteActivity, OS.XmDROP_SITE_INACTIVE};
                  OS.XmDropSiteUpdate(DropTarget.this.control.handle, args, args.length / 2);
                  if (DropTarget.this.control instanceof Label) {
                    int formHandle = OS.XtParent(DropTarget.this.control.handle);
                    OS.XmDropSiteUpdate(formHandle, args, args.length / 2);
                  }
                  break;
                }
            }
          }
        };
    control.addListener(Dispose, controlListener);
    Control c = control;
    while (c != null) {
      c.addListener(Show, controlListener);
      c.addListener(Hide, controlListener);
      c = c.getParent();
    }
    this.addListener(
        Dispose,
        new Listener() {
          public void handleEvent(Event event) {
            if ((DropTarget.this.control == null) || DropTarget.this.control.isDisposed()) {
              return;
            }
            unregisterDropTarget();
            onDispose();
          }
        });
    Object effect = control.getData(DEFAULT_DROP_TARGET_EFFECT);
    if (effect instanceof DropTargetEffect) {
      dropEffect = ((DropTargetEffect) (effect));
    } else if (control instanceof Table) {
      dropEffect = new TableDropTargetEffect();
    } else if (control instanceof Tree) {
      dropEffect = new TreeDropTargetEffect();
    }
    if (control.isVisible()) {
      registerDropTarget();
    }
    dragOverHeartbeat =
        new Runnable() {
          public void run() {
            Control control = DropTarget.this.control;
            if (((control == null) || control.isDisposed()) || (dragOverStart == 0)) {
              return;
            }
            long time = System.currentTimeMillis();
            int delay = DRAGOVER_HYSTERESIS;
            if (time < dragOverStart) {
              delay = ((int) (dragOverStart - time));
            } else {
              int allowedOperations = dragOverEvent.operations;
              TransferData[] allowedTypes = dragOverEvent.dataTypes;
              TransferData[] dataTypes = new TransferData[allowedTypes.length];
              System.arraycopy(allowedTypes, 0, dataTypes, 0, dataTypes.length);
              DNDEvent event = new DNDEvent();
              event.widget = dragOverEvent.widget;
              event.x = dragOverEvent.x;
              event.y = dragOverEvent.y;
              event.time = ((int) (time));
              event.feedback = DND.FEEDBACK_SELECT;
              event.dataTypes = dataTypes;
              event.dataType = selectedDataType;
              event.operations = dragOverEvent.operations;
              event.detail = selectedOperation;
              event.item = getItem(getControl(), dragOverEvent.x, dragOverEvent.y);
              notifyListeners(DragOver, event);
              selectedDataType = null;
              if (event.dataType != null) {
                for (int i = 0; i < allowedTypes.length; i++) {
                  if (allowedTypes[i].type == event.dataType.type) {
                    selectedDataType = event.dataType;
                    break;
                  }
                }
              }
              selectedOperation = DND.DROP_NONE;
              if ((selectedDataType != null) && ((event.detail & allowedOperations) != 0)) {
                selectedOperation = event.detail;
              }
            }
            control = DropTarget.this.control;
            if ((control == null) || control.isDisposed()) {
              return;
            }
            control.getDisplay().timerExec(delay, dragOverHeartbeat);
          }
        };
  }
}
