class PlaceHold {
  boolean setEventData(int context, int x, int y, int time, DNDEvent event) {
    if (context == 0) {
      return false;
    }
    GdkDragContext dragContext = new GdkDragContext();
    OS.memmove(dragContext, context, sizeof);
    if (dragContext.targets == 0) {
      return false;
    }
    int style = getStyle();
    int operations = osOpToOp(dragContext.actions) & style;
    if (operations == DND.DROP_NONE) {
      return false;
    }
    int operation = getOperationFromKeyState();
    keyOperation = operation;
    if (operation == DND.DROP_DEFAULT) {
      if ((style & DND.DROP_DEFAULT) == 0) {
        operation = ((operations & DND.DROP_MOVE) != 0) ? DND.DROP_MOVE : DND.DROP_NONE;
      }
    } else if ((operation & operations) == 0) {
      operation = DND.DROP_NONE;
    }
    int length = OS.g_list_length(dragContext.targets);
    TransferData[] dataTypes = new TransferData[0];
    for (int i = 0; i < length; i++) {
      int pData = OS.g_list_nth(dragContext.targets, i);
      GtkTargetPair gtkTargetPair = new GtkTargetPair();
      OS.memmove(gtkTargetPair, pData, sizeof);
      TransferData data = new TransferData();
      data.type = gtkTargetPair.target;
      for (int j = 0; j < transferAgents.length; j++) {
        Transfer transfer = transferAgents[j];
        if ((transfer != null) && transfer.isSupportedType(data)) {
          TransferData[] newDataTypes = new TransferData[dataTypes.length + 1];
          System.arraycopy(dataTypes, 0, newDataTypes, 0, dataTypes.length);
          newDataTypes[dataTypes.length] = data;
          dataTypes = newDataTypes;
          break;
        }
      }
    }
    if (dataTypes.length == 0) {
      return false;
    }
    int window;
    if (OS.GTK_VERSION >= OS.VERSION(2, 14, 0)) {
      window = OS.gtk_widget_get_window(control.handle);
    } else {
      window = OS.GTK_WIDGET_WINDOW(control.handle);
    }
    int[] origin_x = new int[1];
    int[] origin_y = new int[1];
    OS.gdk_window_get_origin(window, origin_x, origin_y);
    Point coordinates = new Point(origin_x[0] + x, origin_y[0] + y);
    event.widget = this;
    event.x = coordinates.x;
    event.y = coordinates.y;
    event.time = time;
    event.feedback = DND.FEEDBACK_SELECT;
    event.dataTypes = dataTypes;
    event.dataType = dataTypes[0];
    event.operations = operations;
    event.detail = operation;
    if (dropEffect != null) {
      event.item = dropEffect.getItem(coordinates.x, coordinates.y);
    }
    return true;
  }
}
