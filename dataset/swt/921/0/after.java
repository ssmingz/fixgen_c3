class PlaceHold {
  static int initSelectionIfaceCB(int iface) {
    AtkSelectionIface selectionIface = new AtkSelectionIface();
    ATK.memmove(selectionIface, iface);
    selectionIface.is_child_selected = atkSelectionCB_is_child_selected.getAddress();
    selectionIface.ref_selection = atkSelectionCB_ref_selection.getAddress();
    ATK.memmove(iface, selectionIface);
    return 0;
  }
}
