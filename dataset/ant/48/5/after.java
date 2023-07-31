class PlaceHold{
protected void addVendorFiles(Hashtable ejbFiles, String ddPrefix) {
    File weblogicDD = new File(getConfig().descriptorDir, ddPrefix + WL_DD);
    if (weblogicDD.exists()) {
        ejbFiles.put(META_DIR + WL_DD, weblogicDD);
    } else {
        log("Unable to locate weblogic deployment descriptor. It was expected to be in " + weblogicDD.getPath(), MSG_WARN);
        return;
    }
    if (!newCMP) {
        log("The old method for locating CMP files has been DEPRECATED.", MSG_VERBOSE);
        log("Please adjust your weblogic descriptor and set newCMP=\"true\" " + "to use the new CMP descriptor inclusion mechanism. ", MSG_VERBOSE);
        File weblogicCMPDD = new File(getConfig().descriptorDir, ddPrefix + WL_CMP_DD);
        if (weblogicCMPDD.exists()) {
            ejbFiles.put(META_DIR + WL_CMP_DD, weblogicCMPDD);
        }
    } else {
        try {
            File ejbDescriptor = ((File) (ejbFiles.get(META_DIR + EJB_DD)));
            SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
            saxParserFactory.setValidating(true);
            SAXParser saxParser = saxParserFactory.newSAXParser();
            DescriptorHandler handler = getWeblogicDescriptorHandler(ejbDescriptor.getParentFile());
            saxParser.parse(new InputSource(new FileInputStream(weblogicDD)), handler);
            Hashtable ht = handler.getFiles();
            Iterator e = ht.keys();
            while (e.hasNext()) {
                String key = ((String) (e.next()));
                ejbFiles.put(key, ht.get(key));
            } 
        } catch (Exception e) {
            String msg = "Exception while adding Vendor specific files: " + e.toString();
            throw new TaskException(msg, e);
        }
    }
}
}