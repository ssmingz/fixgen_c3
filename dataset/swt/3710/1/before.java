class DropTarget {
  public DropTarget(Control control, int style) {
    super(control, checkStyle(style));
    this.control = control;
    if ((((Drag_Motion == null) || (Drag_Leave == null)) || (Drag_Data_Received == null))
        || (Drag_Drop == null)) {
      DND.error(ERROR_CANNOT_INIT_DROP);
    }
    if (control.getData(DROPTARGETID) != null) {
      DND.error(ERROR_CANNOT_INIT_DROP);
    }
    control.setData(DROPTARGETID, this);
    drag_motion_handler =
        OS.g_signal_connect(control.handle, drag_motion, Drag_Motion.getAddress(), 0);
    drag_leave_handler =
        OS.g_signal_connect(control.handle, drag_leave, Drag_Leave.getAddress(), 0);
    drag_data_received_handler =
        OS.g_signal_connect(control.handle, drag_data_received, Drag_Data_Received.getAddress(), 0);
    drag_drop_handler = OS.g_signal_connect(control.handle, drag_drop, Drag_Drop.getAddress(), 0);
    controlListener =
        new Listener() {
          public void handleEvent(Event event) {
            if (!DropTarget.this.isDisposed()) {
              DropTarget.this.dispose();
            }
          }
        };
    control.addListener(Dispose, controlListener);
    this.addListener(
        Dispose,
        new Listener() {
          public void handleEvent(Event event) {
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
              selectedDataType = null;
              selectedOperation = DND.DROP_NONE;
              notifyListeners(DragOver, event);
              if (event.dataType != null) {
                for (int i = 0; i < allowedTypes.length; i++) {
                  if (allowedTypes[i].type == event.dataType.type) {
                    selectedDataType = event.dataType;
                    break;
                  }
                }
              }
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
