class PlaceHold {
  boolean setEventData(int theDrag, DNDEvent event) {
    if (theDrag == 0) {
      return false;
    }
    int style = getStyle();
    int[] outActions = new int[1];
    OS.GetDragAllowableActions(theDrag, outActions);
    int operations = osOpToOp(outActions[0]) & style;
    if (operations == DND.DROP_NONE) {
      return false;
    }
    int operation = getOperationFromKeyState(theDrag);
    keyOperation = operation;
    if (operation == DND.DROP_DEFAULT) {
      if ((style & DND.DROP_DEFAULT) == 0) {
        operation = ((operations & DND.DROP_MOVE) != 0) ? DND.DROP_MOVE : DND.DROP_NONE;
      }
    } else if ((operation & operations) == 0) {
      operation = DND.DROP_NONE;
    }
    short[] numItems = new short[1];
    OS.CountDragItems(theDrag, numItems);
    int[] flavors = new int[10];
    int index = -1;
    for (short i = 0; i < numItems[0]; i++) {
      int[] theItemRef = new int[1];
      OS.GetDragItemReferenceNumber(theDrag, ((short) (i + 1)), theItemRef);
      short[] numFlavors = new short[1];
      OS.CountDragItemFlavors(theDrag, theItemRef[0], numFlavors);
      int[] theType = new int[1];
      for (int j = 0; j < numFlavors[0]; j++) {
        theType[0] = 0;
        if (OS.GetFlavorType(theDrag, theItemRef[0], ((short) (j + 1)), theType) == OS.noErr) {
          boolean unique = true;
          for (int k = 0; k < flavors.length; k++) {
            if (flavors[k] == theType[0]) {
              unique = false;
              break;
            }
          }
          if (unique) {
            if (index == (flavors.length - 1)) {
              int[] temp = new int[flavors.length + 10];
              System.arraycopy(flavors, 0, temp, 0, flavors.length);
              flavors = temp;
            }
            flavors[++index] = theType[0];
          }
        }
      }
    }
    if (index == (-1)) {
      return false;
    }
    TransferData[] dataTypes = new TransferData[index + 1];
    index = -1;
    for (int i = 0; i < dataTypes.length; i++) {
      if (flavors[i] != 0) {
        TransferData data = new TransferData();
        data.type = flavors[i];
        for (int j = 0; j < transferAgents.length; j++) {
          Transfer transfer = transferAgents[j];
          if ((transfer != null) && transfer.isSupportedType(data)) {
            dataTypes[++index] = data;
            break;
          }
        }
      }
    }
    if (index == (-1)) {
      return false;
    }
    if (index < (dataTypes.length - 1)) {
      TransferData[] temp = new TransferData[index + 1];
      System.arraycopy(dataTypes, 0, temp, 0, index + 1);
      dataTypes = temp;
    }
    Point mouse = new Point();
    OS.GetDragMouse(theDrag, mouse, null);
    event.widget = this;
    event.x = mouse.h;
    event.y = mouse.v;
    event.time = ((int) (System.currentTimeMillis()));
    event.feedback = DND.FEEDBACK_SELECT;
    event.dataTypes = dataTypes;
    event.dataType = dataTypes[0];
    event.operations = operations;
    event.detail = operation;
    if (dropEffect != null) {
      event.item = dropEffect.getItem(event.x, event.y);
    }
    return true;
  }
}
