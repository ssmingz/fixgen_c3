class PlaceHold {
  int initSelectionIfaceCB(int iface) {
    AtkSelectionIface selectionIface = new AtkSelectionIface();
    ATK.memmove(selectionIface, iface);
    atkSelectionCB_is_child_selected = new Callback(this, "atkSelection_is_child_selected", 2);
    selectionIface.is_child_selected = atkSelectionCB_is_child_selected.getAddress();
    atkSelectionCB_ref_selection = new Callback(this, "atkSelection_ref_selection", 2);
    selectionIface.ref_selection = atkSelectionCB_ref_selection.getAddress();
    ATK.memmove(iface, selectionIface);
    return 0;
  }
}
