class PlaceHold {
  static int initActionIfaceCB(int iface) {
    AtkActionIface actionIface = new AtkActionIface();
    ATK.memmove(actionIface, iface);
    actionIface.get_keybinding = atkActionCB_get_keybinding.getAddress();
    actionIface.get_name = atkActionCB_get_name.getAddress();
    ATK.memmove(iface, actionIface);
    return 0;
  }
}
