class Accessible {
  Accessible(Control control) {
    this.control = control;
    int[] ppvObject = new int[1];
    int result =
        ((int)
            (COM.CreateStdAccessibleObject(
                control.handle, OBJID_CLIENT, IIDIAccessible, ppvObject)));
    if (ppvObject[0] == 0) {
      return;
    }
    if (result != COM.S_OK) {
      OLE.error(ERROR_CANNOT_CREATE_OBJECT, result);
    }
    iaccessible = new IAccessible(ppvObject[0]);
    createIAccessible();
    AddRef();
  }
}
