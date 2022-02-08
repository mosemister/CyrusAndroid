package org.cyrus.android.impl;

import org.cyrus.CyrusWeb;
import org.cyrus.android.impl.view.context.BootstrapHandler;
import org.cyrus.webserver.request.WebRequests;

public class AndCyrusMain extends CyrusWeb {


    @Override
    public void init() {
        super.init();
        AndCyrusWebManager manager = new AndCyrusWebManager();
        this.setServerManager(manager);


        WebRequests.getRequests().forEach(manager::register);

        String path = "/bootstrap";
        String jsPath = path + "/js/";
        String cssPath = path + "/css/";

        String[] jsArray = new String[]{"bootstrap.bundle.js", "bootstrap.bundle.js.map", "bootstrap.bundle.min" +
                ".js", "bootstrap.bundle.min.js.map", "bootstrap.esm.js", "bootstrap.esm.js.map", "bootstrap.esm" +
                ".min.js", "bootstrap.esm.min.js.map", "bootstrap.js", "bootstrap.js.map", "bootstrap.min.js",
                "bootstrap.min.js.map"};

        for (String jsFile : jsArray) {
            manager.register(new BootstrapHandler(jsPath + jsFile));
        }

        String[] cssArray = new String[]{"bootstrap-grid.css",
                "bootstrap-grid.css.map",
                "bootstrap-grid.min.css",
                "bootstrap-grid.min.css.map",
                "bootstrap-grid.rtl.css",
                "bootstrap-grid.rtl.css.map",
                "bootstrap-grid.rtl.min.css",
                "bootstrap-grid.rtl.min.css.map",
                "bootstrap-reboot.css",
                "bootstrap-reboot.css.map", "bootstrap-reboot.min.css", "bootstrap-reboot.min.css.map", "bootstrap-reboot.rtl.css", "bootstrap-reboot.rtl.css.map", "bootstrap-reboot.rtl.min.css", "bootstrap-reboot.rtl.min.css.map", "bootstrap-utilities.css", "bootstrap-utilities.css.map", "bootstrap-utilities.min.css", "bootstrap-utilities.min.css.map", "bootstrap-utilities.rtl.css", "bootstrap-utilities.rtl.css.map", "bootstrap-utilities.rtl.min.css", "bootstrap-utilities.rtl.min.css.map", "bootstrap.css", "bootstrap.css.map", "bootstrap.min.css", "bootstrap.min.css.map", "bootstrap.rtl.css", "bootstrap.rtl.css.map", "bootstrap.rtl.min.css", "bootstrap.rtl.min.css.map"};
        for (String jsFile : cssArray) {
            manager.register(new BootstrapHandler(cssPath + jsFile));
        }
    }
}
