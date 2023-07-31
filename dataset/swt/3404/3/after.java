class PlaceHold {
  int convertProcCallback(
      int widget,
      int pSelection,
      int pTarget,
      int pType_return,
      int ppValue_return,
      int pLength_return,
      int pFormat_return,
      int max_length,
      int client_data,
      int request_id) {
    if (pSelection == 0) {
      return 0;
    }
    int[] selection = new int[1];
    OS.memmove(selection, pSelection, 4);
    int xDisplay = getDisplay().xDisplay;
    byte[] bName = Converter.wcsToMbcs(null, "_MOTIF_DROP", true);
    int motifDropAtom = OS.XmInternAtom(xDisplay, bName, true);
    if (selection[0] != motifDropAtom) {
      return 0;
    }
    int[] target = new int[1];
    OS.memmove(target, pTarget, 4);
    bName = Converter.wcsToMbcs(null, "DELETE", true);
    int deleteAtom = OS.XmInternAtom(xDisplay, bName, true);
    if (target[0] == deleteAtom) {
      moveRequested = true;
      bName = Converter.wcsToMbcs(null, "NULL", true);
      int nullAtom = OS.XmInternAtom(xDisplay, bName, true);
      OS.memmove(pType_return, new int[] {nullAtom}, 4);
      OS.memmove(ppValue_return, new int[] {0}, 4);
      OS.memmove(pLength_return, new int[] {0}, 4);
      OS.memmove(pFormat_return, new int[] {8}, 4);
      return 1;
    }
    boolean dataMatch = false;
    TransferData transferData = new TransferData();
    transferData.type = target[0];
    for (int i = 0; i < transferAgents.length; i++) {
      Transfer transfer = transferAgents[i];
      if ((transfer != null) && transfer.isSupportedType(transferData)) {
        dataMatch = true;
        break;
      }
    }
    if (!dataMatch) {
      return 0;
    }
    DNDEvent event = new DNDEvent();
    event.widget = control;
    event.dataType = transferData;
    notifyListeners(DragSetData, event);
    if (!event.doit) {
      return 0;
    }
    Transfer transferAgent = null;
    for (int i = 0; i < transferAgents.length; i++) {
      Transfer transfer = transferAgents[i];
      if ((transfer != null) && transfer.isSupportedType(transferData)) {
        transferAgent = transfer;
        break;
      }
    }
    if (transferAgent == null) {
      return 0;
    }
    transferAgent.javaToNative(event.data, transferData);
    if (transferData.result == 1) {
      OS.memmove(ppValue_return, new int[] {transferData.pValue}, 4);
      OS.memmove(pType_return, new int[] {transferData.type}, 4);
      OS.memmove(pLength_return, new int[] {transferData.length}, 4);
      OS.memmove(pFormat_return, new int[] {transferData.format}, 4);
      return 1;
    }
    OS.memmove(ppValue_return, new int[] {0}, 4);
    OS.memmove(pLength_return, new int[] {0}, 4);
    OS.memmove(pFormat_return, new int[] {8}, 4);
    return 0;
  }
}
