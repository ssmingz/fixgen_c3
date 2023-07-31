class PlaceHold {
  public static void main(String[] args) {
    _log.log("Slave JVM has started with args " + Arrays.toString(args));
    try {
      System.setProperty("java.rmi.server.hostname", "127.0.0.1");
      if ((args.length != 3) && (args.length != 2)) {
        System.exit(1);
      }
      if (args.length == 3) {
        IRemoteClassLoader remoteLoader = null;
        FileInputStream fstream = new FileInputStream(args[2]);
        ObjectInputStream ostream = new ObjectInputStream(new BufferedInputStream(fstream));
        _log.log("Slave JVM reading remote loader object");
        remoteLoader = ((IRemoteClassLoader) (ostream.readObject()));
        _log.log("remote loader read");
        if (ClassLoader.getSystemClassLoader() instanceof CustomSystemClassLoader) {
          CustomSystemClassLoader loader =
              ((CustomSystemClassLoader) (ClassLoader.getSystemClassLoader()));
          loader.setMasterRemote(remoteLoader);
        }
        _log.log("remote loader installed");
      }
      _log.log("Slave JVM reading the remote master object");
      FileInputStream fstream = new FileInputStream(args[0]);
      ObjectInputStream ostream = new ObjectInputStream(new BufferedInputStream(fstream));
      MasterRemote master = ((MasterRemote) (ostream.readObject()));
      _log.log("remote master read");
      fstream.close();
      ostream.close();
      try {
        Class slaveClass = Class.forName(args[1]);
        _log.log("Slave JVM creating singleton instance of slave class " + args[1]);
        SlaveRemote slave = _getInstance(slaveClass);
        _log.log("Slave JVM creaing RMI stub for slave class instance " + slave);
        SlaveRemote stub = ((SlaveRemote) (UnicastRemoteObject.exportObject(slave)));
        _log.log((("Exported stub " + stub) + " for ") + slaveClass);
        _log.log(("Slave JVM invoking the method start(" + master) + ") in the Slave JVM class");
        slave.start(master);
        _log.log(("Slave JVM invoking the method registerSlave(" + slave) + ") in the Master JVM");
        master.registerSlave(slave);
      } catch (Throwable t) {
        _log.log("Slave JVM could not intstantiate slave class and will exit");
        try {
          master.errorStartingSlave(t);
        } catch (RemoteException re) {
          String msg =
              (("Couldn\'t instantiate and register the slave.\n"
                          + "  Also failed to display error through master JVM, because:\n")
                      + StringOps.getStackTrace(re))
                  + "\n";
          _showErrorMessage(msg, t);
        }
        System.exit(3);
      }
    } catch (Throwable t) {
      _showErrorMessage("Couldn't deserialize remote stub for the master JVM.", t);
      System.exit(2);
    }
  }
}
